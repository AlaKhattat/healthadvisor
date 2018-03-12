/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Symptome;
import java.util.ArrayList;

/**
 *
 * @author Firassov
 */
public interface IGestionSymptome {
    public ArrayList<Symptome> AfficherSymptome();
    public Symptome getSymptome(int idsmp);
}
