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
import java.util.ArrayList;
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

    @Override
    public ArrayList<UserReponse> ListUserReponse() {
        ArrayList<UserReponse> listur = new ArrayList<>();
        
        try {
            Statement stm = myDB.getConnexion().createStatement();
            String sql = "select * from user_reponse";
            ResultSet r = stm.executeQuery(sql);
            
            while(r.next()){
                UserReponse ur = new UserReponse();
                String idUser = r.getString("ID_USER");
                int idReponse = r.getInt("ID_REPONSE");
                ur.setId_reponse(idReponse);
                ur.setLogin(idUser);
                listur.add(ur);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionUserReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listur;
    }

    @Override
    public void updateUserReponse(String login, int id_ancienne_reponse, int id_nouvelle_reponse) {
        
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("update user_reponse set ID_REPONSE=? where ID_REPONSE=? and ID_USER=?");
            prep.setInt(1 ,id_nouvelle_reponse);
            prep.setInt(2, id_ancienne_reponse);
            prep.setString(3, login);
            prep.executeUpdate();
            System.out.println("Réponse mise à jour.");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String AfficherUserReponse(String login, int id_sondage) {
        String rep ="";
        
        try {
            Statement stm = myDB.getConnexion().createStatement();
            String sql = "select reponses_possibles.reponse as reponse from reponses_possibles, user_reponse where user_reponse.ID_REPONSE=reponses_possibles.ID_REPONSE and user_reponse.ID_USER='"+login+"' and reponses_possibles.ID_SONDAGE='"+id_sondage+"'";
            ResultSet r = stm.executeQuery(sql);
            
            while(r.next()){
                rep = r.getString("reponse");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionUserReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println(rep);
            return rep;
        }

    @Override
    public int AfficherIdReponseUser(int id_sondage, String login) {
        int res=0;
        
        try {
            Statement stm = myDB.getConnexion().createStatement();
            String sql= "select user_reponse.ID_REPONSE as r from user_reponse, reponses_possibles where user_reponse.ID_REPONSE=reponses_possibles.ID_REPONSE and user_reponse.ID_USER='"+login+"' and reponses_possibles.ID_SONDAGE='"+id_sondage+"'";
            
            
            ResultSet r = stm.executeQuery(sql);
            
            while(r.next()){
            res = r.getInt("r");
            }
                
                
            } catch (SQLException ex) {
                
            Logger.getLogger(GestionStatistiques.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        System.out.println(res);
        return res;
    }
        
    



    
}
