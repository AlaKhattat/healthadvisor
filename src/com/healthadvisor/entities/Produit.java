
package com.healthadvisor.entities;

import java.util.Date;
import java.util.Objects;


public class Produit {
    
    private String Reference;
    private String Nom;
    private float prix_vente;
    private String url_image;
    private String type;
    private String id_user;
    private String description;
    private Date date_mise;
    private float promotion;
    private int quantite;
   

    public Produit() {
    }

    public Produit(String Reference, String Nom, float prix_vente, String url_image, String type, String id_user, String description, Date date_mise, float promotion, int quantite) {
        this.Reference = Reference;
        this.Nom = Nom;
        this.prix_vente = prix_vente;
        this.url_image = url_image;
        this.type = type;
        this.id_user = id_user;
        this.description = description;
        this.date_mise = date_mise;
        this.promotion = promotion;
        this.quantite = quantite;
    }

 
 
    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public float getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(float prix_vente) {
        this.prix_vente = prix_vente;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_mise() {
        return date_mise;
    }

    public void setDate_mise(Date date_mise) {
        this.date_mise = date_mise;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "Reference=" + Reference + ", Nom=" + Nom + ", prix_vente=" + prix_vente + ", url_image=" + url_image + ", type=" + type + ", id_user=" + id_user + ", description=" + description + ", date_mise=" + date_mise + ", promotion=" + promotion + ", quantite=" + quantite + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null){
            Produit p=(Produit)obj;
            if(this.getReference().equalsIgnoreCase(p.getReference())){
                return true;
            }
            return false;
        }
        return false;
    }



  

    

   
  
    
    
   
    
    
    
    
    
}
