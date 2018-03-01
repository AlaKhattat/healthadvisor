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
  private List<String>ptitDejGras;
  private List<String>dejDense;
  private List<String>gouterSucree;
  private List<String>dinerLeger;
  private List<String>proteines;
  private List<String>calories;
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
     ptitDejGras = Arrays.asList(Type_Aliment.matiereGrasse.toString(),
                                 Type_Aliment.cacao.toString(),
                                 Type_Aliment.pains.toString(),
                                 Type_Aliment.thes.toString());
     dejDense = Arrays.asList(Type_Aliment.oeufs.toString(),
                              Type_Aliment.poissons.toString(),
                              Type_Aliment.viandes.toString(),
                              Type_Aliment.pates.toString(),
                              Type_Aliment.riz.toString(),
                              Type_Aliment.pains.toString(),
                              Type_Aliment.laitier.toString());
     gouterSucree = Arrays.asList(Type_Aliment.fruits.toString(),
                                  Type_Aliment.noix.toString(),
                                  Type_Aliment.jus.toString());
     dinerLeger = Arrays.asList(Type_Aliment.fruitsMer.toString(),
                                Type_Aliment.volailles.toString(),
                                Type_Aliment.legumes.toString(),
                                Type_Aliment.haricot_sec.toString());
     proteines = Arrays.asList(Type_Aliment.cereales.toString(),
                               Type_Aliment.laitier.toString(),
                               Type_Aliment.oeufs.toString(),
                               Type_Aliment.poissons.toString(),
                               Type_Aliment.viandes.toString(),
                               Type_Aliment.volailles.toString(),
                               Type_Aliment.riz.toString()
                              );
     calories = Arrays.asList(Type_Aliment.fruits.toString(),
                              Type_Aliment.cereales.toString(),
                              Type_Aliment.jus.toString(),
                              Type_Aliment.matiereGrasse.toString(),
                              Type_Aliment.legumes.toString(),
                              Type_Aliment.oeufs.toString(),
                              Type_Aliment.poissons.toString()
                              );
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
   public Map<String,List<Aliment>>regimeMicronutrition(Regime re)
   {
     
     Map<String,List<Aliment>> tab = new TreeMap<>();
     Map<Type_Aliment,List<Aliment>> list = grouperParAliment(re);
     for(Map.Entry<Type_Aliment,List<Aliment>>i:list.entrySet())
     {
         if(ptitDejGras.contains(i.getKey().toString()))
         {
           
                 tab.put("petit dejeuner",i.getValue());
             
           
         }
         else if(dejDense.contains(i.getKey().toString()))
         {
             
                 tab.put("dejeuner",i.getValue());
             
         }
         else if(gouterSucree.contains(i.getKey().toString()))
         {
             
              
                 tab.put("gouter",i.getValue());
             
             
         }
         else if(dinerLeger.contains(i.getKey().toString()))
         {
            
                 tab.put("diner",i.getValue());
             
         }
     }
      return tab;
   }
   public Map<String,List<Aliment>>regimeHyperProteine(Regime re)
   {
     
     Map<String,List<Aliment>> tab = new TreeMap<>();
     Map<Type_Aliment,List<Aliment>> list = grouperParAliment(re);
     for(Map.Entry<Type_Aliment,List<Aliment>>i:list.entrySet())
     {
         if(proteines.contains(i.getKey().toString()))
         {
                 tab.put("proteine",i.getValue());  
         }      
     }
      return tab;
   }
   public Map<String,List<Aliment>>regimeHypoCalorique(Regime re)
   {
     
     Map<String,List<Aliment>> tab = new TreeMap<>();
     Map<Type_Aliment,List<Aliment>> list = grouperParAliment(re);
     for(Map.Entry<Type_Aliment,List<Aliment>>i:list.entrySet())
     {
         if(calories.contains(i.getKey().toString()))
         {
                 tab.put("calorie",i.getValue());  
         }      
     }
      return tab;
   }
   
}
