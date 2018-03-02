
package com.healthadvisor.javafx.article;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class ListeArticleFXMain extends Application {
    
    public static Stage stage;
    
    public void start(Stage primaryStage) {   
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ListeArticleFXML.fxml"));
        try {
            Parent root=loader.load();
            Scene sc=new Scene(root);
            Stage st=new Stage();
            st.setScene(sc);
            st.show();
        } catch (Exception e) {
        }  
    }

    public void startAffich(Stage primaryStage, int ref, String titre, String des, String cont, String idmed, String img, double note, String tag) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LireArticleFXML.fxml"));
        try {
            Parent root = loader.load();
            LireArticleFXMLController lire=loader.getController();
            lire.setId(ref);
            lire.setTitre(titre);
            lire.setDesc(des);
            lire.setCont(cont);
            lire.setIdmed(idmed);
            lire.setRating(note);
            lire.setTagsL(tag);
            lire.setImg(img);
            lire.setUrl(img); 
            lire.getBack().setVisible(false);
            Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
        } catch (IOException i) {
        }
    }
        
    public static void main(String[] args) {
        launch(args);
    }

    
}
