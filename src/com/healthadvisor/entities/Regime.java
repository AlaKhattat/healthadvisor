/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import com.healthadvisor.enumeration.Type_Regime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author asus
 */
public class Regime 
{
  private String id_regime;
  private List<Aliment> aliments;
  private List<Sport> sports;
  private Type_Regime type_regime;
  private Date date_debut ;
  private Date date_fin;
  private String description;
  private int duree;

    public Regime() 
    {
        this.id_regime = "";
        this.aliments = new ArrayList<>();
        this.sports = new ArrayList<>();
        this.duree = 0;
    }

   
    public Regime(String id_regime, List<Aliment> aliments,Type_Regime type ,Date date_debut, Date date_fin) 
    {
        this.id_regime = id_regime;
        this.aliments = aliments;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type_regime = type;
        
    }
      public Regime(String id_regime, Type_Regime type_regime,int duree,String description ) 
    {
        this.id_regime = id_regime;
        this.description=description;
        this.duree = duree;
        this.type_regime = type_regime;
        
    }
  

    public Regime(String id_regime, List<Aliment> aliments, List<Sport> sports, Type_Regime type_regime, Date date_debut, Date date_fin)
    {
        this.id_regime = id_regime;
        this.aliments = aliments;
        this.sports = sports;
        this.type_regime = type_regime;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    public Regime(String id_regime, Type_Regime type_regime, Date date_debut, Date date_fin,String description) 
    {
        this.id_regime = id_regime;
        this.type_regime = type_regime;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }
    
    public Regime(String id_regime, Date date_debut, Date date_fin) 
    {
        this.id_regime = id_regime;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public String getId_regime() 
    {
        return id_regime;
    }

    public void setId_regime(String id_regime) 
    {
        this.id_regime = id_regime;
    }
   
    public List<Aliment> getAliments() 
    {
        return aliments;
    }

    public void setAliments(List<Aliment> aliments)
    {
        this.aliments = aliments;
    }

    public Date getDate_debut() 
    {
        return date_debut;
    }

    public void setDate_debut(Date date_debut)
    {
        this.date_debut = date_debut;
    }

    public Date getDate_fin()
    {
        return date_fin;
    }

    public void setDate_fin(Date date_fin)
    {
        this.date_fin = date_fin;
    }
   public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
     public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    public boolean verifierFinRegimeDate()
    {
        if(date_fin.compareTo(date_debut)< 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "id_regime=" + id_regime + ", aliments=" + aliments + ", type_regime=" + type_regime + ", date_debut=" + date_debut + ", date_fin=" + date_fin+" ,sports="+sports;
    }

   @Override
    public boolean equals(Object obj) 
    {      
        if(obj!=null && obj instanceof Regime)
        {
            Regime regime = (Regime)obj;
            if(id_regime.equals(regime.id_regime))
            {
              
                return true;
            }
        }
     
        return false;
    }

    @Override
    public int hashCode() {
       
        return 5;
    }
   
   public String totalTypeRegime()
   {
     String ch="";
     for(int i = 0; i < aliments.size(); i++)
     {
         ch+=aliments.get(i).toString()+" ";
     }
     return ch;
   }
   public Type_Regime getType_regime() 
   {
        return type_regime;
   }
   public String regime_type()
   {
       return type_regime.toString();
   }
    public void setType_regime(Type_Regime type_regime) 
    {
        this.type_regime = type_regime;
    }

    public List<Sport> getSports() 
    {
        return sports;
    }
    
    public void setSports(List<Sport> sports) 
    {
        this.sports = sports;
    }
    public String totalTypeSport()
   {
     String ch="";
     for(int i = 0; i < sports.size(); i++)
     {
         ch+=sports.get(i).toString()+" ";
     }
     return ch;
   }
     public String totalDesSport()
   {
       String ch = "";
       for(int i = 0; i < sports.size(); i++)
       {
         ch+=sports.get(i).getNom_sport()+" ";
       }
       return ch;
   }
    public String splitDescription(String ch)
    {
        String total="";
     if(ch!=null)
     {
      String[] list = ch.split("\\s");
      
      int cmpt=0;
      for(String i:list)
      {
          if(cmpt < 30)
          {
               total+=i+" ";
               cmpt+=i.length();
          }
          else
          {
              total+=" \n"+i+" ";
              cmpt = 0;
          }
       
      }
     }
      return total;
    }
   
  
}
