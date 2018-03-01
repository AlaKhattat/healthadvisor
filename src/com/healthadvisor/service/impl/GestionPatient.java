/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Patient;
import com.heathadvisor.service.IGestionPatient;
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
public class GestionPatient implements IGestionPatient{
 MyDB database;

    public GestionPatient() {
        database = MyDB.getInstance();
    }

    @Override
    public void AjouterPatient(Patient patient) {
        try {
            System.out.println("Ajout...");
            Statement stm =database.getConnexion().createStatement();
            String sql="Insert into patient (login,password,cin_user,photo_profile) "+" values (?,?,?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            preparedStmt.setString(1,patient.getLogin());
            preparedStmt.setString(2,patient.getPassword());
            preparedStmt.setString(3,patient.getCin_user());
            preparedStmt.setString(4,patient.getPhoto_profile());
         

            preparedStmt.executeUpdate();

            System.out.println("Insertion avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPatient.class.getName()).log(Level.SEVERE, null, ex);
        }       }

    @Override
    public void ModifierPatient(Patient patient) {
        try{
           System.out.println("Modification Patient...");
           System.out.println("Recuperation url image"+patient.getPhoto_profile());
PreparedStatement stm=database.getConnexion().prepareStatement("UPDATE patient SET login=?,password=?, photo_profile=? WHERE cin_user=?");
stm.setString(1, patient.getLogin());
stm.setString(2, patient.getPassword());
stm.setString(3, patient.getPhoto_profile());
stm.setString(4, patient.getCin_user());
stm.executeUpdate();
System.out.println("Patient bien modifie");
           
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }       }

    @Override
    public boolean SupprimerPatientLogin(String login) {
        try {
            System.out.println("Suppression...");
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from patient where login='"+login+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
            return true;
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPatient.class.getName()).log(Level.SEVERE, null, ex);
        } 
    return false;}

    @Override
    public List<Patient> ListPatient() {
        
    ArrayList<Patient> listp= new ArrayList<>();
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from patient" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Patient patient= new Patient(rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
                listp.add(patient);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listp;    }

    @Override
    public Patient AfficherPatientLogin(String login) {
        
    Patient patient =null;
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from patient where login='"+login+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                 patient= new Patient(rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
            System.out.println("Le Patient :"+patient);
            System.out.println("Recuperation avec succes");   
            }
           
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;    }

    @Override
    public Patient AfficherPatientCin(String cin) {
        Patient patient =null;
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from patient where cin_user='"+cin+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
            patient= new Patient(rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
            System.out.println("Recuperation avec succes");   
            }
           
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;     }

    @Override
    public List<Patient> AfficherQPatient() {
      
    ArrayList<Patient> listp= new ArrayList<>();
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="select patient.* FROM patient WHERE patient.LOGIN NOT IN (SELECT medecin.LOGIN FROM medecin WHERE patient.LOGIN=medecin.LOGIN)" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Patient patient= new Patient(rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
                listp.add(patient);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listp;    }

    @Override
    public Patient Verif_Connexion(String pseudo, String motpasse) {
        Patient patient =null;
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from patient where login='"+pseudo+"'AND password='"+motpasse+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
            patient= new Patient(rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
            System.out.println("Recuperation avec succes");  
            }
           
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;      }

  
    
}
