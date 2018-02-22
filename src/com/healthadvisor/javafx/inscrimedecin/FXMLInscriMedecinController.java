/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.inscrimedecin;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
public class FXMLInscriMedecinController implements Initializable {

    @FXML
    private JFXTextField diplome;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXComboBox<String> spécialite;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        ObservableList<String> sl=FXCollections.observableArrayList(specialitelist);
        spécialite.setItems(sl);
        new ComboBoxAutoComplete<String>(spécialite);

    }    

    @FXML
    private void validerInscri(MouseEvent event) {
        String login=this.login.getText();
        String password =this.password.getText();
        String specialite=this.spécialite.getValue();
        String adresse=this.adresse.getText();
        String diplome=this.diplome.getText();
         GestionMedecin gm=new GestionMedecin();
         GestionPatient gp= new GestionPatient();
         Patient p=new Patient(login, password,FXMLLoginController.Identifiant);
         gp.AjouterPatient(p);
         Medecin medecin=new Medecin(p.getLogin(), specialite, adresse, diplome,0,login, password, p.getCin_user());
         gm.AjouterMedecin(medecin);
         Label l=new Label("Ajout Avec Succés");
         JFXPopup pop=new JFXPopup(l);
         pop.show(valider,PopupVPosition.TOP, PopupHPosition.LEFT);
                 
         
    }
    
}
