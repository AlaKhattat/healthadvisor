/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.bienetre;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author asus
 */
public class BIENETRE extends Application {
    
  
    @Override
    public void start(Stage stage) throws Exception {
       	
     Parent root  = FXMLLoader.load(getClass().getClassLoader().getResource("ImcView.fxml"));
      /*  Parent root  = FXMLLoader.load(getClass().getResource("/bienetre/AjoutIMCView.fxml"));
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();*/
        
  /*      JFXDecorator decorator = new JFXDecorator(stage, root);
        decorator.setCustomMaximize(false);
        decorator.setBorder(Border.EMPTY);
        Scene scene = new Scene(decorator);
 
        scene.getStylesheets().add(BIENETRE.class.getResource("styles.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
         
        stage.setScene(scene);
        stage.setIconified(false);
        stage.show();*/
    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }
    
}
