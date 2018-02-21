/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;
import com.healthadvisor.entities.InfoSante;


/**
 *
 * @author asus
 */
public interface IGestionInfoSante 
{
    public boolean ajouterInfoSante(InfoSante info);
    public boolean supprimerInfoSante(InfoSante info);
    public boolean modifierInfoSante(InfoSante info);
    public InfoSante afficherInfoSante(String login);
    
  
}
