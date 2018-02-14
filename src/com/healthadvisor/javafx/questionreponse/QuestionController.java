/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Question;
import com.healthadvisor.service.impl.GestionQuestion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<?, ?> consulterID;

    @FXML
    private Button btnID;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        GestionQuestion gq = new GestionQuestion();
        ObservableList<Question> listq = FXCollections.observableArrayList();
            for (Question q : gq.ListQuestion() ){
                listq.add(q);
            }
        questionID.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
        tableID.setItems(listq);
    }    
    
}
