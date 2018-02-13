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
public class Medecin extends Patient {
    private String login_med;
    private String specilaite;
    private String adresse;
    private String diplome;
    private int rating;

    public Medecin(String login_med, String specilaite, String adresse, String diplome, int rating, String login, String password, String cin_user) {
        super(login, password, cin_user);
        this.login_med = login_med;
        this.specilaite = specilaite;
        this.adresse = adresse;
        this.diplome = diplome;
        this.rating = rating;
    }

    public String getLogin_med() {
        return login_med;
    }

    public void setLogin_med(String login_med) {
        this.login_med = login_med;
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

    @Override
    public String toString() {
        return "Medecin{" + "login=" + login_med + ", specilaite=" + specilaite + ", adresse=" + adresse + ", diplome=" + diplome + ", rating=" + rating + '}';
    }

    
}
