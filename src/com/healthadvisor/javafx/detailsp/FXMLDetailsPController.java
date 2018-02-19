/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.detailsp;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javafx.suivierendezvous.AlertMaker;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLDetailsPController implements Initializable {

    @FXML
    private JFXTextField cin;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField date;
    @FXML
    private JFXTextField sexe;
    @FXML
    private JFXButton quitter;

    GestionPatient gp=new GestionPatient();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXTextField ville;
    GestionUtilisateur gu=new GestionUtilisateur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
     public void inflateUI(Patient p) {
         Utilisateur u=gu.AfficherUtilisateurCin(p.getCin_user());
       this.cin.setText(p.getCin_user());
       this.nom.setText(p.getNom()); 
       this.prenom.setText(p.getPrenom());
       this.email.setText(u.getEmail());
       this.date.setText(u.getDate_naiss().toString());
       this.sexe.setText(p.getSexe());
       this.ville.setText(u.getVille());

    }
     
    

    @FXML
    private void Exit(MouseEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
