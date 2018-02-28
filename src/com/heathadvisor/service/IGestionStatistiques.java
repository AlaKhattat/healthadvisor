/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.Statistique;
import java.util.ArrayList;

/**
 *
 * @author Tarek
 */
public interface IGestionStatistiques {
    public int countStat(int id_sondage, int id_reponse);
    public int countReponsesSondage(int id_sondage);
    public boolean testReponseDeUserSurSondage(String login,int id_sondage);
}
