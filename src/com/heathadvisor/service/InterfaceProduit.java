/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Produit;
import java.util.Date;
import java.util.List;


        
public interface InterfaceProduit {
    
    public void AjouterProduit(Produit p);
    public void SupprimerProduit(String ref);
    public void UpdateProduit(Produit p);
    public Produit ConsulterProduit(String ref);
    public List<Produit> ConsulterListe_Produits();
    public List<Produit> ListeProduits_Promotion(float prom_min,float prom_max);
    public List<Produit> ListProduits_Date(Date date_min,Date date_max);
    public List<Produit> ListProduits_Prix(float prix_min,float prix_max);
    public List<Produit> ListProduits_Categorie(String type);
    public List<Produit> ListProduits_Image(boolean img);
    public List<Produit> ListProduits_User(String id_user);
    
}
