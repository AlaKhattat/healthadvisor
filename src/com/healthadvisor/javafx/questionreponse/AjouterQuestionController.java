/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Question;
import com.healthadvisor.javafx.inscrimedecin.ComboBoxAutoComplete;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionQuestion;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class AjouterQuestionController implements Initializable {

    @FXML
    private TextArea questionID;
    @FXML
    private Label labelID;
    @FXML
    private Button btnID;
    @FXML
    private AnchorPane paneID;
    @FXML
    private Button btnRetour;
    @FXML
    private ComboBox<String> comboBoxID;
    @FXML
    private Label labelCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));
        
        
        
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
        comboBoxID.setItems(sl);
        new AutoCompleteCmb<String>(comboBoxID);
        
        
        
        
        
    }    

    @FXML
    private void partagerQuestion(ActionEvent event) throws IOException {
        //questionMain qm = new questionMain();
        
        if(comboBoxID.getValue()==null){
            Alert alerte = new Alert(AlertType.WARNING);
            alerte.setTitle("Dialogue d'erreur");
            alerte.setHeaderText("Attention !");
            alerte.setContentText("Vous devez choisir une spécialité ...");
            alerte.show();
        }
        else{
        if(questionID.getText().length()<30){
            Alert alerte = new Alert(AlertType.WARNING);
            alerte.setTitle("Dialogue d'erreur");
            alerte.setHeaderText("Attention !");
            alerte.setContentText("Votre question doit avoir au mois 30 caractères...");
            alerte.show();
        }
        else{
        GestionQuestion gq = new GestionQuestion();
        Question q = new Question(0,questionID.getText(),QuestionUserController.patient.getLogin(),new java.sql.Timestamp(new java.util.Date().getTime()),comboBoxID.getValue());
        gq.ajouterQuestion(q);
        
        Alert alerte = new Alert(AlertType.INFORMATION);
        alerte.setTitle("Dialogue d'information");
        alerte.setHeaderText("Succès !");
        alerte.setContentText("Votre question à été partagée avec succès...");
        alerte.show();
        
        ScrollPane a = FXMLLoader.load(getClass().getResource("QuestionUser.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
    }
    }}

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        ScrollPane a = FXMLLoader.load(getClass().getResource("QuestionUser.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
    }
    
}
