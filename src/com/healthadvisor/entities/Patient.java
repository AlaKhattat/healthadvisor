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
public class Patient {
    
    private String login;
    private String password;
    private String cin_user;
    private String nom;
    private String prenom;
    private String sexe;
    private String pays;

public Patient(){}
    public Patient(String login, String password, String cin_user) {
        this.login = login;
        this.password = password;
        this.cin_user = cin_user;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
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
 
   
    public String getCin_user() {
        return cin_user;
    }

    public void setCin_user(String cin_user) {
        this.cin_user = cin_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patient{" + "login=" + login + ", password=" + password + ", cin_user=" + cin_user + '}';
    }
    

    
}
