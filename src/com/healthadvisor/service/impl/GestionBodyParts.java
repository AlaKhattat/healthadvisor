/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.BodyParts;
import com.heathadvisor.service.IGestionBodyParts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firassov
 */
public class GestionBodyParts implements IGestionBodyParts{
    MyDB database;

    public GestionBodyParts() {
        database = MyDB.getInstance();
    }
    @Override
    public ArrayList<BodyParts> AfficherBodyParts() {
    ArrayList<BodyParts> listbp= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from body_parts" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                BodyParts bp= new BodyParts(rs.getInt(1),rs.getString(2));
                listbp.add(bp);
            }
            
            System.out.println("Recuperation avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(IGestionBodyParts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listbp;     
    }    
    
}
