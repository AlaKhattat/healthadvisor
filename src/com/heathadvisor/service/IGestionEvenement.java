
package com.heathadvisor.service;

import com.healthadvisor.entities.Evenement;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


public interface IGestionEvenement {
 
    public void ajouterEvenement(Evenement E);
    public void supprimerEvenement(int id);
    public void modifierEvenement(int id, String nom, Date date, Time heure, String endroit, String type, int max, String image);
    public List<Evenement> afficherEvenement();
    
}
