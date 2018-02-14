/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.healthadvisor.entities.Question;
import com.healthadvisor.service.impl.GestionQuestion;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView<Question> tableId;

    @FXML
    private TableColumn<Question, Question> columnId;

    @FXML
    private Button btnId;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<Question> observQuestion = FXCollections.observableArrayList();
        GestionQuestion gq = new GestionQuestion();
        
        
        
    }    
    
}
