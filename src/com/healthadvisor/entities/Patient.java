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
public class Patient extends Utilisateur{
    
    private String login;
    private String password;

   
    public Patient(String login, String password, String cin, String nom, String prenom, String email, Date date_naiss, String sexe, String pays, String ville) {
        super(cin, nom, prenom, email, date_naiss, sexe, pays, ville);
        this.login = login;
        this.password = password;
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

}
