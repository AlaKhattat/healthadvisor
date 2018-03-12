/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Rendez_Vous;
import java.util.List;

/**
 *
 * @author khattout
 */
public interface IGestionRendezVous {
    
    public void AjouterRendezVous(Rendez_Vous rendezvous);
    public List<Rendez_Vous> ListRendez_Vous();
    public boolean supprimerRendezVous(int id_rendezvous);
    public String RecupererMedecin(String id_medecin);
    public String RecupererPatient(String id_patient);
    public boolean ModifierRendezVous(Rendez_Vous rdv);
    public boolean ModifierRendezVousdate(Rendez_Vous rdv);
    
}
