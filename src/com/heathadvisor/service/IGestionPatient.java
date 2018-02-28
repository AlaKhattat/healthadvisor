/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Patient;
import java.util.List;

/**
 *
 * @author khattout
 */
public interface IGestionPatient {
    public void AjouterPatient(Patient patient);
    public void ModifierPatient(Patient patient);
    public boolean SupprimerPatientLogin(String login);
    public List<Patient> ListPatient();
    public Patient AfficherPatientLogin(String login);
    public Patient AfficherPatientCin(String cin);
    public List<Patient> AfficherQPatient();
    

}
