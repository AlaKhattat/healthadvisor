/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

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
            String sql="Insert into medecin (login_med,specialite,adresse,diplome,rating) "+ " values (?,?,?,?,?)";
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
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void ModifierMedecin(Medecin medecin) {
        System.out.println("Modification Medecin...");
        try{
                Statement stm =database.getConnexion().createStatement();

           String sql="UPDATE medecin SET specialite='"+medecin.getSpecilaite()+"', adresse='"+medecin.getAdresse()+"', diplome='"+medecin.getDiplome()+"', rating='"+medecin.getRating()+"' WHERE login_med='"+medecin.getLogin_med()+"'";
           stm.executeUpdate(sql);
           System.out.println("Medecin bien modifiÃ©");
           
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }    
    }

    @Override
    public void SupprimerMedecinLogin(String login) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from medecin where login_med='"+login+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Medecin> ListMedecin() {
    ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN_MED=patient.LOGIN_P AND patient.CIN_USER=utilisateur.CIN" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString("login_med"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getString("login_p"),rs.getString("password"),rs.getString("rating"));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;    }

    @Override
    public Medecin AfficherMedecinLogin(String login) {
        Medecin med =null;
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN_MED=patient.LOGIN_P AND patient.CIN_USER=utilisateur.CIN AND medecin.LOGIN_MED='"+login+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                 med= new Medecin(rs.getString("login_med"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getString("login_p"),rs.getString("password"),rs.getString("rating"));
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return med;    
    }    

    @Override
    public List<Medecin> AfficherMedecinSpecialite(String specialite) {
    ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN_MED=patient.LOGIN_P AND patient.CIN_USER=utilisateur.CIN AND medecin.SPECIALITE='"+specialite+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString("login_med"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getString("login_p"),rs.getString("password"),rs.getString("rating"));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;  }

    @Override
    public List<Medecin> AfficherMedecinAdresse(String adresse) {
    ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN_MED=patient.LOGIN_P AND patient.CIN_USER=utilisateur.CIN AND medecin.ADRESSE='"+adresse+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString("login_med"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getString("login_p"),rs.getString("password"),rs.getString("rating"));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;     }
    
}
