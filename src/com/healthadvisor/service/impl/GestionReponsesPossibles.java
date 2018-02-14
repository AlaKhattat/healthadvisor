/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.service.impl.GestionQuestion;
import com.heathadvisor.service.IGestionReponsesPossibles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarek
 */
public class GestionReponsesPossibles implements IGestionReponsesPossibles{
    
    MyDB myDB;
    
    public GestionReponsesPossibles() {
    myDB=  MyDB.getInstance();
    }
    
    @Override
    public void ajouterReponsesPossibles(ReponsesPossibles r) {
        try {
            String query = "insert into reponses_possibles (ID_REPONSE,REPONSE,ID_SONDAGE) values (?,?,?)";
            PreparedStatement prep= myDB.getConnexion().prepareStatement(query);
            
            prep.setInt(1,r.getId_reponse());
            prep.setString(2 , r.getReponse());
            prep.setInt(3, r.getId_sondage());
            prep.executeUpdate();
       
            System.out.println("Réponse ajoutée au sondage.");
        } catch (SQLException ex) {
            System.out.println("Réponse non ajoutée au sondage !");
         }
    }

    @Override
    public void supprimerReponsesPossibles(ReponsesPossibles r) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("delete from reponses_possibles where ID_REPONSE=?");
            prep.setInt(1 , r.getId_reponse());
            prep.executeUpdate();
            System.out.println("Réponse supprimé du sondage.");
        } catch (SQLException ex) {
            System.out.println("Réponse non supprimé du sondage !");
        }
    }

    @Override
    public void updateReponsesPossibles(int id_reponse, String reponse) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("update reponses_possibles set REPONSE=? where ID_REPONSE=?");
            prep.setString(1 , reponse);
            prep.setInt(2,id_reponse);
            prep.executeUpdate();
            System.out.println("Réponse mise à jour.");
        } catch (SQLException ex) {
            System.out.println("Erreur de mise à jour !");
        }
    }

    @Override
    public void afficherReponsesPossibles(int id_sondage) {
        //List<ReponsesPossibles> reponsesPossibles = new ArrayList<>();
          
        try {
            Statement stm = myDB.getConnexion().createStatement();
            ResultSet r= stm.executeQuery("select ID_REPONSE,REPONSE from reponses_possibles where ID_SONDAGE=id_sondage");  
            while(r.next()){
                System.out.println("ID_REPONSE = "+r.getInt(1)+" | REPONSE = "+r.getString(2));     
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<ReponsesPossibles> ListReponsesPossibles() {
        ArrayList<ReponsesPossibles> listrp = new ArrayList<>();
        
        
        try {
            Statement stm = myDB.getConnexion().createStatement();
            String sql= "select * from reponses_possibles";
            ResultSet r = stm.executeQuery(sql);
            
            while (r.next()){
                ReponsesPossibles rp = new ReponsesPossibles();
                int id = r.getInt("ID_REPONSE");
                String reponseText = r.getString("REPONSE");
                int idSondage = r.getInt("ID_REPONSE");
                rp.setId_reponse(id);
                rp.setReponse(reponseText);
                rp.setId_sondage(idSondage);
                listrp.add(rp);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionReponsesPossibles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrp;
        
        
    }
    
}
