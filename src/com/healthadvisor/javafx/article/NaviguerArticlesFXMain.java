
package com.healthadvisor.javafx.article;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class NaviguerArticlesFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("NaviguerArticlesFXML.fxml"));
        try {
            Parent root=loader.load();
            Scene sc=new Scene(root);
            Stage st=new Stage();
            st.setScene(sc);
            st.show();
        } catch (IOException i) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
