/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tarek
 */
public class Question {
    private int id;
    private String question;
    private Patient patient;

    public Question(int id, String question, Patient patient) {
        this.id = id;
        this.question = question;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
}
