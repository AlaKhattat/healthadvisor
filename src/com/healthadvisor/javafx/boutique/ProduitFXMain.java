/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.boutique;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ProduitFXMain extends Application {
    
    @Override
   public void start(Stage primaryStage) {
  FXMLLoader loader= new FXMLLoader(getClass().getResource("FXMLProduit.fxml"));
        
        try{
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException ex){
            
        }
       /*FXMLLoader loaderAffichage= new FXMLLoader(getClass().getResource("FXMLGererProduit_Admin.fxml"));
        try{
            
           

            Parent root=loaderAffichage.load();
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            
            
            
            stage.show();
            
        }catch(IOException ex){
            
        }*/
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
