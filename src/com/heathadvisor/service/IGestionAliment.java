/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Aliment;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IGestionAliment 
{
    public boolean ajouterAliment(Aliment aliment);
    public boolean supprimerAliment(Aliment aliment);
    public boolean modifierAliment(Aliment aliment);
    public List<Aliment> afficherAliment();
    public Aliment rechercherAliment(Aliment aliment);
    public List<Aliment> rechercherAlimentAvancee(Aliment aliment);
    public float calculerTotalValeurEnergetique();
}
