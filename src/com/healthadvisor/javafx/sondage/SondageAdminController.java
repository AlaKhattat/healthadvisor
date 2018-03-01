/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Sondage;
import static com.healthadvisor.javafx.sondage.SondageController.sondage;
import com.healthadvisor.service.impl.GestionSondage;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class SondageAdminController implements Initializable {

    @FXML
    private TableView<Sondage> tableID;
    @FXML
    private TableColumn<Sondage, String> columnID;
    @FXML
    private Button statBtn;
    public static Sondage sondage;
    @FXML
    private Button ajouterBtn;
    @FXML
    private TextField searchBarID;
    @FXML
    private Button btnSupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GestionSondage gs = new GestionSondage();
        ObservableList<Sondage> lists = FXCollections.observableArrayList(gs.ListSondage());
           /* for (Sondage s : gs.ListSondage() ){
                lists.add(s);
            }*/
        columnID.setCellValueFactory(new PropertyValueFactory<Sondage,String>("nom"));
        tableID.setItems(lists);
        
        //SearhBar filters
        FilteredList<Sondage> filteredData = new FilteredList<>(lists, e -> true);
        searchBarID.setOnKeyReleased(e->{
            searchBarID.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Sondage>) s -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (s.getNom().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(s.getNom().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } 
                    return false;
                });
            });
            SortedList<Sondage> sortedQuestion = new SortedList<>(filteredData);
            sortedQuestion.comparatorProperty().bind(tableID.comparatorProperty());
            tableID.setItems(sortedQuestion);
        });
        
    }    

    @FXML
    private void statBtnAction(ActionEvent event) throws IOException {
        SondageController.sondage=tableID.getSelectionModel().getSelectedItem();
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("Stat.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
    }

    @FXML
    private void tableViewEvent(MouseEvent event) {
        sondage = tableID.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void AjouterBtnAction(ActionEvent event) throws IOException {
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("ajouterSondage.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
        
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) throws IOException {
        GestionSondage gs = new GestionSondage();
        gs.supprimerSondage(tableID.getSelectionModel().getSelectedItem());
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("SondageAdmin.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
        
    }
    
}
