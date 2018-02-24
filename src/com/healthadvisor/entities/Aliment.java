/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import com.healthadvisor.enumeration.Type_Aliment;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author asus
 */

public class Aliment
{
  private String nom_aliment;
  private ArrayList<Type_Aliment>type_aliment;
  private float valeur_energetique; //valeur energitique par calorie
  private float quantite; //quantite du produit soit en g ou en litre
  public Aliment()
  {
   nom_aliment = "";
   type_aliment=new ArrayList<>();
   valeur_energetique = 0;
   quantite = 0;
  }
  public Aliment(String nom_aliment, ArrayList<Type_Aliment> type_aliment, float valeur_energetique, float quantite) 
  {
        this.nom_aliment = nom_aliment;
        this.type_aliment = type_aliment;
        this.valeur_energetique = valeur_energetique;
        this.quantite = quantite;
  }
  public Aliment(String nom_aliment, float valeur_energetique, float quantite) 
  {
        this.nom_aliment = nom_aliment;
        this.valeur_energetique = valeur_energetique;
        this.quantite = quantite;
  }
  public String getNom_aliment()
  {
        return nom_aliment;
  }
  public void setNom_aliment(String nom_aliment)
  {
        this.nom_aliment = nom_aliment;
  }

  public ArrayList<Type_Aliment> getType_aliment()
  {
        return type_aliment;
  }

  public void setType_aliment(ArrayList<Type_Aliment> type_aliment)
  {
        this.type_aliment = type_aliment;
  }
  public float getValeur_energetique() 
  {
        return valeur_energetique;
  }
  public void setValeur_energetique(float valeur_energetique)
  {
        this.valeur_energetique = valeur_energetique;
  }
  public float getQuantite() 
  {
        return quantite;
  }
  public void setQuantite(float quantite)
  {
        this.quantite = quantite;
  }
    @Override
  public String toString()
  {
       String ch="nom_aliment=" + nom_aliment + ", type_aliment=";
       for(int i = 0; i < type_aliment.size(); i++)
       {
           ch+=type_aliment.get(i).toString()+", ";
       }
        ch+="valeur_energetique=" + valeur_energetique + ", quantite=" + quantite;
        return ch ;
  }
  public String totalTypeAliment()
  {
     String ch="";
     for(int i = 0; i < type_aliment.size(); i++)
     {
         ch+=type_aliment.get(i).toString()+" ";
     }
     return ch;
  }
  public List<String> totalNomAliment(List<Aliment>aliments)
  {
      List<String> ch=new ArrayList<>();
      for(Aliment a: aliments)
      {
          ch.add(a.nom_aliment);
      }
      return ch;
  }
  public  static ArrayList<Type_Aliment> explodeAliment(String listaliment)
  {
     ArrayList<Type_Aliment> type_aliments = new ArrayList<>();
     String[] list = listaliment.split("\\s");
     for(String i:list)
     {
        type_aliments.add(Type_Aliment.valueOf(i));
     }
     return type_aliments;
  }
  
}
