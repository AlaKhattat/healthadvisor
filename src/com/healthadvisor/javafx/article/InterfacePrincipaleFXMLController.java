
package com.healthadvisor.javafx.article;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import health_advisor.FXMLHomeViewController;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class InterfacePrincipaleFXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Hyperlink list;
    @FXML
    private FontAwesomeIconView gestion;
    @FXML
    private Hyperlink mesArticles;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void redirectList(ActionEvent event) {
        try {
                    AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("NaviguerArticlesFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectMyArt(ActionEvent event) {
        try {
       AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("MesArticlesFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectGestion(MouseEvent event) {
        try {
         AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("ListeArticleFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
