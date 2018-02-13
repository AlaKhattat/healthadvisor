/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Question;
import java.util.List;

/**
 *
 * @author Tarek
 */
public interface IGestionQuestion {
    
    public void ajouterQuestion(Question q);
    public void supprimerQuestion(Question q);
    public void updateQuestion (int id, String question);
    public void afficherQuestion (); 
    public List <Question> ListQuestion ();
}
