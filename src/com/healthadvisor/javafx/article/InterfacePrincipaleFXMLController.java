
package com.healthadvisor.javafx.article;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;


public class InterfacePrincipaleFXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Hyperlink list;
    @FXML
    private Hyperlink gestion;
    @FXML
    private Hyperlink mesArticles;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void redirectList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NaviguerArticlesFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            NaviguerArticlesFXMLController list = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void redirectGestion(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeArticleFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            ListeArticleFXMLController list = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectMyArt(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticlesFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            MesArticlesFXMLController mine = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
