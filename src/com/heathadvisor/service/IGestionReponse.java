/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;

/**
 *
 * @author Tarek
 */
public interface IGestionReponse {
    public void ajouterReponse(Reponse r);
    public void supprimerReponse(Reponse r);
    public void updateReponse (int id, String reponse);
    public void afficherReponse (); 
}
