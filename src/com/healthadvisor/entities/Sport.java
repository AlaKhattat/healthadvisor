/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import com.healthadvisor.enumeration.Type_Exercice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author asus
 */
public class Sport 
{
   private String nom_sport;
   private float depense_energetique;
   private List<Type_Exercice> type_exercices;

    public Sport()
    {
        nom_sport ="";
        depense_energetique = 0.0f;
        type_exercices = new ArrayList<>();
    }

    public Sport(String nom_sport, float depense_energetique) 
    {
        this.nom_sport = nom_sport;
        this.depense_energetique = depense_energetique;
    }

    public Sport(String nom_sport, float depense_energetique, List<Type_Exercice> type_exercices) 
    {
        this.nom_sport = nom_sport;
        this.depense_energetique = depense_energetique;
        this.type_exercices = type_exercices;
    }

   
    public String getNom_sport() 
    {
        return nom_sport;
    }

    public void setNom_sport(String nom_sport) 
    {
        this.nom_sport = nom_sport;
    }

    public float getDepense_energetique() 
    {
        return depense_energetique;
    }

    public void setDepense_energetique(float depense_energetique)
    {
        this.depense_energetique = depense_energetique;
    }

    public List<Type_Exercice> getType_exercices() 
    {
        return type_exercices;
    }

    public void setType_exercices(List<Type_Exercice> type_exercices) 
    {
        this.type_exercices = type_exercices;
    }

    @Override
    public String toString() 
    {
        String ch="nom_sport=" + nom_sport + ", depense_energetique=" + depense_energetique+ ", type_exercices=";
        for(int i = 0; i < type_exercices.size(); i++)
        {
            ch+=type_exercices.get(i).toString()+", ";
        }
        return  ch;
    }
   public String totalTypeRegime()
   {
     String ch="";
     for(int i = 0; i < type_exercices.size(); i++)
     {
         ch+=type_exercices.get(i).toString()+" ";
     }
     return ch;
   }
  
  public  static ArrayList<Type_Exercice> explodeSport(String liste_exercice)
  {
     ArrayList<Type_Exercice> exercices = new ArrayList<>();
     String[] list = liste_exercice.split("\\s");
     for(String i:list)
     {
        exercices.add(Type_Exercice.valueOf(i));
     }
     return exercices;
  }
   public static ArrayList<String> explodePipeSport(String liste_exercie)
   {
       ArrayList<String>exercices = new ArrayList<>();
       String[] list = liste_exercie.split("\\|");
       exercices.addAll(Arrays.asList(list));
       return exercices;
   }
}
