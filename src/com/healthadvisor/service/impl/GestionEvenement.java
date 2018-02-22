package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Evenement;
import com.heathadvisor.service.IGestionEvenement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import javafx.scene.control.Alert;

public class GestionEvenement implements IGestionEvenement {

    public MyDB db;

    public GestionEvenement() {

        db = MyDB.getInstance();

    }

    @Override
    public void ajouterEvenement(Evenement e) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("insert into evennement(nom, date, location, type, max_participants, url_image, heure) values(?,?,?,?,?,?,?)");
            stm.setString(1, e.getNom());
            stm.setDate(2, e.getDate());
            stm.setString(3, e.getEndroit());
            stm.setString(4, e.getType());
            stm.setInt(5, e.getNbrMax());
            stm.setString(6, e.getImage());
            stm.setTime(7, e.getHeure());
            stm.executeUpdate();
            System.out.println("Ajout effectué ! ");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Ajout evenement");
            al.setHeaderText(null);
            al.setContentText("Ajout de l'evenement '" + e.getNom() + "' réussie !");
            al.showAndWait();
        } catch (SQLException err) {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Ajout événement");
            al.setHeaderText(null);
            al.setContentText("Echec de l'ajout de l'événement '" + e.getNom() + "'\n " + err.getSQLState() + "\nMessage : " + err.getMessage());
            al.showAndWait();
        }
    }

    @Override
    public void supprimerEvenement(int id) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("delete from evennement where id="+id);
            stm.executeUpdate();
            System.out.println("Suppression effectue ! ");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Suppression événement");
            al.setHeaderText(null);
            al.setContentText("Suppression de l'événement réussie !");
            al.showAndWait();
        } catch (SQLException err) {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Suppression Article");
            al.setHeaderText(null);
            al.setContentText("Echec de la suppression de l'événement \n " + err.getSQLState() + "\nMessage : " + err.getMessage());
            al.showAndWait();
        }

    }

    @Override
    public void modifierEvenement(int id, String nom, Date date, Time heure, String endroit, String type, int max, String image) {

        try {
            PreparedStatement stm = db.getConnexion().prepareStatement("update evennement set nom= ?, date=?, location=?, type=?, max_participants=?, url_image=?, heure=? where id=?");
            stm.setString(1, nom);
            stm.setDate(2, date);
            stm.setString(3, endroit);
            stm.setString(4, type);
            stm.setInt(5, max);
            stm.setString(6, image);
            stm.setTime(7, heure);
            stm.setInt(8, id);
            stm.executeUpdate();
            System.out.println("Modification réussie ! ");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Modification événement");
            al.setHeaderText(null);
            al.setContentText("Modification de l'événement réussie !");
            al.showAndWait();
        } catch (SQLException e) {
            Alert al;
            al=new Alert(Alert.AlertType.ERROR);
            al.setTitle("Modification événement");
            al.setHeaderText(null);
            al.setContentText("Echec de la modification de l'événement\n "+e.getSQLState()+ "\nMessage : " + e.getMessage());
            al.showAndWait();
        }

    }

    public List<Evenement> afficherEvenement() {
        List<Evenement> listE = new ArrayList<>();

        try {
            String sql = "select * from evennement";
            Statement stm = db.getConnexion().createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                Evenement e = new Evenement();
                e.setId(res.getInt(1));
                e.setNom(res.getString(2));
                e.setDate(res.getDate(3));
                e.setEndroit(res.getString(4));
                e.setType(res.getString(5));
                e.setNbrMax(res.getInt(6));
                e.setImage(res.getString(7));
                e.setHeure(res.getTime(8));
                listE.add(e);

            }
            System.out.println("Affichage réussi ! ");
        } catch (SQLException err) {
            System.out.println("Erreur affichage : " + err.getSQLState());
        }
        return listE;
    }

    public List<Evenement> rechercheEvenement(String s) {
        List<Evenement> rech = new ArrayList<>();
        for (Evenement i : afficherEvenement()) {
            String tot = i.getNom() + i.getType() + i.getEndroit();
            if (tot.indexOf(s) != -1) {
                rech.add(i);
            }
        }
        return rech;
    }

}
