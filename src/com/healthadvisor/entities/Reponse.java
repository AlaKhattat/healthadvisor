/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Tarek
 */
public class Reponse {
    private int id;
    private String reponse;
    private String id_medecin;
    private Question question;
    private Timestamp date_publication;


    public Reponse() {
    }

    public Reponse(int id,String reponse, String id_medecin, Question question, Timestamp date_publication) {
        this.id=id;
        this.reponse = reponse;
        
        this.id_medecin = id_medecin;
        this.question = question;
        this.date_publication = date_publication;
    }

    public Timestamp getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Timestamp date_publication) {
        this.date_publication = date_publication;
    }

    
    
    public String getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(String id_medecin) {
        this.id_medecin = id_medecin;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }



    public Question getQuestion() {
        return question;
    }

 

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    @Override
    public String toString() {
        return reponse;
    }
}
