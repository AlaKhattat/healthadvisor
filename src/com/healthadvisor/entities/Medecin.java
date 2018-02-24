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
    private String specialite;
    private String adresse;
    private String diplome;
    private int rating;
    private String nom;
    private String prenom;
    private String sexe;
    private Double long_p;
    private Double lat_p;

    public Medecin(String login_med, String specialite, String adresse, String diplome, int rating, Double long_p, Double lat_p, String login, String password, String cin_user) {
        super(login, password, cin_user);
        this.login_med = login_med;
        this.specialite = specialite;
        this.adresse = adresse;
        this.diplome = diplome;
        this.rating = rating;
        this.long_p = long_p;
        this.lat_p = lat_p;
    }


public Medecin(){}
    public String getLogin_med() {
        return login_med;
    }

    public void setLogin_med(String login_med) {
        this.login_med = login_med;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
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
        return "Medecin{" + "login=" + login_med + ", specilaite=" + specialite + ", adresse=" + adresse + ", diplome=" + diplome + ", rating=" + rating + '}';
    }

    
}
