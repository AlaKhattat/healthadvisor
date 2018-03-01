/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.heathadvisor.service.IGestionRendezVous;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khattout
 */
public class GestionRendezVous implements IGestionRendezVous{
  MyDB database;

    public GestionRendezVous() {
        database = MyDB.getInstance();
    }
    
    @Override
    public void AjouterRendezVous(Rendez_Vous rendezvous) {
       try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Insert into rendez_vous (date_heure,user_id,med_id,statut) "+" values (?,?,?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            java.util.Date utilStartDate = rendezvous.getDate_heure();
            Timestamp sqlStartDate = new Timestamp(utilStartDate.getTime());
            preparedStmt.setTimestamp(1,sqlStartDate);
            preparedStmt.setString(2,rendezvous.getPatient_id());
            preparedStmt.setString(3,rendezvous.getMedecin_id());
            preparedStmt.setString(4,rendezvous.getStatut_rendezvous());
     
            preparedStmt.executeUpdate();

            System.out.println("Insertion avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }        }

    @Override
    public boolean supprimerRendezVous(int id_rendezvous) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from rendez_vous where id='"+id_rendezvous+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
            return true;
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }      
    return false;}

    @Override
    public List<Rendez_Vous> ListRendez_Vous() {
    
    ArrayList<Rendez_Vous> listrdv= new ArrayList<>();
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from rendez_vous" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Rendez_Vous rdv= new Rendez_Vous(rs.getInt("id"),rs.getTimestamp("date_heure"),rs.getString("user_id"),rs.getString("med_id"),rs.getString("statut"),rs.getTimestamp("Date_Valid"));
                listrdv.add(rdv);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrdv; 
    }

    @Override
    public String RecupererMedecin(String id_medecin) {
        GestionMedecin gm=new GestionMedecin();
        Medecin medecin=gm.AfficherMedecinLogin(id_medecin);
      GestionPatient gp=new GestionPatient();
        Patient patient=gp.AfficherPatientLogin(medecin.getLogin_med());
        GestionUtilisateur gu =new GestionUtilisateur();
        Utilisateur utilisateur=gu.AfficherUtilisateurCin(patient.getCin_user());
        
        return utilisateur.getNom()+" "+utilisateur.getPrenom();
    }

    @Override
    public String RecupererPatient(String id_patient) {
GestionPatient gp=new GestionPatient();
        Patient patient=gp.AfficherPatientLogin(id_patient);
        GestionUtilisateur gu =new GestionUtilisateur();
        Utilisateur utilisateur=gu.AfficherUtilisateurCin(patient.getCin_user());
        
return utilisateur.getNom()+" "+utilisateur.getPrenom();

            }

    @Override
    public boolean ModifierRendezVous(Rendez_Vous rdv) {
        try{
           System.out.println("Modification RDV...");
           Statement stm =database.getConnexion().createStatement();
           String sql="UPDATE rendez_vous SET statut='"+rdv.getStatut_rendezvous()+"' WHERE id='"+rdv.getId()+"'";
           stm.executeUpdate(sql);
           System.out.println("Rendez_vous bien modifiÃ©");
           return true;
           
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }      
    return false;}

    @Override
    public boolean ModifierRendezVousdate(Rendez_Vous rdv) {
        try{
           System.out.println("Modification RDV...");
           Statement stm =database.getConnexion().createStatement();
           Timestamp t=new Timestamp(rdv.getDate_heure().getTime());
           String sql="UPDATE rendez_vous SET DATE_HEURE='"+t+"' WHERE id='"+rdv.getId()+"'";
           stm.executeUpdate(sql);
           System.out.println("Rendez_vous bien modifiÃ©");
           return true;
           
        }catch(SQLException e){
           System.err.println(e.getMessage());
       }      
            return false;
    }

    @Override
    public List<Rendez_Vous> ListRendez_Vous_Medecin(String id_medecin) {

    ArrayList<Rendez_Vous> listrdv= new ArrayList<>();
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT rendez_vous.* FROM rendez_vous WHERE rendez_vous.MED_ID='"+id_medecin+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Rendez_Vous rdv= new Rendez_Vous(rs.getInt("id"),rs.getTimestamp("date_heure"),rs.getString("user_id"),rs.getString("med_id"),rs.getString("statut"),rs.getTimestamp("Date_Valid"));
                listrdv.add(rdv);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrdv;     }

    @Override
    public int Rendez_Vous_Encours(String id_medecin) {
  int totalEncours=0;
        try {
            System.out.println("Recupération...");
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT COUNT(rendez_vous.ID) AS encours from rendez_vous WHERE rendez_vous.STATUT='"+StatutRendezVousEnum.ENCOURS.name()+"'AND rendez_vous.MED_ID='"+id_medecin+"'";
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
              totalEncours =rs.getInt("encours");
              return totalEncours;
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalEncours;     
    }
}
