/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;


import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Symptome;
import com.heathadvisor.service.IGestionSubBodyPartSymptome;
import com.heathadvisor.service.IGestionSymptome;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firassov
 */
public class GestionSubBodyPartSymptome implements IGestionSubBodyPartSymptome{
    MyDB database;

    public GestionSubBodyPartSymptome() {
        database = MyDB.getInstance();
    }
    @Override
    public ArrayList<Symptome> AfficherSubBodySymptome(int id_subbody,String gender) {
        ArrayList<Symptome> listsmpt= new ArrayList<>();
        
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from sub_body_parts_symptome where gender='"+gender+"' and ID_SUB_BODY="+id_subbody;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                GestionSymptome gs=new GestionSymptome();
                Symptome smpt= new Symptome();
                smpt=gs.getSymptome(rs.getInt(3));
                
                listsmpt.add(smpt);
               
            }
            
            System.out.println("Recuperation avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(IGestionSymptome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listsmpt;
    }
    
}
