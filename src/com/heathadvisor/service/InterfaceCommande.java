
package com.heathadvisor.service;


import com.healthadvisor.entities.Commande;
import java.util.List;


public interface InterfaceCommande {
 
    public void AjouterCommande(Commande cmd);
    public void SupprimerCommande(String  ref_commande);
    public void UpdateCommande(Commande cmd);
    public Commande ConsulterCommande(String  ref_commande);
    public List<Commande> ConsulterListe_Commandes();
    
}
