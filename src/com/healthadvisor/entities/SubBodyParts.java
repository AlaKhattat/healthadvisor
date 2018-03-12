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
public class SubBodyParts {
    private int id;
    private String Nom;
    private BodyParts bodypart;

    public SubBodyParts(int id, String Nom, BodyParts bodypart) {
        this.id = id;
        this.Nom = Nom;
        this.bodypart = bodypart;
    }

    public SubBodyParts(int id, String Nom) {
        this.id = id;
        this.Nom = Nom;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public BodyParts getBodypart() {
        return bodypart;
    }

    public void setBodypart(BodyParts bodypart) {
        this.bodypart = bodypart;
    }

    @Override
    public String toString() {
        return this.Nom;
    }
    
    
}
