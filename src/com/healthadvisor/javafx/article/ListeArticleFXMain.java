
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
    
    public void startModif(Stage primaryStage,int ref, String titre, String desc, String tag, String cont, String img) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifArticleFXML.fxml"));
        try {
            Parent root = loader.load();
            Scene sc = new Scene(root);
            stage=new Stage();
            stage.setScene(sc);
            ModifArticleFXMLController cnt = loader.getController();
            cnt.setRef(ref);
            cnt.setContF(cont);
            cnt.setImgView(img);
            cnt.setDescCombo(desc);
            cnt.setTagsF(tag);
            cnt.setTitreF(titre);
            stage.show();
        } catch (IOException e) {
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
            //lire.setAffich(affich);
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
