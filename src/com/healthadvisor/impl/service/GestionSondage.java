/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.entities.Sondage;
import com.heathadvisor.service.IGestionSondage;
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
public class GestionSondage implements IGestionSondage{
    
    MyDB myDB;
    
    public GestionSondage() {
    myDB=  MyDB.getInstance();
    }
    
    @Override
    public void ajouterSondage(Sondage s) {
        
        try {
            String query = "insert into sondage (ID_SONDAGE,NOM_SONDAGE) values (?,?)";
            PreparedStatement prep= myDB.getConnexion().prepareStatement(query);
            
            prep.setInt(1,s.getId());
            prep.setString(2 , s.getNom());
            prep.executeUpdate();
       
            System.out.println("Sondage ajouté.");
        } catch (SQLException ex) {
            System.out.println("Sondage non ajouté !");
         }
    
    }

    @Override
    public void supprimerSondage(Sondage s) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("delete from sondage where ID_SONDAGE=?");
            prep.setInt(1 , s.getId());
            prep.executeUpdate();
            System.out.println("Sondage supprimé.");
        } catch (SQLException ex) {
            System.out.println("Sondage non supprimé !");
        }
    }

    @Override
    public void updateSondage(int id, String nom) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("update sondage set NOM_SONDAGE=? where ID_SONDAGE=?");
            prep.setString(1 , nom);
            prep.setInt(2,id);
            prep.executeUpdate();
            System.out.println("Sondage mis à jour.");
        } catch (SQLException ex) {
            System.out.println("Erreur de mise à jour !");
        }
    }

    @Override
    public void afficherSondage() {
        //List<Sondage> sondages = new ArrayList<>();
          
        try {
            Statement stm = myDB.getConnexion().createStatement();
            ResultSet r= stm.executeQuery("select * from sondage");  
            while(r.next()){
                System.out.println("ID_SONDAGE = "+r.getInt(1)+" | NOM = "+r.getString(2));     
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
