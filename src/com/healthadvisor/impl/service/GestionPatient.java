/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.heathadvisor.service.IGestionMedecin;
import com.heathadvisor.service.IGestionPatient;
import com.heathadvisor.service.IGestionUtilisateur;
import java.sql.Date;
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
            String sql="Insert into patient (patient,password) "+" values (?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            preparedStmt.setString(1,patient.getLogin());
            preparedStmt.setString(2,patient.getPassword());
         

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
                   +" WHERE cin="+patient.getCin();
            stm.executeUpdate(sql);
           System.out.println("Patient bien modifiÃ©");
           
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }       }

    @Override
    public void SupprimerPatientCin(String cin) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from patient where cin='"+cin+"'" ;
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
                Patient patient= new Patient(rs.getString(0),rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9));
                listp.add(patient);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listp;     }

    @Override
    public Patient AfficherPatientCin(String cin) {
    Patient patient =null;
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from patient where cin='"+cin+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            patient= new Patient(rs.getString(0),rs.getString(1), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7), rs.getString(8), rs.getString(9), rs.getString(10));
            
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;    }

    
}
