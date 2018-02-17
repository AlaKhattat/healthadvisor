/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.afficherprofilemed;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javafx.inscrimedecin.ComboBoxAutoComplete;
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
import java.time.ZonedDateTime;
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
public class FXMLProfileMedController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField pays;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> sexe;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField diplome;
    @FXML
    private JFXComboBox<String> specialite;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton confirmer;
    String[] sexelist={"Homme","Femme"};
          String[] specialitelist={
"Anesthésiologie",
"Biochimie médicale",
"Cardiologie",
"Chirurgie cardiaque",
"Chirurgie colorectale",
"Chirurgie générale",
"Chirurgie générale oncologique",
"Chirurgie générale pédiatrique",
"Chirurgie orthopédique",
"Chirurgie plastique",
"Chirurgie thoracique",
"Chirurgie vasculaire",
"Dermatologie",
"Endocrinologie et métabolisme",
"Gastro-entérologie",
"Génétique médicale",
"Gériatrie",
"Gérontopsychiatrie",
"Hématologie",
"Hématologie/oncologie pédiatrique",
"Immunologie clinique et allergie",
"Maladies infectieuses",
"Médecine d'urgence",
"Médecine d'urgence pédiatrique",
"Médecine de l'adolescence",
"Médecine de soins intensifs",
"Médecine du travail",
"Médecine interne",
"Médecine interne générale",
"Médecine maternelle et fœtale",
"Médecine néonatale et périnatale",
"Médecine nucléaire",
"Médecine physique et réadaptation",
"Microbiologie médicale",
"Néphrologie",
"Neurochirurgie",
"Neurologie",
"Neuropathologie",
"Obstétrique et gynécologie",
"Oncologie gynécologique",
"Oncologie médicale",
"Ophtalmologie",
"Pathologie générale",
"Pathologie hématologique",
"Pathologie judiciaire",
"Pédiatrie",
"Pédiatrie du développement",
"Pneumologie",
"Psychiatrie",
"Psychiatrie légale",
"Radio-oncologie",
"Radiologie diagnostique",
"Rhumatologie",
"Santé publique et médecine préventive",
"Urologie"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        confirmer.setOpacity(0);
        ObservableList<String> sl=FXCollections.observableArrayList(sexelist);
        sexe.setItems(sl);
        ObservableList<String> sll=FXCollections.observableArrayList(specialitelist);
        specialite.setItems(sll);
        new ComboBoxAutoComplete<String>(specialite);
        
        GestionUtilisateur gu=new GestionUtilisateur();
        Utilisateur u=gu.AfficherUtilisateurCin("10002563");
        GestionPatient gp=new GestionPatient();
        Patient p=gp.AfficherPatientCin(u.getCin());
        GestionMedecin gm=new GestionMedecin();
        Medecin m= gm.AfficherMedecinLogin(p.getLogin());
        
        sexe.setValue(u.getSexe());
        specialite.setValue(m.getSpecilaite());
        this.nom.setText(u.getNom());
        this.prenom.setText(u.getPrenom());
        this.email.setText(u.getEmail());
        
       /* Date input = u.getDate_naiss();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();
        this.date.setValue(date);*/
        
        this.pays.setText(u.getPays());
        this.ville.setText(u.getVille());
        this.adresse.setText(m.getAdresse());
        this.login.setText(p.getLogin());
        this.password.setText(p.getPassword());
        this.diplome.setText(m.getDiplome());
    }    

    @FXML
    private void modifierMedecin(MouseEvent event) {
        new ComboBoxAutoComplete<String>(specialite);
        nom.setEditable(true);
        prenom.setEditable(true);
        email.setEditable(true);
        date.setEditable(true);
        sexe.setEditable(true);
        pays.setEditable(true);
        ville.setEditable(true);
        specialite.setEditable(true);
        adresse.setEditable(true);
        diplome.setEditable(true);
        login.setEditable(true);
        password.setEditable(true);
        confirmer.setOpacity(1);

        
    }

    @FXML
    private void validerModifMedecin(MouseEvent event) {
 String nom=this.nom.getText();
 String prenom=this.prenom.getText();
 String email=this.email.getText();
 LocalDate localDate =date.getValue();
 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
 Date date = Date.from(instant);
 String sexe=this.sexe.getValue();
 String specialite=this.specialite.getValue();
 String pays=this.pays.getText();
 String ville=this.ville.getText();
 String login=this.login.getText();
 String password=this.password.getText();
 String diplome=this.diplome.getText();
 String adresse=this.adresse.getText();

        GestionUtilisateur gu=new GestionUtilisateur();
        
        Utilisateur u=new Utilisateur("10002563", nom, prenom, email, date, sexe, pays, ville);
        gu.ModifierUtilisateur(u);
        GestionPatient gp=new GestionPatient();
        Patient p=new Patient(login, password, u.getCin());
        gp.ModifierPatient(p);
        GestionMedecin gm=new GestionMedecin();
        Medecin m=new Medecin(login, specialite, adresse, diplome, 0, login, password, u.getCin());
        gm.ModifierMedecin(m);
         Label l=new Label("Modification Avec Succés");
         JFXPopup pop=new JFXPopup(l);
         pop.show(confirmer,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
         
         
        this.nom.setEditable(true);
        this.prenom.setEditable(true);
        this.email.setEditable(true);
        this.date.setEditable(true);
        this.sexe.setEditable(true);
        this.pays.setEditable(true);
        this.ville.setEditable(true);
        this.specialite.setEditable(true);
        this.adresse.setEditable(true);
        this.diplome.setEditable(true);
        this.login.setEditable(true);
        this.password.setEditable(true);
        pop.hide();
        confirmer.setOpacity(0);
    }
    
}
