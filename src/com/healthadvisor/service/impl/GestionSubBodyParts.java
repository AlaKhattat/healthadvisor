/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;


import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.BodyParts;
import com.healthadvisor.entities.SubBodyParts;
import com.heathadvisor.service.IGestionBodyParts;
import com.heathadvisor.service.IGestionSubBodyParts;
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
public class GestionSubBodyParts implements IGestionSubBodyParts{
    MyDB database;

    public GestionSubBodyParts() {
        database = MyDB.getInstance();
    }
    @Override
    public ArrayList<SubBodyParts> AfficherSubBodyParts() {
           ArrayList<SubBodyParts> listbp= new ArrayList<>();
           
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from sub_body_parts" ;
            ResultSet rs = stm.executeQuery(sql);
            String chnom="";
            while(rs.next()){
                
                        try {
            Statement stm2 =database.getConnexion().createStatement();
            String sql2="select * from body_parts where ID_BODY_PART="+rs.getObject(3) ;
            ResultSet rs2 = stm2.executeQuery(sql2);
            while(rs2.next()){
                 
                  chnom=(rs2.getString(2));
            }
                        }catch(SQLException e){
                            System.out.println(e);
                        }
                        BodyParts bp=new BodyParts(rs.getInt(3),chnom);
                SubBodyParts sbp= new SubBodyParts(rs.getInt(1),rs.getString(2),bp);
                listbp.add(sbp);
            }
            
            System.out.println("Recuperation avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(IGestionBodyParts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listbp;
    }
    @Override
    public ArrayList<SubBodyParts> AfficherSubBodyParts(int idBodyPart) {
           ArrayList<SubBodyParts> listbp= new ArrayList<>();
           String chnom="";
           try {
            Statement stm2 =database.getConnexion().createStatement();
            String sql2="select * from body_parts where ID_BODY_PART="+idBodyPart ;
            ResultSet rs2 = stm2.executeQuery(sql2);
            while(rs2.next()){
                 
                  chnom=(rs2.getString(2));
            }
                        }catch(SQLException e){
                            System.out.println(e);
                        }
                        BodyParts bp=new BodyParts(idBodyPart,chnom);
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from sub_body_parts where ID_BODY_PART="+idBodyPart ;
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                
                        
                SubBodyParts sbp= new SubBodyParts(rs.getInt(1),rs.getString(2),bp);
                listbp.add(sbp);
            }
            
            System.out.println("Recuperation avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(IGestionBodyParts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listbp;
    }
    
}
