/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

/**
 *
 * @author Firassov
 */
public class SubBodyPart_Symptome {
    private int id;
    private SubBodyParts subbody;
    private Symptome symptome;
    private String genre;

    public SubBodyPart_Symptome(int id, SubBodyParts subbody, Symptome symptome, String genre) {
        this.id = id;
        this.subbody = subbody;
        this.symptome = symptome;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubBodyParts getSubbody() {
        return subbody;
    }

    public void setSubbody(SubBodyParts subbody) {
        this.subbody = subbody;
    }

    public Symptome getSymptome() {
        return symptome;
    }

    public void setSymptome(Symptome symptome) {
        this.symptome = symptome;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
}
