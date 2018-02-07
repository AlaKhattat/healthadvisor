/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Medecin;
import com.heathadvisor.service.IGestionMedecin;
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
 * @author khattout
 */
public class GestionMedecin implements IGestionMedecin{
    MyDB database;

    public GestionMedecin() {
        database = MyDB.getInstance();
    }

    @Override
    public void AjouterMedecin(Medecin medecin) {
 try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Insert into medecin (login,specialite,adresse,diplome,rating) "+ " values (?,?,?,?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            preparedStmt.setString(1,medecin.getLogin());
            preparedStmt.setString(2,medecin.getSpecilaite());
            preparedStmt.setString(3,medecin.getAdresse());
            preparedStmt.setString(4,medecin.getDiplome());
            preparedStmt.setInt(5,medecin.getRating());

              preparedStmt.executeUpdate();

            System.out.println("Insertion avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void ModifierMedecin(Medecin medecin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SupprimerMedecinCin(String cin) {
 try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from medein where cin='"+cin+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Medecin> ListMedecin() {
        ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from medecin" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;    }

    @Override
    public Medecin AfficherMedecinCin(String cin) {
        Medecin med =null;
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from medecin where cin='"+cin+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            med= new Medecin(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12));
            
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return med;    
    }    

    @Override
    public List<Medecin> AfficherMedecinSpecialite(String specialite) {
    ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from medecin specialite='"+specialite+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;  }

    @Override
    public List<Medecin> AfficherMedecinAdresse(String adresse) {
ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from medecin adresse='"+adresse+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9), rs.getString(10), rs.getString(11), rs.getString(12));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;     }
    
}
