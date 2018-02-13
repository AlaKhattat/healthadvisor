/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.UserReponse;
import com.healthadvisor.service.impl.GestionQuestion;
import com.heathadvisor.service.IGestionUserReponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarek
 */
public class GestionUserReponse implements IGestionUserReponse{
    
    MyDB myDB;
    
    public GestionUserReponse() {
    myDB=  MyDB.getInstance();
    }
    
    @Override
    public void ajouterUserReponse(UserReponse ur) {
        try {
            
            String query = "insert into user_reponse (ID_USER,ID_REPONSE) values (?,?)";
            PreparedStatement prep= myDB.getConnexion().prepareStatement(query);
            
            prep.setString(1,ur.getLogin());
            prep.setInt(2 , ur.getId_reponse());
            prep.executeUpdate();
       
            System.out.println("Réponse utilisateur ajoutée.");
        } catch (SQLException ex) {
            System.out.println(ex);
         }
    }

    @Override
    public void afficherUserReponse() {
        try {
            Statement stm = myDB.getConnexion().createStatement();
            ResultSet r= stm.executeQuery("select * from user_reponse");  
            while(r.next()){
                System.out.println("ID_USER = "+r.getString("id_user")+" | ID_REPONSE = "+r.getInt("id_reponse")); 
                Statement stm2 = myDB.getConnexion().createStatement();
                ResultSet query= stm2.executeQuery("select id_sondage from reponses_possibles where id_reponse="+r.getInt("id_reponse"));  
            while(query.next()){
                System.out.println("ID_SONDAGE = "+query.getInt("id_sondage")); 
            }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
