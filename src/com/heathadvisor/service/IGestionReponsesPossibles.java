/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.Sondage;

/**
 *
 * @author Tarek
 */
public interface IGestionReponsesPossibles {
    
    public void ajouterReponsesPossibles(ReponsesPossibles r);
    public void supprimerReponsesPossibles(ReponsesPossibles r);
    public void updateReponsesPossibles(int id_reponse, String reponse);
    public void afficherReponsesPossibles(int id_sondage); 
    
}
