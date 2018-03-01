/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.UserReponse;
import java.util.ArrayList;

/**
 *
 * @author Tarek
 */
public interface IGestionUserReponse {
    
    public void ajouterUserReponse(UserReponse ur);
    public void afficherUserReponse(); 
    public void updateUserReponse(String login, int id_ancienne_reponse, int id_nouvelle_reponse);
    public ArrayList <UserReponse> ListUserReponse();
    public String AfficherUserReponse(String login, int id_sondage);
    public int AfficherIdReponseUser(int id_sondage, String reponse);
    
}
