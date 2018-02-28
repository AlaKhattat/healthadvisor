/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;

/**
 *
 * @author Tarek
 */
public class Question {
    private int id;
    private String question;
   // private Patient patient;
    private String id_patient;
    private Timestamp date_publication;
    private String specialite;

    public Question(int id, String question, String id_patient, Timestamp date_publication, String specialite) {
        this.id = id;
        this.question = question;
        this.id_patient = id_patient;
        this.date_publication = date_publication;
        this.specialite = specialite;
    }

    


 
    
    public Question() {
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    
    
    
    public Timestamp getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Timestamp date_publication) {
        this.date_publication = date_publication;
    }


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getId_patient() {
        return id_patient;
    }

    public void setId_patient(String id_patient) {
        this.id_patient = id_patient;
    }

       @Override
    public String toString() {
        return this.question; //To change body of generated methods, choose Tools | Templates.
    }

    
}
