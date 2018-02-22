package com.healthadvisor.entities;

import java.sql.Time;
import java.sql.Date;



public class Evenement {
    
    private int id;
    private String nom;
    private Date date;
    private Time heure;
    private String endroit;
    private String type;
    private int nbrMax;
    private String image;

    public Evenement(String nom, Date date, Time heure, String endroit, String type, int nbrMax, String image) {
        this.nom = nom;
        this.date=date;
        this.heure=heure;
        this.endroit = endroit;
        this.type = type;
        this.nbrMax = nbrMax;
        this.image=image;
    }

    public Evenement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public String getEndroit() {
        return endroit;
    }

    public void setEndroit(String endroit) {
        this.endroit = endroit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrMax() {
        return nbrMax;
    }

    public void setNbrMax(int nbrMax) {
        this.nbrMax = nbrMax;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String toString(){
        return "L'id est : "+id+"\nLe nom est : "+nom+"\nLa date est : "+date+"\nL'heure est : "+heure+"\nL'endroit est : "+endroit+"\nLe type est : "+type+"\nLe nombre max est : "+nbrMax;
    }
    
    
    
    
    
}
