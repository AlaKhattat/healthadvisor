/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

/**
 *
 * @author Tarek
 */
public class Reponse {
    private int id;
    private String reponse;
    private Medecin medecin;
    private Question question;


    public Reponse() {
    }
    
    
    public Reponse(int id, String reponse, Medecin medecin, Question question) {
        this.id = id;
        this.reponse = reponse;
        this.medecin = medecin;
        this.question = question;
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

    public Medecin getMedecin() {
        return medecin;
    }

    public Question getQuestion() {
        return question;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", reponse=" + reponse + ", medecin=" + medecin + ", question=" + question + '}';
    }
}
