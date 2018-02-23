/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Firassov
 */
public class Maladie {
    private String nom;
    private double precision;
    private ArrayList<String> listSpécialite;

    public Maladie(String nom, double precision, ArrayList<String> listSpécialite) {
        this.nom = nom;
        this.precision = precision;
        this.listSpécialite = listSpécialite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public ArrayList<String> getListSpécialite() {
        return listSpécialite;
    }

    public void setListSpécialite(ArrayList<String> listSpécialite) {
        this.listSpécialite = listSpécialite;
    }
    
}
