/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Aliment;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.ProgrammeRegime;
import com.healthadvisor.entities.Regime;
import com.healthadvisor.entities.Sport;
import com.healthadvisor.entities.UserRegime;
import com.heathadvisor.service.IGestionUserRegime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author asus
 */
public class GestionUserRegime implements IGestionUserRegime
{
    public MyDB database;

    public GestionUserRegime() 
    {
        this.database = MyDB.getInstance();
    }
    
    @Override
    public ProgrammeRegime rechercherUserRegime(Patient p) 
    {                    
         //ID_USER	ID_REGIME	ID_SPORT	DATE_DEBUT	DUREE 	DailyProgrammeDate DailyAliment
       ProgrammeRegime prog = new ProgrammeRegime();
        try
        { 
          System.out.println("login:"+p.getLogin());
          String query="select * from  regime_user  where ID_USER=?"; 
          PreparedStatement statement = database.getConnexion().prepareStatement(query);//adding diet in diet table
          statement.setString(1, p.getLogin());
          ResultSet result = statement.executeQuery(); 
          if(result!=null)
          {
              result.next();             
              prog.setNomRegime(result.getString("id_regime"));
              prog.setAlimentJour(ProgrammeRegime.ExplodeNomAliment(result.getString("DailyAliment")));
              prog.setSport(Sport.explodePipeSport(result.getString("id_sport")));
              prog.setDateJour(result.getDate("DailyProgrammeDate"));
          }
        }
        catch(SQLException exception)
        {
            System.out.println("Echec d'ajout erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        } 
        return prog;
    }

    @Override
    public void ajouterProgrammeRegime(Patient p,ProgrammeRegime programme)
    {
        try
        { 
              
          String query="insert into regime_user(DailyProgrammeDate,DailyAliment) values (?,?) where id_user=? and id_regime=?"; 
          PreparedStatement statement = database.getConnexion().prepareStatement(query);//adding diet in diet table
          java.util.Date date= programme.getDateJour();
          java.sql.Date datedebutsql=new java.sql.Date(date.getTime());
          statement.setDate(1,datedebutsql);
          statement.setString(2,programme.totalNomAliment());
          statement.setString(3,p.getLogin());
          statement.setString(4,programme.getNomRegime());
          System.out.println(" dd"+ statement.executeUpdate());
          System.out.println("ajouter avec succes");
        }
        catch(SQLException exception)
        {
            System.out.println("Echec d'ajout erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        }    
    }
    @Override
    public void modifierProgrammeRegime(Patient p,ProgrammeRegime programme)
    {
        try
        {
          String query="update regime_user set DailyProgrammeDate=?,DailyAliment=?  where id_user=? and id_regime=?"; 
          PreparedStatement statement = database.getConnexion().prepareStatement(query);//adding diet in diet table
          java.util.Date date= programme.getDateJour();
          java.sql.Date datedebutsql=new java.sql.Date(date.getTime());
          statement.setDate(1,datedebutsql);
          statement.setString(2,programme.totalNomAliment());
          statement.setString(3,p.getLogin());
          statement.setString(4,programme.getNomRegime());
          System.out.println(" dd"+ statement.executeUpdate());
          System.out.println("ajouter avec succes");
        }
        catch(SQLException exception)
        {
            System.out.println("Echec de modif erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        }
    }
}
