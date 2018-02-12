/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author asus
 */
public class InfoSante 
{
  private float taille;
  private float poids;
  private List<String>allergies;
  private List<String>maladies; 
  private String sexe;
  private String login;
  
  public InfoSante() 
  {
        taille = 0;
        poids = 0;
        sexe="";
        login="";
        allergies = new ArrayList<>();
        maladies = new ArrayList<>();
  }
  public InfoSante(float taille, float poids, List<String> allergies, List<String> maladies, String sexe,String login) 
  {
        this.taille = taille;
        this.poids = poids;
        this.allergies = allergies;
        this.maladies = maladies;
        this.sexe = sexe;
  }

  public InfoSante(float taille, float poids, String sexe,String login) 
  {
        this.taille = taille;
        this.poids = poids;
        this.sexe = sexe;
        allergies = new ArrayList<>();
        maladies = new ArrayList<>();
        this.login = login;
  }

    public float getTaille() 
    {
        return taille;
    }

    public void setTaille(float taille) 
    {
        this.taille = taille;
    }

    public float getPoids() 
    {
        return poids;
    }

    public void setPoids(float poids)
    {
        this.poids = poids;
    }

    public List<String> getAllergies() 
    {
        return allergies;
    }

    public void setAllergies(List<String> allergies)
    {
        this.allergies = allergies;
    }

    public List<String> getMaladies()
    {
        return maladies;
    }

    public void setMaladies(List<String> maladies) 
    {
        this.maladies = maladies;
    }

    public String getSexe()
    {
        return sexe;
    }

    public void setSexe(String sexe)
    {
        this.sexe = sexe;
    }

    public String getLogin() 
    {
        return login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
    }
    

    @Override
    public String toString() 
    {
        return "taille=" + taille + " poids=" + poids + " allergies=" + allergies + " maladies=" + maladies + " sexe=" + sexe+" login="+login;
    }
    public String totalMaladies()
    {
        String ch="";
        ch = maladies.stream().map((i) -> i+" ").reduce(ch, String::concat);
        return ch;
    }
    public String totalAllergies()
    {
        String ch="";
        ch = allergies.stream().map((i)->i+" ").reduce(ch,String::concat);
        return ch;
    }
    public  static ArrayList<String> explodeAliment(String liste)
    {
     ArrayList<String> type = new ArrayList<>();
     String[] list = liste.split("\\s");
     type.addAll(Arrays.asList(list));
     return type;
    }
    
    
}
