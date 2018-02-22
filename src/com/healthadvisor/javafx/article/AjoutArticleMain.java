
package com.healthadvisor.javafx.article;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AjoutArticleMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AjoutArticleFXML.fxml"));
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
