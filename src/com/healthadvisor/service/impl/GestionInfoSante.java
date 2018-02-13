/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.InfoSante;
import com.heathadvisor.service.IGestionInfoSante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class GestionInfoSante implements IGestionInfoSante
{
   
     public MyDB database;

    public GestionInfoSante() 
    {
        database = MyDB.getInstance();
    }
     
    @Override
    public boolean ajouterInfoSante(InfoSante info)
    {
        
         try
        {       
          String query="insert into information_sante values (?,?,?,?,?,?)"; 
          PreparedStatement statement = database.getConnexion().prepareStatement(query);
          statement.setFloat(1, info.getTaille());
          statement.setFloat(2,info.getPoids());
          statement.setString(3,info.totalAllergies());
          statement.setString(4,info.totalMaladies());
          statement.setString(5,info.getLogin());
          statement.setString(6,info.getSexe());
          
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
    public boolean supprimerInfoSante(InfoSante info) 
    {
         try
        {
            String query="delete  from information_sante where login=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1, info.getLogin());
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
    public boolean modifierInfoSante(InfoSante info) 
    {
        //taille	poids	allergies	maladies	login	sexe
        try
        {
            String query="update information_sante set taille=?,poids=?,allergies=?,maladies=?,sexe=? where login=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setFloat(1, info.getTaille());
            statement.setFloat(2,info.getPoids());
            statement.setString(3,info.totalAllergies());
            statement.setString(4,info.totalMaladies());
            statement.setString(5,info.getSexe());
            statement.setString(6,info.getLogin());
            System.out.println(statement.executeUpdate());
            System.out.println("modifier avec succes");
        }
        catch(SQLException exception)
        {
            System.out.println("Echec d'ajout MODIF: state:"+exception.getSQLState()+" message:"+exception.getMessage());
       
        }
        return true;
    }

    @Override
    public InfoSante afficherInfoSante(String login)
    {
        InfoSante a = new InfoSante();
          try
          {
            String query="select * from information_sante where login=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1,login);
            ResultSet result = statement.executeQuery();
            if(result!=null)
            {
                result.next();
                a = new InfoSante(result.getFloat("taille"),result.getFloat("poids"), InfoSante.explodeAliment(result.getString("allergies")),InfoSante.explodeAliment(result.getString("maladies")),result.getString("sexe"),result.getString("login"));
            }
          }
          catch(SQLException exception)
          {
              System.out.println("Echec de recherche erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());           
          }
          return a;    
    }

   
      
}
