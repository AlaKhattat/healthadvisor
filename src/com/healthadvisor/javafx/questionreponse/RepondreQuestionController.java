/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Reponse;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionReponse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class RepondreQuestionController implements Initializable {

    @FXML
    private TextArea textAreaID;
    @FXML
    private Button btnPartagerReponse;
    @FXML
    private Label labelReponseID;
    @FXML
    private Label labelQuestion;
    @FXML
    private Label Question;
    @FXML
    private AnchorPane paneID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        QuestionController qc = new QuestionController();
        Question.setText(qc.question.toString());
    }    

    @FXML
    private void partagerReponse(ActionEvent event) throws IOException {
        QuestionController qc = new QuestionController();
        GestionReponse gr = new GestionReponse();
        Reponse r = new Reponse(0,textAreaID.getText(),FXMLLoginController.pseudo, qc.question,new java.sql.Timestamp(new java.util.Date().getTime()));
        gr.ajouterReponse(r);
        
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Dialogue d'information");
        alerte.setHeaderText("Succès !");
        alerte.setContentText("Votre réponse à été partagée avec succès...");
        alerte.show();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterQuestion.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
    }
    
}
