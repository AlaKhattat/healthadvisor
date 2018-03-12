
package com.heathadvisor.service;

import com.healthadvisor.entities.Evenement_Participants;
import java.util.List;


public interface IGestionEvenementParticipants {
 
    public void ajouterEvenementParticipants(Evenement_Participants p);
    public void supprimerEvenementParticipants(Evenement_Participants p);
    public List<Evenement_Participants> afficherEvenementParticipants();
    
}
