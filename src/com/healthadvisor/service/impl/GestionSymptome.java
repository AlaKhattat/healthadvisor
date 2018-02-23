/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;


import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Symptome;
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
public class GestionSymptome implements IGestionSymptome{
    MyDB database;

    public GestionSymptome() {
        database = MyDB.getInstance();
    }
    @Override
    public ArrayList<Symptome> AfficherSymptome() {
        ArrayList<Symptome> listsmpt= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from symptome" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Symptome symp= new Symptome(rs.getInt(1),rs.getString(2));
                listsmpt.add(symp);
            }
            
            System.out.println("Recuperation avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(IGestionSymptome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listsmpt;
    }

    @Override
    public Symptome getSymptome(int idsmp) {
        Symptome smpt= new Symptome();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from symptome where ID_SYMPTOME="+idsmp ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                smpt.setId(rs.getInt(1));
                smpt.setNom(rs.getString(2));
               
            }
            
            System.out.println("Recuperation avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(IGestionSymptome.class.getName()).log(Level.SEVERE, null, ex);
        }
        return smpt;
    }
    
}
