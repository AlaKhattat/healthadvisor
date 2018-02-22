/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.inscripatient;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.javafx.routes.Routes;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private void validerPatient(MouseEvent event) throws IOException {
        String login=this.login.getText();
        String password =this.password.getText();
        GestionPatient gp= new GestionPatient();
         Patient p=new Patient(login, password,FXMLLoginController.Identifiant);
         gp.AjouterPatient(p);
         Label l=new Label("Ajout Avec Succés");
         JFXPopup pop=new JFXPopup(l);
         pop.show(valider,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
           FXMLLoader loader=new FXMLLoader(getClass().getResource(Routes.LOGINVIEW)); 
            Parent root=loader.load();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();
    }
    
}
