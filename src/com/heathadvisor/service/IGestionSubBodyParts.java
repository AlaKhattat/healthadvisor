/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;


import com.healthadvisor.entities.SubBodyParts;
import java.util.ArrayList;

/**
 *
 * @author Firassov
 */
public interface IGestionSubBodyParts {
    public ArrayList<SubBodyParts>AfficherSubBodyParts();
    public ArrayList<SubBodyParts>AfficherSubBodyParts(int idBodyPart);
}
