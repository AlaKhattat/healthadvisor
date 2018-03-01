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
  private double taille;
  private double poids;
  private int age;
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
  public InfoSante(float taille, float poids,int age, List<String> allergies, List<String> maladies, String sexe,String login) 
  {
        this.taille = taille;
        this.poids = poids;
        this.allergies = allergies;
        this.maladies = maladies;
        this.sexe = sexe;
        this.age = age;
  }

  public InfoSante(double taille, double poids, String sexe,String login,int age) 
  {
        this.taille = taille;
        this.poids = poids;
        this.age = age;
        this.sexe = sexe;
        allergies = new ArrayList<>();
        maladies = new ArrayList<>();
        this.login = login;
  }

    public double getTaille() 
    {
        return taille;
    }

    public void setTaille(float taille) 
    {
        this.taille = taille;
    }

    public double getPoids() 
    {
        return poids;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPoids(double poids)
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
        return "taille=" + taille + " poids=" + poids + " allergies=" + allergies + " maladies=" + maladies + " sexe=" + sexe+" login="+login+" age="+age;
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
    public double calculIMC(InfoSante info)
    {
        //IMC = poids(kg)/(taille*taille)m
        double IMC = info.poids/((Math.pow(info.taille/100,2)));
        
        return Math.round(IMC*100.0)/100.0;
    }
    public double calculPoidIdeal(InfoSante info)
    {
       //(homme)=H(taille en cm)−100−((H−150)/4)
      //(femme)=H(taille en cm)−100−((H−150)/2,5
       double poidIdeal = 0;
       if("HOMME".equals(info.sexe))
       {
           poidIdeal = (info.taille)-100-((info.taille-150)/4);
       }
       else if("FEMME".equals(info.sexe))
       {
           poidIdeal = (info.taille)-100-((info.taille-150)/2.5);
       }
       return poidIdeal;
    }
    public double calculCalorieMin(InfoSante info)
    {
        // femme  MB = 9,74 x P + 172,9 x T - 4,737 x A + 667,051
        //homme adulte MB = 13,707 x P + 492,3 x T - 6,673 x A + 77,607
        double depCalori = 0;
       if("FEMME".equals(info.sexe))
       {
           depCalori = (9.74 * info.poids)+(172.9*(info.taille/100))-(4.737*info.age)+667.051;
       }
       else if("HOMME".equals(info.sexe))
       {
          depCalori = (13.707 * info.poids)+(492.3*(info.taille/100))-(6.673*info.age)+77.607;
       }
       return depCalori;
    }
    public String interpreterIMC(double IMC)
    {
       String result ="";
      if(IMC < 16.5)
      {
            result ="vous etes en etat de Famine suivez un de nos regime";
      }
      else if(IMC >= 16.5 && IMC < 18.5 )
      {
          result="vous etes en etat de Maigreur suivez un de nos regime";
      }
      else if(IMC >= 18.5 && IMC < 25 )
      {
          result="vous avez une Corpulence normale ";
      }
      else if(IMC >= 25 && IMC < 30 )
      {
          result="vous etes en etat de Surpoids suivez un de nos regimes";
      }
      else if(IMC >= 30 && IMC < 35 )
      {
          result="vous etes en etat d'Obesite moderee suivez un de nos regimes";
      }
      else if(IMC >= 35 && IMC < 40 )
      {
          result="vous etes en etat d'Obesite severe suivez un de nos regime";
      }
      else
      {
          result="vous etes en etat Obesité morbide suivez un de nos regime";      
      }
      return result;
    }
    
}
