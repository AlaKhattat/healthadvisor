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
    private StatutRendezVousEnum statut_rendezvous;

    public Rendez_Vous(int id, Date date_heure, String patient_id, String medecin_id, StatutRendezVousEnum statut_rendezvous) {
        this.id = id;
        this.date_heure = date_heure;
        this.patient_id = patient_id;
        this.medecin_id = medecin_id;
        this.statut_rendezvous = statut_rendezvous;
    }

    
    
    public StatutRendezVousEnum getStatut_rendezvous() {
        return statut_rendezvous;
    }

    public void setStatut_rendezvous(StatutRendezVousEnum statut_rendezvous) {
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
    
    
    
}
