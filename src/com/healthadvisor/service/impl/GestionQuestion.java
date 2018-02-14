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
            System.out.println("Question non supprimée !");
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
            System.out.println("Erreur de mise à jour !");
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
                question.setId(idQuestion);
                GestionPatient gp=new GestionPatient();
                question.setPatient(gp.AfficherPatientCin(loginPatient));
                question.setQuestion(textQuestion);
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
                question.setId(idQuestion);
                question.setQuestion(textQuestion);
                GestionPatient gp = new GestionPatient();
                Patient patient = gp.AfficherPatientCin(loginPatient);
                question.setPatient(patient);
                listq.add(question);
            }
            
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listq;
    }



    
    
    
}
