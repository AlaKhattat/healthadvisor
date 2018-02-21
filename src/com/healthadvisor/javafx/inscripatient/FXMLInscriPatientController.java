/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.inscripatient;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLInscriPatientController implements Initializable {

    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXButton valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validerPatient(MouseEvent event) {
        String login=this.login.getText();
        String password =this.password.getText();
        GestionPatient gp= new GestionPatient();
         Patient p=new Patient(login, password, "11111111");
         gp.AjouterPatient(p);
         Label l=new Label("Ajout Avec Succés");
         JFXPopup pop=new JFXPopup(l);
         pop.show(valider,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
    }
    
}
