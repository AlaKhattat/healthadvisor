package com.healthadvisor.service.impl;

import com.healthadvisor.javafx.article.LireArticleFXMLController;
import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Article;
import com.healthadvisor.entities.Article_Votes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.heathadvisor.service.IGestionArticle;
import java.sql.Blob;
import javafx.scene.control.Alert;

public class GestionArticle implements IGestionArticle {

    public MyDB db;

    public GestionArticle() {

        db = MyDB.getInstance();

    }

    public void ajouterArticle(Article a) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("insert into article(nom, description, contenu, id_medecin, url_image, moyenne_notes, validation, tags) values(?,?,?,?,?,?,?,?)");
            stm.setString(1, a.getNom());
            stm.setString(2, a.getDescription());
            stm.setString(3, a.getContenu());
            stm.setString(4, a.getIdMed());
            stm.setString(5, a.getImage());
            stm.setDouble(6,a.getNote());
            stm.setString(7,"Non défini");
            stm.setString(8, a.getTags());
            stm.executeUpdate();
            System.out.println("Ajout effectué ! ");
//            Alert al = new Alert(Alert.AlertType.INFORMATION);
//            al.setTitle("Ajout Article");
//            al.setHeaderText(null);
//            al.setContentText("Ajout de l'article '"+a.getNom()+"' réussie !");
//            al.showAndWait();

        } catch (SQLException e) {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Ajout Article");
            al.setHeaderText(null);
            al.setContentText("Echec de l'ajout de l'article '" + a.getNom() + "'\n " + e.getSQLState() + "\nMessage : " + e.getMessage());
            al.showAndWait();
        }
    }

    public void supprimerArticle(int ref) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("delete from article where reference =" + ref);
            stm.executeUpdate();
            System.out.println("Suppression effectue ! ");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Suppression Article");
            al.setHeaderText(null);
            al.setContentText("Suppression de l'article réussie !");
            al.showAndWait();
        } catch (SQLException e) {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Suppression Article");
            al.setHeaderText(null);
            al.setContentText("Echec de la suppression de l'article \nMessage : " + e.getMessage());
            al.showAndWait();
        }

    }

    public void modifierArticle(int ref, String nom, String desc, String tags, String cont, String image) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("update article set nom= ?, description=?, contenu=?, url_image=?, tags=? where reference=?");
            stm.setString(1, nom);
            stm.setString(2, desc);
            stm.setString(3, cont);
            stm.setString(4, image);
            stm.setString(5, tags);
            stm.setInt(6, ref);
            stm.executeUpdate();
            System.out.println("Modification réussie ! ");
//            Alert al = new Alert(Alert.AlertType.INFORMATION);
//            al.setTitle("Modification article");
//            al.setHeaderText(null);
//            al.setContentText("Modification de l'article réussie !");
//            al.showAndWait();
        } catch (SQLException e) {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Modification Article");
            al.setHeaderText(null);
            al.setContentText("Echec de la modification de l'article\n " + e.getSQLState() + "\nMessage : " + e.getMessage());
            al.showAndWait();
        }

    }

    @Override
    public List<Article> afficherArticle() {
        List<Article> listA = new ArrayList<>();

        try {
            String sql = "select * from article";
            Statement stm = db.getConnexion().createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                Article a = new Article();
                a.setReference(res.getInt(1));
                a.setNom(res.getString(2));
                a.setDescription(res.getString(3));
                a.setContenu(res.getString(4));
                a.setIdMed(res.getString(5));
                a.setImage(res.getString(6));
                a.setNote(res.getDouble(7));
                a.setValid(res.getString(8));
                a.setTags(res.getString(9));
                listA.add(a);

            }
            System.out.println("Affichage réussi ! ");
        } catch (SQLException e) {
            System.out.println("Erreur affichage : " + e.getSQLState());
        }
        return listA;
    }
    
    public int dernierArt(){
        int taille=afficherArticle().size()-1;
        Article a=afficherArticle().get(taille);
        return a.getReference();
    }

    public List<Article> rechercheArticle(String s) {

        List<Article> rech = new ArrayList<>();
        for (Article i : afficherArticle()) {
            String tot = i.getNom() + " " + i.getDescription() + " " + i.getContenu();
            if (tot.indexOf(s) != -1) {
                rech.add(i);
            }
        }
        return rech;
    }

    public Article rechercheRef(int ref) {
        Article a = new Article();
        for (Article i : afficherArticle()) {
            if(i.getReference()==ref){
                a=i;
            }
        }
        return a;
    }

    public void calcul(int id) {
        int compteur = 0;
        double total = 0;
        Article a = rechercheRef(id);
        GestionArticleVotes gav = new GestionArticleVotes();
        for (int i=0;i<gav.afficherArticleVotes().size();i++) {
            if (gav.afficherArticleVotes().get(i).getId_article() == a.getReference()) {
                total+=gav.afficherArticleVotes().get(i).getVote();
                compteur++;
            }
        }
        double moy = total / compteur;
        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("update article set moyenne_notes= ? where reference="+id);
            stm.setDouble(1, moy);
            stm.executeUpdate();
            System.out.println("notes mises à jour !");
        } catch (Exception e) {
        }
    }
    
    public boolean isValid(int id) {
        Article a = rechercheRef(id);
        for (Article i : afficherArticle()) {
            if (i.getReference()== id) {
                if(i.getValid().equals("Validé")){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void validationArticle(int id, String statut) {
        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("update article set validation=? where reference=?");
            stm.setString(1, statut);
            stm.setInt(2, id);
            stm.executeUpdate();
            if (statut.equals("Validé")) {
                System.out.println("Article validé ! ");
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Validation article");
                al.setHeaderText(null);
                al.setContentText("Validation de l'article réussie !");
                al.showAndWait();
            } else {
                System.out.println("Article retiré ! ");
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Validation article");
                al.setHeaderText(null);
                al.setContentText("Retrait de l'article réussi !");
                al.showAndWait();
            }
        } catch (SQLException e) {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Validation Article");
            al.setHeaderText(null);
            al.setContentText("Echec de la mise à jour du statut de cet article\n " + e.getSQLState() + "\nMessage : " + e.getMessage());
            al.showAndWait();
        }
    }

}
