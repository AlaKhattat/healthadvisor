
package com.heathadvisor.service;


import com.healthadvisor.entities.Commande;
import java.util.List;


public interface InterfaceCommande {
 
    public void AjouterCommande(Commande cmd);
    public void SupprimerCommande(int num_commande);
    public void UpdateCommande(Commande cmd);
    public Commande ConsulterCommande(int num_commande);
    public List<Commande> ConsulterListe_Commandes();
    
}
