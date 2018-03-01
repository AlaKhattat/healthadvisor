/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.service.impl.GestionReponse;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class ModifierReponseController implements Initializable {

    @FXML
    private TextArea reponseText;
    @FXML
    private Button modifierReponseBtn;
    @FXML
    private AnchorPane AnchorID;
    @FXML
    private Button retourID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        reponseText.setText(ConsulterQuestionUserController.reponseStatic.getReponse());
        
    }    

    @FXML
    private void modifierReponseBtn(ActionEvent event) throws IOException {
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Dialogue de confirmation");
        alerte.setHeaderText("Attetion ! ");
        alerte.setContentText("Voulez vous vraiment modifier cette réponse ?");
        
        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            
           
            
            GestionReponse gr = new GestionReponse();
            gr.updateReponse(ConsulterQuestionUserController.reponseStatic.getId(), reponseText.getText());
        }else{
            
        }
        
        ScrollPane a = FXMLLoader.load(getClass().getResource("ConsulterQuestionUser.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
        
    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        
        ScrollPane a = FXMLLoader.load(getClass().getResource("ConsulterQuestionUser.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
    }
    
}
