/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Utilisateur;
import java.util.List;

/**
 *
 * @author khattout
 */
public interface IGestionUtilisateur {
    
    public void AjouterUtilisateur(Utilisateur utilisateur);
    public void ModifierUtilisateur(Utilisateur utilisateur);
    public void SupprimerUtilisateurCin(String cin);
    public List<Utilisateur> ListUtilisateurs();
    public Utilisateur AfficherUtilisateurCin(String cin);
    
}
