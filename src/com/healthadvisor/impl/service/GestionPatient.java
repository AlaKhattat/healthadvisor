/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Patient;
import com.heathadvisor.service.IGestionMedecin;
import com.heathadvisor.service.IGestionPatient;
import com.heathadvisor.service.IGestionUtilisateur;
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
            Statement stm =database.getConnexion().createStatement();
            String sql="Insert into patient (login_p,password,cin_user) "+" values (?,?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            preparedStmt.setString(1,patient.getLogin());
            preparedStmt.setString(2,patient.getPassword());
            preparedStmt.setString(3,patient.getCin_user());
         

            preparedStmt.executeUpdate();

            System.out.println("Insertion avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }       }

    @Override
    public void ModifierPatient(Patient patient) {
        try{
                Statement stm =database.getConnexion().createStatement();

           String sql="UPDATE patient SET login_p="+patient.getLogin()
                   +", password="+patient.getPassword()
                   +" WHERE cin_user="+patient.getCin_user();
            stm.executeUpdate(sql);
           System.out.println("Patient bien modifiÃ©");
           
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }       }

    @Override
    public void SupprimerPatientCin(String cin) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from patient where login_p='"+cin+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }      }

    @Override
    public List<Patient> ListPatient() {
        
    ArrayList<Patient> listp= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from patient" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Patient patient= new Patient(rs.getString("login_p"),rs.getString("password"),rs.getString("cin_user"));
                listp.add(patient);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listp;    }

    @Override
    public Patient AfficherPatientCin(String cin) {
        
    Patient patient =null;
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from patient where login_p='"+cin+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            patient= new Patient(rs.getString("login_p"),rs.getString("password"),rs.getString("cin_user"));
            
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;    }

    
}
