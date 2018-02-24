/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import com.healthadvisor.enumeration.Type_Aliment;
import java.util.ArrayList;
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

    public ProgrammeRegime(Regime regime) {
        this.regime = regime;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public ProgrammeRegime() 
    {
      regime = new Regime();
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
   public Map<Integer,List<Aliment>> microNutrition(Regime re)
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
