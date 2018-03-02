/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.service.impl.GestionQuestion;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private AnchorPane paneID;
    @FXML
    private TableColumn<Question, String> utilisateurID;
    @FXML
    private Button btnConsulter;
    
    public static Question question; 
    //public static Patient patient = new Patient("firassov", "pass", "2");
    @FXML
    private TextField searchBarID;
    @FXML
    private TableColumn<Question, Timestamp> dateID;
    @FXML
    private TableColumn<Question, String> specialite;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        GestionQuestion gq = new GestionQuestion();
        ObservableList<Question> listq = FXCollections.observableArrayList();
            for (Question q : gq.ListQuestion() ){
                listq.add(q);
                
                utilisateurID.setCellValueFactory(new PropertyValueFactory<Question,String>("id_patient"));
                questionID.setCellValueFactory(new PropertyValueFactory<Question,String>("question"));
                dateID.setCellValueFactory(new PropertyValueFactory<Question,Timestamp>("date_publication"));
                specialite.setCellValueFactory(new PropertyValueFactory<Question,String>("specialite"));
                //Button btnConsulter = new Button("Consulter");
                //consulterID.setCellValueFactory(new PropertyValueFactory<Question,Button>("btnConsulter"));
            }
        tableID.setItems(listq);
        
        
        //SearhBar filters
        
        FilteredList<Question> filteredData = new FilteredList<>(listq, e -> true);
        searchBarID.setOnKeyReleased(e->{
            try{
            searchBarID.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Question>) q -> {
                   
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (q.getQuestion().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(q.getId_patient().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } 
                    else if(q.getSpecialite().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } 
                    return false;
                });
            });
            SortedList<Question> sortedQuestion = new SortedList<>(filteredData);
            sortedQuestion.comparatorProperty().bind(tableID.comparatorProperty());
            tableID.setItems(sortedQuestion);
            }catch(Exception ex){
                ex.getMessage();
            }
        });
        
        
    }    

    private void btnPoserQuestionAction(ActionEvent event) throws IOException {
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("AjouterQuestion.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
        
        
        
    }

    @FXML
    private void btnConsulterQuestion(ActionEvent event) throws IOException {
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("ConsulterQuestion.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
        
    }

    @FXML
    private void tableview(MouseEvent event) {
        question = tableID.getSelectionModel().getSelectedItem();
    }
    
}
