/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.analysesymptome;


import com.healthadvisor.entities.Maladie;
import com.healthadvisor.javafx.recherchemedecin.FXMLRechercheMedecinInterfaceController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Firassov
 */
public class ResultatAnalyseFXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ScrollPane scrollpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Maladie>l=SymptomeFXMLController.ResultatAnalyse;
            VBox v=new VBox();
            v.setPrefSize(625,130);
            v.setLayoutX(5);
            v.setLayoutY(25);
        for(Maladie x : l){
           
            HBox h1=new HBox();
            h1.setPadding(new Insets(10, 10, 10, 10));
            h1.setSpacing(5);
            h1.setFillHeight(true);
            h1.setPrefSize(625,200);
            h1.setMaxHeight(200);
            h1.setMinHeight(200);
            Label NomMaladie=new Label(x.getNom());
            NomMaladie.setMaxWidth(150);
            NomMaladie.setMinWidth(150);
            NomMaladie.setWrapText(true);
            h1.getChildren().add(NomMaladie);
            ProgressBar precision=new ProgressBar(x.getPrecision()/100);
            precision.setMaxWidth(150);
            precision.setMinWidth(150);
            h1.getChildren().add(precision);
            ObservableList<String>ob=FXCollections.observableArrayList(x.getListSpécialite());
            ListView<String>listviewSpecialite=new ListView(ob);
            listviewSpecialite.setPrefHeight(150);
            listviewSpecialite.setMaxHeight(150);
            listviewSpecialite.setMinHeight(150);
            listviewSpecialite.setOnMouseClicked((event) -> {
                try {
                    FXMLRechercheMedecinInterfaceController.spec=listviewSpecialite.getSelectionModel().getSelectedItem();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/com/healthadvisor/javafx/affichermedecin/FXMLAfficherMedecin.fxml"));
                    Parent root=loader.load();
                    Scene s = scrollpane.getScene();
                    s.setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(ResultatAnalyseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            h1.getChildren().add(listviewSpecialite);
            v.getChildren().add(h1);
         
        }
        
        scrollpane.setContent(v);
                    //anchor.getChildren().add(s);
                    

    }    
    
}
