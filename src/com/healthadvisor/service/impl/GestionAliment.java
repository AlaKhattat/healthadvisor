/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Aliment;
import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.heathadvisor.service.IGestionAliment;

/**
 *
 * @author asus
 */
public class GestionAliment implements IGestionAliment
{
    public MyDB database;

    public GestionAliment() 
    {
        database = MyDB.getInstance();
    }
    
    public GestionAliment(MyDB database) 
    {
        this.database = database;
    }
 
    @Override
    public boolean ajouterAliment(Aliment aliment) 
    {
        
       
        try
        {       
          String query="insert into aliment values (?,?,?,?)"; 
          PreparedStatement statement = database.getConnexion().prepareStatement(query);
          statement.setString(1, aliment.getNom_aliment());
          statement.setFloat(2, aliment.getQuantite());
          statement.setFloat(3,aliment.getValeur_energetique());
          statement.setString(4,aliment.totalTypeAliment());
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
    public boolean supprimerAliment(Aliment aliment) 
    {
        try
        {
            String query="delete  from aliment where nom_aliment=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1, aliment.getNom_aliment());
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
    public boolean modifierAliment(Aliment aliment)
    {
        try
        {
            String query="update aliment set quantite=?,valeur_energetique=?,type_aliment=? where nom_aliment=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setFloat(1, aliment.getQuantite());
            statement.setFloat(2,aliment.getValeur_energetique());
            statement.setString(3,aliment.totalTypeAliment());
            statement.setString(4, aliment.getNom_aliment());
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
    public List<Aliment> afficherAliment() 
    {
        ArrayList<Aliment> aliments = new ArrayList<>();
        try
        {
            String query="select * from aliment";
            Statement statement = database.getConnexion().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next())
            {
              Aliment a = new Aliment(result.getString("nom_aliment"),Aliment.explodeAliment(result.getString("type_aliment")),result.getFloat("valeur_energetique"),result.getFloat("quantite"));
              aliments.add(a);
            }
            
        }
        catch(SQLException exception)
        {
              System.out.println("Echec d'affichage erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());
        }
        return aliments;
    }

    @Override
    public Aliment rechercherAliment(Aliment aliment)
    {
        Aliment a = new Aliment();
          try
          {
            String query="select * from aliment where nom_aliment=?";
            PreparedStatement statement = database.getConnexion().prepareStatement(query);
            statement.setString(1, aliment.getNom_aliment());
            ResultSet result = statement.executeQuery();
            if(result!=null)
            {
                result.next();
                a = new Aliment(result.getString("nom_aliment"),Aliment.explodeAliment(result.getString("type_aliment")),result.getFloat("valeur_energetique"),result.getFloat("quantite"));
            }
          }
          catch(SQLException exception)
          {
              System.out.println("Echec de recherche erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());           
          }
          return a;
    }

    @Override
    public float calculerTotalValeurEnergetique() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Aliment> rechercherAlimentAvancee(Aliment aliment) 
    {
       ArrayList<Aliment>aliments = new ArrayList<>();
       try
       {
           String query ="select * from aliment where nom_aliment like ? or quantite like ? or valeur_energetique like ? or type_aliment like ?";
           PreparedStatement statement = database.getConnexion().prepareStatement(query);
           statement.setString(1, '%'+aliment.getNom_aliment()+'%');          
           statement.setFloat(2, '%'+aliment.getQuantite()+'%');
           statement.setFloat(3,'%'+aliment.getValeur_energetique()+'%');
           statement.setString(4,'%'+aliment.totalTypeAliment()+'%');
           ResultSet result = statement.executeQuery();
           while(result.next())
           {
               Aliment a = new Aliment(result.getString("nom_aliment"),Aliment.explodeAliment(result.getString("type_aliment")),result.getFloat("valeur_energetique"),result.getFloat("quantite"));
               aliments.add(a);
               System.out.println(a);
           }
          
       }
       catch(SQLException exception)
       {
             System.out.println("Echec de recherche erreur: state:"+exception.getSQLState()+" message:"+exception.getMessage());  
       }  
    return aliments;
    }
}
