/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.healthadvisor.entities.Aliment;
import com.healthadvisor.entities.Regime;
import com.healthadvisor.entities.Sport;
import com.healthadvisor.enumeration.Type_Regime;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.heathadvisor.service.IGestionRegime;

/**
 *
 * @author asus
 */
public class GestionRegime implements IGestionRegime
{
    public MyDB database;

    public GestionRegime() 
    {
        database = MyDB.getInstance();
    }
    
    @Override
    public boolean ajouterRegime(Regime regime) 
    {
        
      
        try
        {       
          String query="insert into regime values (?,?,?,?)"; 
          String query2="insert into aliment_regime values(?,?)";
          String query3="insert into regime_sport values(?,?)";
          PreparedStatement statement = database.getConnexion().prepareStatement(query);//adding diet in diet table
          statement.setString(1, regime.getId_regime());
          statement.setString(2,regime.regime_type());
          java.util.Date datedebut= regime.getDate_debut();
          java.sql.Date datedebutsql=new java.sql.Date(datedebut.getTime());
           java.util.Date datefin= regime.getDate_debut();
          java.sql.Date datefinsql=new java.sql.Date(datedebut.getTime());
          statement.setDate(3,datedebutsql);
          statement.setDate(4,datefinsql);
          System.out.println(" dd"+ statement.executeUpdate());
          for(int i = 0; i < regime.getAliments().size(); i++) //adding foods in diet_food table
          {
             System.out.println("okay");
             statement = database.getConnexion().prepareStatement(query2);
             statement.setString(1,regime.getAliments().get(i).getNom_aliment());
             statement.setString(2,regime.getId_regime());
             statement.executeUpdate();
          }
            //ID_REGIME	NOM_SPORT regime_sport
          for(int i = 0; i < regime.getSports().size(); i++) //adding sports in diet_sport table
          {
              System.out.println("okay");
              statement = database.getConnexion().prepareStatement(query3);
              statement.setString(1,regime.getId_regime());
              statement.setString(2,regime.getSports().get(i).getNom_sport());
              statement.executeUpdate();
          } 
          System.out.println("ajouter avec succes");
        }
        catch(SQLException exception)
        {
            System.out.println("Echec d'ajout erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        }
      
      return true;   
    }

    @Override
    public boolean supprimerRegime(Regime regime)
    {
        try
        {
            String query="delete from aliment_regime where nom_aliment=? and id_regime=?";
            String query2="delete from regime_sport where id_regime=? and nom_sport=?";
            String query3="delete  from regime where id_regime=?";
            
            PreparedStatement statement = database.getConnexion().prepareStatement(query);//deleting diet from aliment_regime table
            for(int i = 0; i < regime.getAliments().size(); i++)
            {
               statement.setString(1,regime.getAliments().get(i).getNom_aliment());
               statement.setString(2,regime.getId_regime());
               statement.executeUpdate();
            }
            statement = database.getConnexion().prepareStatement(query2);//deleting diet from sport_regime table
            for(int i = 0; i < regime.getSports().size(); i++)
            {
               statement.setString(1,regime.getSports().get(i).getNom_sport());
               statement.setString(2,regime.getId_regime());
               statement.executeUpdate();
            }
            statement = database.getConnexion().prepareStatement(query3);
            statement.setString(1,regime.getId_regime());
            statement.executeUpdate();
            System.out.println("supprimer avec success");
        }
        catch(SQLException exception)
        {
           System.out.println("Echec d'ajout erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());   
        }
        return true;
    }

    @Override
    public List<Regime> afficherRegime() 
    {
        List<Regime> regimes = new ArrayList<>();
        GestionAliment gestion_aliment = new GestionAliment();
        GestionSport gestion_sport = new GestionSport();
         try
        {
            String query="select * from  aliment_regime";
            String query2="select * from regime_sport";
            
            Statement statement = database.getConnexion().createStatement();
            ResultSet result = statement.executeQuery(query);
           
            while(result.next())
            {
              Regime r= new Regime();
              r.setId_regime(result.getString("id_regime"));
                System.out.println("hjjkhjkhj");
              r = rechercherRegime(r);  
              List<Aliment>aliments = gestion_aliment.rechercherAlimentAvancee(result.getString("nom_aliment"));
             
              if(regimes.contains(r)==false)//check if the diet don't exist
              {
                  r.setAliments(aliments);
                  regimes.add(r);
              
              }
              else //if the diet exist food will be add directly to the diet 
              {
                  final Regime regime = r;
                  regimes.forEach((Regime reg) -> {
                      if(reg.equals(regime))
                      {
                          reg.getAliments().addAll(aliments);
                      }
                  });
              }
            }
            /*****************************************************regime_sport*********************************************************/
            
            result = statement.executeQuery(query2);
            while(result.next())
            {
              Regime r= new Regime();
              r.setId_regime(result.getString("id_regime"));
              r = rechercherRegime(r);  
              List<Sport>sports = gestion_sport.rechercherSportAvancee(result.getString("nom_sport"));
              if(regimes.contains(r)==false)//check if the diet don't exist
              {
                
                  r.setSports(sports);
                  regimes.add(r);
              }
              else //if the diet exist sport activities will be add directly to the diet 
              {
                  final Regime regime2 = r;
                  regimes.forEach((Regime reg) -> {
                      if(reg.equals(regime2))
                      {
                          if(reg.getSports()==null)
                          {
                              reg.setSports(sports);
                          }
                          else
                          {
                              reg.getSports().addAll(sports);   
                          }
                          
                      }
                  });
              }
            }
            
        }
        catch(SQLException exception)
        {
              System.out.println("Echec d'affichage erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        }
        return regimes;
    }

    @Override
    public Regime rechercherRegime(Regime regime) 
    {
          Regime r = new Regime();
          try
          {
            String query="select * from regime where id_regime=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1, regime.getId_regime());
            ResultSet result = statement.executeQuery();
            if(result!=null)
            {
                result.next();
                r = new Regime(result.getString("id_regime"),Type_Regime.valueOf(result.getString("type")),result.getInt("duree"),result.getString("description_regime"));
                System.out.println("reg  hgvhbj:"+r);
            }
          }
          catch(SQLException exception)
          {
              System.out.println("Echec de recherche erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());           
          }
          return r; 
    }
         
}
