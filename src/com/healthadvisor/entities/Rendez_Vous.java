/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import com.healthadvisor.enumeration.StatutRendezVousEnum;
import java.util.Date;

/**
 *
 * @author khattout
 */
public class Rendez_Vous {
    private int id;
    private Date date_heure;
    private String patient_id;
    private String medecin_id;
    private String statut_rendezvous;
    private String docteur;
    private String patient;
    private Date date_validation;
    private String date_rdv;

    public String getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(String date_rdv) {
        this.date_rdv = date_rdv;
    }


    public String getDocteur() {
        return docteur;
    }

    public void setDocteur(String docteur) {
        this.docteur = docteur;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
    
    public  Rendez_Vous(){}
    
    public Rendez_Vous(int id, Date date_heure, String patient_id, String medecin_id, String statut_rendezvous) {
        this.id = id;
        this.date_heure = date_heure;
        this.patient_id = patient_id;
        this.medecin_id = medecin_id;
        this.statut_rendezvous = statut_rendezvous;
    }
    public Rendez_Vous(int id, Date date_heure, String patient_id, String medecin_id, String statut_rendezvous, Date date_validation) {
        this.id = id;
        this.date_heure = date_heure;
        this.patient_id = patient_id;
        this.medecin_id = medecin_id;
        this.statut_rendezvous = statut_rendezvous;
        this.date_validation = date_validation;
    }

    public Date getDate_validation() {
        return date_validation;
    }

    public void setDate_validation(Date date_validation) {
        this.date_validation = date_validation;
    }

    
    
    public String getStatut_rendezvous() {
        return statut_rendezvous;
    }

    public void setStatut_rendezvous(String statut_rendezvous) {
        this.statut_rendezvous = statut_rendezvous;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(Date date_heure) {
        this.date_heure = date_heure;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getMedecin_id() {
        return medecin_id;
    }

    public void setMedecin_id(String medecin_id) {
        this.medecin_id = medecin_id;
    }

    @Override
    public String toString() {
        return "Rendez_Vous{" + "id=" + id + ", date_heure=" + date_heure + ", patient_id=" + patient_id + ", medecin_id=" + medecin_id + ", statut_rendezvous=" + statut_rendezvous + '}';
    }
    
    
    
}
