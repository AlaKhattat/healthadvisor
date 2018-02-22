/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;


import com.healthadvisor.entities.Maladie;
import java.util.ArrayList;

/**
 *
 * @author Firassov
 */
public interface IGestionMaladie {
    public ArrayList<Maladie> Diagnostique(String token,int anne,String gender,String Symptomes);
}
