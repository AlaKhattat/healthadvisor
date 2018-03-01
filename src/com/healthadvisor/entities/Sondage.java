/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import java.util.ArrayList;

/**
 *
 * @author Tarek
 */
public class Sondage {
    private int id;
    private String nom;

    public Sondage(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    
    
    public Sondage() {
    }

    public Sondage(String nom) {
        
        this.nom = nom;
    }
    


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    

    
}
