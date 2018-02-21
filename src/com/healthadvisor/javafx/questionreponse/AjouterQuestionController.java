/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Question;
import com.healthadvisor.service.impl.GestionQuestion;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));
        
    }    

    @FXML
    private void partagerQuestion(ActionEvent event) throws IOException {
        //questionMain qm = new questionMain();
        GestionQuestion gq = new GestionQuestion();
        Question q = new Question(0,questionID.getText(), QuestionController.patient.getLogin(),new java.sql.Timestamp(new java.util.Date().getTime()));
        gq.ajouterQuestion(q);
        
        Alert alerte = new Alert(AlertType.INFORMATION);
        alerte.setTitle("Dialogue d'information");
        alerte.setHeaderText("Succès !");
        alerte.setContentText("Votre question à été partagée avec succès...");
        alerte.show();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("question.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        questionMain qm = new questionMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("question.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
    }
    
}
