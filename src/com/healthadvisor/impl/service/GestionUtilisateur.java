/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.entities.Utilisateur;
import com.heathadvisor.service.IGestionUtilisateur;
import java.util.List;

/**
 *
 * @author khattout
 */
public class GestionUtilisateur implements IGestionUtilisateur{

    @Override
    public void AjouterUtilisateur(Utilisateur utilisateur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ModifierUtilisateur(Utilisateur utilisateur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SupprimerUtilisateurCin(String cin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> ListUtilisateurs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur AfficherUtilisateurCin(String cin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
