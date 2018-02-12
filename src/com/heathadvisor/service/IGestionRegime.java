/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Regime;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IGestionRegime 
{
    public boolean ajouterRegime(Regime regime);
    public boolean supprimerRegime(Regime regime);
    public List<Regime> afficherRegime();
    public Regime rechercherRegime(Regime regime);
}
