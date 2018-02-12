/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Sport;
import com.heathadvisor.service.IGestionSport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class GestionSport implements IGestionSport
{
    private MyDB database;

    public GestionSport() 
    {
        database = MyDB.getInstance();
    }
    

    @Override
    public boolean ajouterSport(Sport sport) 
    {
     
        try
        {       
          String query="insert into sport values (?,?,?)"; 
          PreparedStatement statement = database.getConnexion().prepareStatement(query);
          statement.setString(1,sport.getNom_sport());
          statement.setString(2,sport.totalTypeRegime());
          statement.setFloat(3,sport.getDepense_energetique());
            System.out.println(" dd"+ statement.executeUpdate());
          System.out.println("ajouter avec succes");
        }
        catch(SQLException exception)
        {
            System.out.println("Echec d'ajout erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        }
      
      return true;   
    }

    @Override
    public boolean supprimerSport(Sport sport)
    {
       try
        {
            String query="delete  from sport where nom_sport=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1, sport.getNom_sport());
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
    public boolean modifierSport(Sport sport)
    {
        try
        {
            String query="update sport set type=?,depense_energetique=?  where nom_sport=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1, sport.totalTypeRegime());
            statement.setFloat(2,sport.getDepense_energetique());
            statement.setString(3,sport.getNom_sport());
            System.out.println(statement.executeUpdate());
            System.out.println("modifier avec succes");
        }
        catch(SQLException exception)
        {
            System.out.println("Echec d'ajout erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
       
        }
        return true;
    }
 
    @Override
    public List<Sport> afficherSport() 
    {
          ArrayList<Sport> sports = new ArrayList<>();
        try
        {
            String query="select * from sport";
            Statement statement = database.getConnexion().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next())
            {
              Sport s = new Sport(result.getString("nom_sport"),result.getFloat("depense_energetique"),Sport.explodeSport(result.getString("type")));
              sports.add(s);
            }
            
        }
        catch(SQLException exception)
        {
              System.out.println("Echec d'affichage erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        }
        return sports;
    }
    
    @Override
    public Sport rechercherSport(Sport sport) 
    { 
          Sport s = new Sport();  
          try
          {
            String query="select * from sport where nom_sport=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1, sport.getNom_sport());
            ResultSet result = statement.executeQuery();
            if(result!=null)
            {
                result.next();  
                s= new Sport(result.getString("nom_sport"),result.getFloat("depense_energetique"),Sport.explodeSport(result.getString("type")));
            }
          }
          catch(SQLException exception)
          {
              System.out.println("Echec de recherche erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());           
          }
          return s;
    }
    @Override
    public List<Sport> rechercherSportAvancee(String sport)
    {
        ArrayList<Sport>sports = new ArrayList<>();
       try
       {
           String query ="select * from sport where nom_sport like ? or type like ? or depense_energetique=? ";
           PreparedStatement statement = database.getConnexion().prepareStatement(query);
           statement.setString(1, '%'+sport+'%');          
           try //this will generate an exception if it's impossible to parse a string to float
           {
                statement.setFloat(3,Float.parseFloat(sport));
           }
           catch(NumberFormatException | SQLException ex)//if the parsing is impossible the default value of query parameter will be 0.0
           {      
               System.out.println("erreur impossible de convertir: "+ex.getMessage());
               statement.setFloat(3,0.0f);   
           }
          
           statement.setString(2,'%'+sport+'%');
           ResultSet result = statement.executeQuery();
           while(result.next())
           {
               Sport s= new Sport(result.getString("nom_sport"),result.getFloat("depense_energetique"),Sport.explodeSport(result.getString("type")));
               sports.add(s);
               System.out.println(s);
           }
          
       }
       catch(SQLException exception)
       {
             System.out.println("Echec de recherche erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());  
       }  
    return sports;
    }

    @Override
    public float calculerTotalDepenseEnergetique()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
}
