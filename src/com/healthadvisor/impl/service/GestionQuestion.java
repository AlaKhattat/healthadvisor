/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.impl.service;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.heathadvisor.service.IGestionQuestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarek
 */
public class GestionQuestion implements IGestionQuestion{
    
    MyDB myDB;
    
    public GestionQuestion() {
    myDB=  MyDB.getInstance();
    }

    @Override
    public void ajouterQuestion(Question q) {
        try {
            String query = "insert into question (ID,QUESTION,ID_PATIENT) values (?,?,?)";
            PreparedStatement prep= myDB.getConnexion().prepareStatement(query);
            
            prep.setInt(1,q.getId());
            prep.setString(2 , q.getQuestion());
            prep.setString(3, q.getPatient().getLogin());
            prep.executeUpdate();
       
            System.out.println("Question ajoutée.");
        } catch (SQLException ex) {
            System.out.println("Question non ajoutée !");
         }
    }

    @Override
    public void supprimerQuestion(Question q) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("delete from question where ID=?");
            prep.setInt(1 , q.getId());
            prep.executeUpdate();
            System.out.println("Question supprimée.");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateQuestion(int id, String question) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("update question set QUESTION=? where ID=?");
            prep.setString(1 , question);
            prep.setInt(2,id);
            prep.executeUpdate();
            System.out.println("Question mise à jour.");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void afficherQuestion() {
        List<Question> questions = new ArrayList<>();
          
        try {
            Statement stm = myDB.getConnexion().createStatement();
            ResultSet rest= 
                    stm.executeQuery("select * from question");
                 System.out.println("ID | QUESTION | ID_PATIENT");   
            while(rest.next()){
                System.out.println(rest.getInt(1)+" | "+rest.getString(2)+" | "+rest.getString(3));     
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
