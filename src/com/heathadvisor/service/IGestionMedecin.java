/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Medecin;
import java.util.List;

/**
 *
 * @author khattout
 */
public interface IGestionMedecin {
    public void AjouterMedecin(Medecin medecin);
    public void ModifierMedecin(Medecin medecin);
    public boolean SupprimerMedecinLogin(String login);
    public List<Medecin> ListMedecin();
    public Medecin AfficherMedecinLogin(String login);
    public List<Medecin> AfficherMedecinSpecialite(String specialite);
    public List<Medecin> AfficherMedecinAdresse(String adresse);
    public Medecin AficherMedecinNomPrenom(String nomprenom);
    public List<Medecin> AfficherMedecinSnomprenom(String nomprenom);


}
