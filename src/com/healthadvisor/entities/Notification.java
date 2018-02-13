/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import com.healthadvisor.enumeration.StatutNotificationEnum;
import java.util.Date;

/**
 *
 * @author khattout
 */
public class Notification {
    private long id;
    private String message;
    private Date date;
    private Utilisateur user;
    private StatutNotificationEnum statut;

    public Notification(long id, String message, Date date, Utilisateur user, StatutNotificationEnum statut) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.user = user;
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public StatutNotificationEnum getStatut() {
        return statut;
    }

    public void setStatut(StatutNotificationEnum statut) {
        this.statut = statut;
    }
    
    
    
}
