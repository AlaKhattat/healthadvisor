/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Sport;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IGestionSport 
{
    public boolean ajouterSport(Sport sport);
    public boolean supprimerSport(Sport sport);
    public boolean modifierSport(Sport sport);
    public List<Sport> afficherSport();
    public Sport rechercherSport(Sport sport);
    public List<Sport> rechercherSportAvancee(String sport);
    public float calculerTotalDepenseEnergetique();   
}
