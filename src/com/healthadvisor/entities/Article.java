package com.healthadvisor.entities;


public class Article {
    
    private int reference;
    private String nom;
    private String description;
    private String contenu;
    private String idMed;
    private String image;
    private double note;
    private String tags;
    private String valid;

    public Article(String nom, String description, String tags, String contenu, Medecin medecin, String image) {
    
        this.nom=nom;
        this.description=description;
        this.tags=tags;
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

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
    
    public String toString(){
        return "La reference est : "+reference+"\nLe nom est : "+nom+"\nLa description est : "+description+"\nLes tags sont : "+tags+"\nLe contenu est : "+contenu+"\nL'id du medecin est : "+idMed+"\nLa moyenne des notes est"+note+"\nValidation de l'article"+valid;
    }
    
    
}