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
public class Sondage {
    private String nomSondage;

    public Sondage(String nomSondage) {
        this.nomSondage = nomSondage;
    }
    

    public String getNomSondage() {
        return nomSondage;
    }

    public void setNomSondage(String nomSondage) {
        this.nomSondage = nomSondage;
    }
}
