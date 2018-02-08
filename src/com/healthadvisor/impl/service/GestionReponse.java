/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;
import com.heathadvisor.service.IGestionReponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarek
 */
public class GestionReponse implements IGestionReponse{
    
    
    MyDB myDB;
    
    public GestionReponse() {
    myDB=  MyDB.getInstance();
    }
    
    
    @Override
    public void ajouterReponse(Reponse r) {
        try {
            String query = "insert into reponse (ID_REPONSE,REPONSE,ID_MEDECIN,ID_QUESTION) values (?,?,?,?)";
            PreparedStatement prep= myDB.getConnexion().prepareStatement(query);
            
            prep.setInt(1,r.getId());
            prep.setString(2 , r.getReponse());
            prep.setString(3, r.getMedecin().getLogin());
            prep.setInt(4, r.getQuestion().getId());
            prep.executeUpdate();
       
            System.out.println("Réponse ajoutée.");
        } catch (SQLException ex) {
            System.out.println("Réponse non ajoutée !");
         }
    }

    @Override
    public void supprimerReponse(Reponse r) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("delete from reponse where ID_REPONSE=?");
            prep.setInt(1 , r.getId());
            prep.executeUpdate();
            System.out.println("Réponse supprimée.");
        } catch (SQLException ex) {
            System.out.println("Réponse non supprimée !");
        }
    }

    @Override
    public void updateReponse(int id, String reponse) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("update reponse set REPONSE=? where ID_REPONSE=?");
            prep.setString(1 , reponse);
            prep.setInt(2,id);
            prep.executeUpdate();
            System.out.println("Réponse mise à jour.");
        } catch (SQLException ex) {
            System.out.println("Erreur de mise à jour !");
        }
    }

    @Override
    public void afficherReponse() {
        List<Reponse> reponses = new ArrayList<>();
          
        try {
            Statement stm = myDB.getConnexion().createStatement();
            ResultSet r= stm.executeQuery("select * from reponse");  
            while(r.next()){
                System.out.println("ID_REPONSE = "+r.getInt(1)+" | REPONSE = "+r.getString(2)+" | ID_MEDECIN = "+r.getString(3)+" | ID_QUESTION = "+r.getInt(4));     
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
