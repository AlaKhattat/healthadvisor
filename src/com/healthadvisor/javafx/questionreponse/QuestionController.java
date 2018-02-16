/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.service.impl.GestionQuestion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class QuestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Question> tableID;

    @FXML
    private TableColumn<Question, String> questionID;


    @FXML
    private Button btnID;
    @FXML
    private AnchorPane paneID;
    @FXML
    private TableColumn<Question, String> utilisateurID;
    @FXML
    private Button btnConsulter;
    
    public static Question question; 
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        GestionQuestion gq = new GestionQuestion();
        ObservableList<Question> listq = FXCollections.observableArrayList();
            for (Question q : gq.ListQuestion() ){
                listq.add(q);
                
                utilisateurID.setCellValueFactory(new PropertyValueFactory<Question,String>("id_patient"));
                questionID.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
                Button btnConsulter = new Button("Consulter");
                //consulterID.setCellValueFactory(new PropertyValueFactory<Question,Button>("btnConsulter"));
            }
        tableID.setItems(listq);
        
    }    

    @FXML
    private void btnPoserQuestionAction(ActionEvent event) throws IOException {
        
        questionMain qm = new questionMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterQuestion.fxml"));
        Parent root=loader.load();
        Scene s = tableID.getScene();
        s.setRoot(root);
        
        
        
    }

    @FXML
    private void btnConsulterQuestion(ActionEvent event) throws IOException {
        
        questionMain qm = new questionMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterQuestion.fxml"));
        Parent root=loader.load();
        Scene s = tableID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void tableview(MouseEvent event) {
        question = tableID.getSelectionModel().getSelectedItem();
    }
    
}
