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
public class Medecin extends Utilisateur {
    private String login;
    private String specilaite;
    private String adresse;
    private String diplome;
    private int rating;


    public Medecin(String login, String specilaite, String adresse, String diplome, int rating, String cin, String nom, String prenom, String email, Date date_naiss, String sexe, String pays, String ville) {
        super(cin, nom, prenom, email, date_naiss, sexe, pays, ville);
        this.login = login;
        this.specilaite = specilaite;
        this.adresse = adresse;
        this.diplome = diplome;
        this.rating = rating;
    }
   

    
   

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSpecilaite() {
        return specilaite;
    }

    public void setSpecilaite(String specilaite) {
        this.specilaite = specilaite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    
}
