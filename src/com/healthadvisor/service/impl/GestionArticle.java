package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Article;
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
            PreparedStatement stm = db.getConnexion().prepareStatement("insert into article(reference, nom, description, contenu, id_medecin, url_image) values(?,?,?,?,?,?)");
            stm.setInt(1, a.getReference());
            stm.setString(2, a.getNom());
            stm.setString(3, a.getDescription());
            stm.setString(4, a.getContenu());
            stm.setString(5, a.getIdMed());
            stm.setString(6, a.getImage());
            stm.executeUpdate();
            System.out.println("Ajout effectué ! ");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Ajout Article");
            al.setHeaderText(null);
            al.setContentText("Ajout de l'article '"+a.getNom()+"' réussie !");
            al.showAndWait();
            
        } catch (SQLException e) {
            Alert al;
            al=new Alert(Alert.AlertType.ERROR);
            al.setTitle("Ajout Article");
            al.setHeaderText(null);
            al.setContentText("Echec de l'ajout de l'article '"+a.getNom()+"'\n "+e.getSQLState()+ "\nMessage : " + e.getMessage());
            al.showAndWait();
        }
    }

    public void supprimerArticle(int ref) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("delete from article where reference ="+ref);
            stm.executeUpdate();
            System.out.println("Suppression effectue ! ");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Suppression Article");
            al.setHeaderText(null);
            al.setContentText("Suppression de l'article réussie !");
            al.showAndWait();
        } catch (SQLException e) {
            Alert al;
            al=new Alert(Alert.AlertType.ERROR);
            al.setTitle("Suppression Article");
            al.setHeaderText(null);
            al.setContentText("Echec de la suppression de l'article \nMessage : " + e.getMessage());
            al.showAndWait();
        }

    }

    public void modifierArticle(int ref, String nom, String desc, String cont, String image) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("update article set nom= ?, description=?, contenu=?, image=? where reference=?");
            stm.setString(1, nom);
            stm.setString(2, desc);
            stm.setString(3, cont);
            stm.setString(4, image);
            stm.setInt(5, ref);
            stm.executeUpdate();
            System.out.println("Modification réussie ! ");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Modification article");
            al.setHeaderText(null);
            al.setContentText("Modification de l'article réussie !");
            al.showAndWait();
        } catch (SQLException e) {
            Alert al;
            al=new Alert(Alert.AlertType.ERROR);
            al.setTitle("Modification Article");
            al.setHeaderText(null);
            al.setContentText("Echec de la modification de l'article\n "+e.getSQLState()+ "\nMessage : " + e.getMessage());
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
                listA.add(a);

            }
            System.out.println("Affichage réussi ! ");
        } catch (SQLException e) {
            System.out.println("Erreur affichage : " + e.getSQLState());
        }
        return listA;
    }

    public List<Article> rechercheArticle(String s) {

        List<Article> rech=new ArrayList<>();
        for (Article i : afficherArticle()) {
            String tot=i.getNom()+" "+i.getDescription()+" "+i.getContenu();
            if (tot.indexOf(s)!=-1) {
                rech.add(i);
            }
        }
        return rech;
    }
    
    public Article rechercheRef(int ref){
        Article a=new Article();
        for(Article i : afficherArticle()){
            a=i;
        }
        return a;
    }

}
