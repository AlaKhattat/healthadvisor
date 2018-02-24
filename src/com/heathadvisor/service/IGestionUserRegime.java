/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.ProgrammeRegime;

/**
 *
 * @author asus
 */
public interface IGestionUserRegime 
{
    public ProgrammeRegime rechercherUserRegime(Patient p);
    public void  ajouterProgrammeRegime(Patient p,ProgrammeRegime programme);
    public void modifierProgrammeRegime(Patient p,ProgrammeRegime programme);
}
