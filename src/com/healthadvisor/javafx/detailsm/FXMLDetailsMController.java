/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.detailsm;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.service.impl.GestionMedecin;
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
public class FXMLDetailsMController implements Initializable {
    GestionPatient gp=new GestionPatient();
    GestionUtilisateur gu=new GestionUtilisateur();
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField date;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXTextField specialite;
    @FXML
    private JFXTextField sexe;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXButton quitter;
    @FXML
    private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
         public void inflateUI(Medecin m) {
          Patient p=gp.AfficherPatientLogin(m.getLogin_med());
          Utilisateur u=gu.AfficherUtilisateurCin(p.getCin_user());
          this.nom.setText(u.getNom());
          this.prenom.setText(u.getPrenom());
          this.cin.setText(u.getCin());
          this.date.setText(u.getDate_naiss().toString());
          this.ville.setText(u.getVille());
          this.specialite.setText(m.getSpecialite());
          this.sexe.setText(u.getSexe());
          this.email.setText(u.getEmail());
         }

    @FXML
    private void quitter(MouseEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
