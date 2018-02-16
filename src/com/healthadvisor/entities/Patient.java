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

    public Patient(String login, String password, String cin_user) {
        this.login = login;
        this.password = password;
        this.cin_user = cin_user;
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
