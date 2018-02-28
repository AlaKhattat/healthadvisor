/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.Sondage;
import java.util.ArrayList;

/**
 *
 * @author Tarek
 */
public interface IGestionReponsesPossibles {
    
    public void ajouterReponsesPossibles(int id_reponse,String reponse, int id_sondage);
    public void supprimerReponsesPossibles(ReponsesPossibles r);
    public void updateReponsesPossibles(int id_reponse, String reponse);
    public void afficherReponsesPossibles(int id_sondage);
    public ArrayList <ReponsesPossibles> ListReponsesPossibles(int id_sondage);
    
}
