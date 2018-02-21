
package com.heathadvisor.service;

import com.healthadvisor.entities.Ligne_Commande;
import java.util.List;


public interface InterfaceLigne_Commande {
    
    public void AjouterLigne_Commande(Ligne_Commande lc);
    public void SupprimerLigne_Commande(String id_produit,int id_commande);
    public void UpdateLigne_Commande(Ligne_Commande lc);
    public Ligne_Commande ConsulterLigne_Commande(String id_produit,int id_commande);
    public List<Ligne_Commande> Liste_LigneCommandes_IDPRoduit(String id_produit);
    public List<Ligne_Commande> Liste_LigneCommandes_IDCommande(int id_commande);
    
}
