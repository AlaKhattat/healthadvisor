/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public class UserRegime 
{
  private String id_user;
  private String id_regime;
  private List<String> id_sport;
  private Date date;
  private int duree;

    public UserRegime() 
    {
       id_user="";
       id_regime="";
       id_sport=new ArrayList<>();
       date = new Date();
       duree=0;
    }
    public UserRegime(String id_user, String id_regime, List<String> id_sport, Date date, int duree) {
        this.id_user = id_user;
        this.id_regime = id_regime;
        this.id_sport = id_sport;
        this.date = date;
        this.duree = duree;
    }
    public String getId_user() 
    {
        return id_user;
    }
    public void setId_user(String id_user)
    {
        this.id_user = id_user;
    }
    public String getId_regime() 
    {
        return id_regime;
    }
    public void setId_regime(String id_regime)
    {
        this.id_regime = id_regime;
    }
    public List<String> getId_sport() 
    {
        return id_sport;
    }
    public void setId_sport(List<String> id_sport)
    {
        this.id_sport = id_sport;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }
    public int getDuree() 
    {
        return duree;
    }
    public void setDuree(int duree) 
    {
        this.duree = duree;
    }
    
  
}
