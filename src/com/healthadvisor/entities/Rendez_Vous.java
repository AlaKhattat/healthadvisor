/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

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

    public Rendez_Vous(int id, Date date_heure, String patient_id, String medecin_id) {
        this.id = id;
        this.date_heure = date_heure;
        this.patient_id = patient_id;
        this.medecin_id = medecin_id;
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
