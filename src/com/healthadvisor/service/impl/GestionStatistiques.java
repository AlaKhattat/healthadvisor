/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.Sondage;
import com.healthadvisor.entities.Statistique;
import com.heathadvisor.service.IGestionStatistiques;
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
public class GestionStatistiques implements IGestionStatistiques{
    
    MyDB myDB;
    
    public GestionStatistiques() {
    myDB=  MyDB.getInstance();
    }
    
    
    @Override
    public int countStat(int id_sondage,int id_reponse) {
        
        
        int res=0;
        
        try {
            Statement stm = myDB.getConnexion().createStatement();
            String sql= "select count(*) as total from user_reponse, reponses_possibles where user_reponse.ID_REPONSE = reponses_possibles.ID_REPONSE and reponses_possibles.ID_SONDAGE ='"+id_sondage+"' and reponses_possibles.ID_REPONSE ='"+id_reponse+"'";
            
            
            ResultSet r = stm.executeQuery(sql);
            
            while(r.next()){
            res = r.getInt("total");
            }
                
                
            } catch (SQLException ex) {
                
            Logger.getLogger(GestionStatistiques.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
        return res;
        
    }

    @Override
    public int countReponsesSondage(int id_sondage) {
        int res=0;
        
        try {
            Statement stm = myDB.getConnexion().createStatement();
            String sql= "select count(*) as total from user_reponse, reponses_possibles where user_reponse.ID_REPONSE = reponses_possibles.ID_REPONSE and reponses_possibles.ID_SONDAGE ='"+id_sondage+"'";
            
            
            ResultSet r = stm.executeQuery(sql);
            
            while(r.next()){
            res = r.getInt("total");
            }
                
                
            } catch (SQLException ex) {
                
            Logger.getLogger(GestionStatistiques.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return res;
    }

    @Override
    public boolean testReponseDeUserSurSondage(String login, int id_sondage) {
        int ressql=0;
        boolean res=false;
        
        try {
            Statement stm = myDB.getConnexion().createStatement();
            String sql= "select count(*) as total from user_reponse, reponses_possibles where user_reponse.ID_USER='"+login+"'and user_reponse.ID_REPONSE=reponses_possibles.ID_REPONSE and reponses_possibles.ID_SONDAGE='"+id_sondage+"'";
            
            
            ResultSet r = stm.executeQuery(sql);
            
            while(r.next()){
            ressql = r.getInt("total");
            
            if (ressql==0){
                res=false;
            }else{
                res=true;
            }
            }
                
                
            } catch (SQLException ex) {
                
            Logger.getLogger(GestionStatistiques.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return res;
    }
    
}
