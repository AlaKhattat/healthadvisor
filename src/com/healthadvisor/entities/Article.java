package com.healthadvisor.entities;


public class Article {
    
    private int reference;
    private String nom;
    private String description;
    private String contenu;
    private String idMed;
    private String image;

    public Article(String nom, String description, String contenu, Medecin medecin, String image) {
    
        this.nom=nom;
        this.description=description;
        this.contenu=contenu;
        this.idMed=medecin.getLogin();
        this.image=image;
    }

    public Article() {
    }
    
    
    
    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getIdMed() {
        return idMed;
    }

    public void setIdMed(String idMed) {
        this.idMed = idMed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String toString(){
        return "La reference est : "+reference+"\nLe nom est : "+nom+"\nLa description est : "+description+"\nLe contenu est : "+contenu+"\nL'id du medecin est : "+idMed+"\n";
    }
    
    
}