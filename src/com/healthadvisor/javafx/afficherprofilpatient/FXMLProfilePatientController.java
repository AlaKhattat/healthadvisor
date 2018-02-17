/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.afficherprofilpatient;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLProfilePatientController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField pays;
    @FXML
    private JFXTextField Ville;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> sexe;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton confirmer;
    String[] sexelist={"Homme","Femme"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        confirmer.setOpacity(0);
        ObservableList<String> sl=FXCollections.observableArrayList(sexelist);
        sexe.setItems(sl); 
        GestionUtilisateur gu=new GestionUtilisateur();
        Utilisateur u=gu.AfficherUtilisateurCin("11111111");
        GestionPatient gp=new GestionPatient();
        Patient p=gp.AfficherPatientCin(u.getCin());
        
        sexe.setValue(u.getSexe());
        this.nom.setText(u.getNom());
        this.prenom.setText(u.getPrenom());
        this.email.setText(u.getEmail());
        
       /* Date input = u.getDate_naiss();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();
        this.date.setValue(date);*/
        this.Ville.setText(u.getVille());
        this.pays.setText(u.getPays());
        this.login.setText(p.getLogin());
        this.password.setText(p.getPassword());
    }    

    @FXML
    private void modifierPatient(MouseEvent event) {
        nom.setEditable(true);
        prenom.setEditable(true);
        email.setEditable(true);
        date.setEditable(true);
        sexe.setEditable(true);
        pays.setEditable(true);
        Ville.setEditable(true);       
        login.setEditable(true);
        password.setEditable(true);
        confirmer.setOpacity(1);
    }

    @FXML
    private void confirmerPatient(MouseEvent event) {
 String nom=this.nom.getText();
 String prenom=this.prenom.getText();
 String email=this.email.getText();
 LocalDate localDate =date.getValue();
 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
 Date date = Date.from(instant);
 String sexe=this.sexe.getValue();
 String pays=this.pays.getText();
 String login=this.login.getText();
 String ville=this.Ville.getText();
 String password=this.password.getText();

        GestionUtilisateur gu=new GestionUtilisateur();
        
        Utilisateur u=new Utilisateur("11111111", nom, prenom, email, date, sexe, pays, ville);
        gu.ModifierUtilisateur(u);
        GestionPatient gp=new GestionPatient();
        Patient p=new Patient(login, password, u.getCin());
        gp.ModifierPatient(p);
    
         Label l=new Label("Modification Avec Succés");
         JFXPopup pop=new JFXPopup(l);
         pop.show(confirmer,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
         
         
        this.nom.setEditable(false);
        this.prenom.setEditable(false);
        this.email.setEditable(false);
        this.date.setEditable(false);
        this.sexe.setEditable(false);
        this.pays.setEditable(false);
        this.Ville.setEditable(false);
        this.login.setEditable(false);
        this.password.setEditable(false);
        pop.hide();
        confirmer.setOpacity(0);
    }
    
}
