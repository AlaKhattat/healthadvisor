/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.service.impl.GestionReponse;
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
        
        ConsulterQuestionController cqc = new ConsulterQuestionController();
        reponseText.setText(cqc.reponse.toString());
        
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
            gr.updateReponse(ConsulterQuestionController.reponse.getId(), reponseText.getText());
        }else{
            
        }
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterQuestion.fxml"));
        Parent root=loader.load();
        Scene s = AnchorID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        questionMain qm = new questionMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterQuestion.fxml"));
        Parent root=loader.load();
        Scene s = AnchorID.getScene();
        s.setRoot(root);
    }
    
}
