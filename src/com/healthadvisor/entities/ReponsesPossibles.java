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
public class ReponsesPossibles {
    private int id_reponse;
    private String reponse;
    private int id_sondage;

    public ReponsesPossibles() {
    }

    
    
    public ReponsesPossibles(int id_reponse, String reponse, int id_sondage) {
        this.id_reponse = id_reponse;
        this.reponse = reponse;
        this.id_sondage = id_sondage;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public int getId_sondage() {
        return id_sondage;
    }

    public String getReponse() {
        return reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setId_sondage(int id_sondage) {
        this.id_sondage = id_sondage;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
    
}
