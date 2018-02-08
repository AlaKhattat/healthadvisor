/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Rendez_Vous;
import com.heathadvisor.service.IGestionRendezVous;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khattout
 */
public class GestionRendezVous implements IGestionRendezVous{
  MyDB database;

    public GestionRendezVous() {
        database = MyDB.getInstance();
    }
    
    @Override
    public void AjouterRendezVous(Rendez_Vous rendezvous) {
       try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Insert into rendez_vous (date_heure,user_id,med_id) "+" values (?,?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            java.util.Date utilStartDate = rendezvous.getDate_heure();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            preparedStmt.setDate(1,sqlStartDate);
            preparedStmt.setString(2,rendezvous.getPatient_id());
            preparedStmt.setString(3,rendezvous.getMedecin_id());
         

            preparedStmt.executeUpdate();

            System.out.println("Insertion avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }        }

    @Override
    public void supprimerRendezVous(int id_rendezvous) {
 try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from rendez_vous where id='"+id_rendezvous+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionRendezVous.class.getName()).log(Level.SEVERE, null, ex);
        }        }
    
}
