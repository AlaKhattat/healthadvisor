/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import com.healthadvisor.enumeration.Type_Aliment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author asus
 */
public class ProgrammeRegime 
{
  private Regime regime;
  private String nomRegime;
  private List<String> alimentJour;
  private List<String> Sport;
  private Date dateJour;
  
    public ProgrammeRegime(Regime regime) 
    {
        this.regime = regime;
        this.nomRegime = "";
        this.alimentJour = new ArrayList<>();
        this.dateJour = new Date();
    }

    public ProgrammeRegime() 
    {
      regime = new Regime();
      this.nomRegime = "";
      this.alimentJour = new ArrayList<>();
      this.dateJour = new Date();
    }
    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public String getNomRegime() {
        return nomRegime;
    }

    public void setNomRegime(String nomRegime) {
        this.nomRegime = nomRegime;
    }

    public List<String> getAlimentJour() {
        return alimentJour;
    }

    public void setAlimentJour(List<String> alimentJour) {
        this.alimentJour = alimentJour;
    }

    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }

    public List<String> getSport() {
        return Sport;
    }

    public void setSport(List<String> Sport) {
        this.Sport = Sport;
    }
    
    @Override
    public String toString() {
        return "ProgrammeRegime{" + "regime=" + regime + ", nomRegime=" + nomRegime + ", alimentJour=" + alimentJour + ", dateJour=" + dateJour + '}';
    }
    
    public static List<String>ExplodeNomAliment(String listaliment)
    {
        List<String>tab = new ArrayList<>();
        if(listaliment!=null)
        {
          String[] list = listaliment.split("\\s");
          tab.addAll(Arrays.asList(list));   
        }
        
        return tab;
    }
    public String totalNomAliment()
    {
        String ch="";
        ch = this.alimentJour.stream().map((i) -> i+" ").reduce(ch, String::concat);
        return ch;
    }
    
  public Map<Type_Aliment,List<Aliment>> grouperParAliment(Regime regime)
  {
   Map<Type_Aliment,List<Aliment>> aliments = new TreeMap<>();
  if(regime.getAliments()!=null)
  {
   for(Aliment a : regime.getAliments())
   {
       for(Type_Aliment typea : a.getType_aliment())
       {
           if(aliments.containsKey(typea))
           {
               aliments.get(typea).add(a);
           }
           else
           {
               aliments.put(typea,new ArrayList<>());
               aliments.get(typea).add(a);
           }
       }
   }
  }
   return aliments;
  }
   public Map<Aliment,Integer> regimeIntermite()
   {
       //choix heure de debut et fin
       Map<Aliment,Integer> tab=new HashMap<>();  
       return tab;
   }
   public Map<Integer,List<Aliment>> regimeDissocie(Regime re)
   {     
     Map<Integer,List<Aliment>> tab = new TreeMap<>();
     Map<Type_Aliment,List<Aliment>> list = grouperParAliment(re);
     Random random = new Random();
     for(Map.Entry<Type_Aliment,List<Aliment>> j : list.entrySet())
     {
       int rand = random.nextInt(100);
       tab.put(rand, j.getValue());
     }   
     return tab;
   }
}
