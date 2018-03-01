/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.bienetre;

import com.healthadvisor.entities.InfoSante;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionInfoSante;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;

import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXMLAjoutIMCViewController implements Initializable {

    @FXML
    private JFXTextField poid;
    @FXML
    private JFXTextField taille;
    @FXML
    private JFXButton calculIMC;
    @FXML
    private JFXComboBox<String> sexe;
    @FXML
    private JFXTextField age;
    public static Stage stage;
    @FXML
    private JFXSpinner spin;
    @FXML
    private DialogPane pane;
    public static Patient patient;
    public static double IMC;
   

    /*public void setEtat(int etat) {
        this.etat = etat;
    }*/
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        TailleValidator t = new TailleValidator();
        t.setMessage("veullez entrée une taille valide en cm");
        this.taille.getValidators().add(t);
        
        KiloValidator poidValider = new KiloValidator();
        poidValider.setMessage("veullez  saisir un poid correct");
        this.poid.getValidators().add(poidValider);

        AgeValidator ageValider = new AgeValidator();
        ageValider.setMessage("veullez  saisir un age correct");
        this.age.getValidators().add(ageValider);

        this.sexe.getItems().add("HOMME");
        this.sexe.getItems().add("FEMME");
        this.sexe.setValue(sexe.getItems().get(0));

        this.spin.setVisible(false);
        IMC = 0;
        
        patient = new Patient(FXMLLoginController.pseudo, " "," ","");
      
        remplirChamp(patient);
    }

    public Patient getPatient() {
        return patient;
    }

    public static void setPatient(Patient patient) {
        FXMLAjoutIMCViewController.patient = patient;
    }

   
    

    @FXML
    public void validerTaille() {

        if (taille.validate() == true) {
            Color c = Color.web("#008000"); //green
            taille.validate();
            taille.focusColorProperty().setValue(c);
            taille.getValidators().get(0).setMessage("");
        } else {
            Color c = Color.web("#FF0000"); //red
            taille.validate();
            taille.focusColorProperty().setValue(c);
            taille.getValidators().get(0).setMessage("veullez entrée une taille valide en cm");
        }

    }

    @FXML
    public void validerPoid() {
        if (this.poid.validate()) {
            Color c = Color.web("#008000"); //green
            poid.validate();
            poid.focusColorProperty().setValue(c);
            poid.getValidators().get(0).setMessage("");
        } else {
            Color c = Color.web("#FF0000"); //red
            poid.validate();
            poid.focusColorProperty().setValue(c);
            poid.getValidators().get(0).setMessage("veullez  saisir un poid correct");
        }
    }

    @FXML
    public void validerAge() {
        if (this.age.validate()) {
            Color c = Color.web("#008000"); //green
            age.validate();
            age.focusColorProperty().setValue(c);
            age.getValidators().get(0).setMessage("");
        } else {
            Color c = Color.web("#FF0000"); //red
            age.validate();
            age.focusColorProperty().setValue(c);
            age.getValidators().get(0).setMessage("veullez  saisir un age correct");
        }
    }

    public void startIMCAJOUT() throws Exception {
        stage = new Stage();
        Parent root = FXMLLoader.load(FXMLAjoutIMCViewController.class.getResource("FXMLAjoutIMCView.fxml"));
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();
      
     
    }
    @FXML
 public void calculerIMC()
 {
      
       
        try
        {
        float tailleL = Float.parseFloat(this.taille.getText());
        float poidL = Float.parseFloat(this.poid.getText());
        int ageL = Integer.parseInt(this.age.getText());
        String sexeL = this.sexe.getValue();
        if(FXMLLoginController.pseudo.equals(""))
        {
           patient.setLogin(FXMLLoginController.pseudo);
        }
        String loginL = patient.getLogin();
        System.out.println("login:"+loginL);
        InfoSante info = new InfoSante(tailleL, poidL, sexeL, loginL, ageL);
        GestionInfoSante ginfo = new GestionInfoSante();
        InfoSante info2 = ginfo.afficherInfoSante(loginL);
              if(info2.getTaille()>0 && info2.getPoids() > 0)
              {
                 if(info2.getPoids() >0)
                 {
                      ginfo.modifierInfoSante(info);   
                 }
              }
              else
              {
                        ginfo.ajouterInfoSante(info); 
                  
              }
        spin.setVisible(true);
        Timer time = new Timer(true);
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                spin.setVisible(false);
                IMC = info.calculIMC(info);
                   Platform.runLater(() -> {
                       FXMLAjoutIMCViewController.stage.close();
                });
                  
            }

        }, 3000);
          
           
        }
        catch(NumberFormatException e)
        {
            System.out.println("erreur :"+e.getMessage());
        }
                
 }

  
public void closeIMCAJOUT()
     {
         stage.close();
     }

    public DialogPane getPane() {
        return pane;
    }

    public void setPane(DialogPane pane) {
        this.pane = pane;
    }
    public void remplirChamp(Patient p)
    {
        GestionInfoSante g = new GestionInfoSante();
        InfoSante info = g.afficherInfoSante(p.getLogin());
        System.out.println("info:"+info);
        if(info.getTaille()>0)
        {
            this.taille.setText(Double.toString(info.getTaille()));
            this.age.setText(Integer.toString(info.getAge()));
            this.poid.setText(Double.toString(info.getPoids()));
            this.sexe.setValue(info.getSexe());
            IMC = info.calculIMC(info);
        }
       
    }
     
        
    
}
