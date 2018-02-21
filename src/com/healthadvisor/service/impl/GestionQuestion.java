/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.heathadvisor.service.IGestionQuestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
            String query = "insert into question (QUESTION,ID_PATIENT,date_publication,modification_etat) values (?,?,?,false)";
            PreparedStatement prep= myDB.getConnexion().prepareStatement(query);
            
            
            prep.setString(1 , q.getQuestion());
            prep.setString(2, q.getId_patient());
            prep.setTimestamp(3, q.getDate_publication());
            prep.executeUpdate();
       
            System.out.println("Question ajoutée.");
        } catch (SQLException ex) {
            System.out.println(ex);
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
            System.out.println("Question non supprimée !");
        }
    }
    
    @Override
    public void supprimerQuestion(int id_question) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("delete from question where ID=?");
            prep.setInt(1 , id_question);
            prep.executeUpdate();
            System.out.println("Question supprimée.");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateQuestion(int id, String question) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("update question set QUESTION=?, date_publication=?, modification_etat=? where ID="+id);
            prep.setString(1 , question);
            prep.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setBoolean(3, true);
            prep.executeUpdate();
            System.out.println("Question mise à jour.");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Question afficherQuestion(int id) {
        //List<Question> questions = new ArrayList<>();
         
        try {
            Statement stm = myDB.getConnexion().createStatement();
            ResultSet r= stm.executeQuery("select * from question where ID="+id);  
            while(r.next()){
                Question question = new Question();
                int idQuestion = r.getInt("ID");
                String textQuestion =r.getString("QUESTION");
                String loginPatient = r.getString("ID_PATIENT");
                Timestamp date = r.getTimestamp("date_publication");
                //question.setId(idQuestion);
                GestionPatient gp=new GestionPatient();
                question.setId_patient(loginPatient);
                question.setQuestion(textQuestion);
                question.setDate_publication(date);
                return question;
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Question> ListQuestion() {
        ArrayList<Question> listq= new ArrayList<>();
        try {
            Statement stm =myDB.getConnexion().createStatement();
            String sql="select * from question" ;
            ResultSet r = stm.executeQuery(sql);

            while(r.next()){
                Question question = new Question();
                int idQuestion = r.getInt("ID");
                String textQuestion =r.getString("QUESTION");
                String loginPatient = r.getString("ID_PATIENT");
                Timestamp date = r.getTimestamp("date_publication");
                question.setId(idQuestion);
                question.setQuestion(textQuestion);
                question.setId_patient(loginPatient);
                question.setDate_publication(date);
                listq.add(question);
            }
            
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listq;
    }

    
    
    
}
