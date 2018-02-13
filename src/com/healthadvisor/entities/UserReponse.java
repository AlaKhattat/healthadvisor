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
public class UserReponse {
    private String login;
    private int id_reponse;

    public UserReponse(String login, int id_reponse) {
        this.login = login;
        this.id_reponse = id_reponse;
    }

    

    public int getId_reponse() {
        return id_reponse;
    }

    public String getLogin() {
        return login;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
}
