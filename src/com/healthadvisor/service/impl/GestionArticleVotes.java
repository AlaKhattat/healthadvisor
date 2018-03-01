package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Article_Votes;
import com.heathadvisor.service.IGestionArticlesVotes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestionArticleVotes implements IGestionArticlesVotes {

    public MyDB db;

    public GestionArticleVotes() {
        db = MyDB.getInstance();
    }

    @Override
    public void ajouterArticleVotes(Article_Votes av) {
        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("insert into article_vote(id_article, id_user, vote) values(?,?,?)");
            stm.setInt(1, av.getId_article());
            stm.setString(2, av.getLogin_user());
            stm.setDouble(3, av.getVote());
            stm.executeUpdate();
            System.out.println("Ajout effectué ! ");
        } catch (SQLException err) {
            System.out.println("Erreur ajout : " + err.getSQLState() + " msg:" + err.getMessage());
        }
    }

    @Override
    public void supprimerArticleVotes(Article_Votes av) {
        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("delete from article_vote where id_article= ? and id_user=?");
            stm.setInt(1, av.getId_article());
            stm.setString(2, av.getLogin_user());
            stm.executeUpdate();
            System.out.println("Suppression effectue ! ");
        } catch (SQLException err) {
            System.out.println("Erreur suppression : " + err.getSQLState());
        }
    }

    public void modifierArticleVotes(int id_article, String login, double vote) {
        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("update article_vote set vote= ? where id_article=? and id_user=?");
            stm.setDouble(1, vote);
            stm.setInt(2, id_article);
            stm.setString(3, login);
            stm.executeUpdate();
            System.out.println("modification effectuée !");
        } catch (SQLException e) {
            System.out.println("erreur modification ! "+e.getSQLState());
        }
    }

    @Override
    public List<Article_Votes> afficherArticleVotes() {
        List<Article_Votes> listAV = new ArrayList<>();
        try {
            String sql = "select * from article_vote";
            Statement stm = db.getConnexion().createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                Article_Votes av = new Article_Votes();
                av.setId_article(res.getInt(1));
                av.setLogin_user(res.getString(2));
                av.setVote(res.getDouble(3));
                listAV.add(av);
            }
            System.out.println("Affichage réussi ! ");
        } catch (SQLException err) {
            System.out.println("Erreur affichage : " + err.getSQLState());
        }
        return listAV;
    }
    
    public boolean rechArticleLogin(int id, String login){
        for(Article_Votes av : afficherArticleVotes()){
            if(av.getId_article()==id && av.getLogin_user().equals(login)){
                return true;
            }
        }
        return false;
    }

}
