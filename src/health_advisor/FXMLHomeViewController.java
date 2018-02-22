/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.healthadvisor.javafx.routes.Routes;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLHomeViewController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label txtCurrentWindow;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
   HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }

        });
        try {
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/FXMLDrawer.fxml"));
            AnchorPane  acceuil= FXMLLoader.load(getClass().getResource(Routes.ChoixUser));
            AnchorPane login = FXMLLoader.load(getClass().getResource(Routes.LOGINVIEW));
            AnchorPane recherche = FXMLLoader.load(getClass().getResource(Routes.RechercheMedecin));
            AnchorPane suivie = FXMLLoader.load(getClass().getResource(Routes.InscriPatient));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane Boutique = FXMLLoader.load(getClass().getResource(Routes.BOUTIQUE));
            AnchorPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSE));
            AnchorPane Sondage = FXMLLoader.load(getClass().getResource(Routes.SONDAGE));
            AnchorPane BienEtre = FXMLLoader.load(getClass().getResource(Routes.BIENETRE));
            ScrollPane Article = FXMLLoader.load(getClass().getResource(Routes.ARTICLE));
            ScrollPane Evenement = FXMLLoader.load(getClass().getResource(Routes.EVENEMENT));

            setNode(acceuil);
            drawer.setSidePane(sidePane);
            ScrollPane ss=new ScrollPane();
            ss.setContent(sidePane);
            drawer.getChildren().add(ss);
            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "acceuil":
                                drawer.close();
                                setNode(acceuil);
                                break;
                            case "recherchemed":
                                drawer.close();                               
                                setNode(recherche);
                                break;
                            case "sign":
                                drawer.close();
                                setNode(login);
                                break;
                            case "symptome":
                                drawer.close();                                
                                setNode(Symptome);
                                break;    
                            case "geolocalisation":
                                drawer.close();                                
                                setNode(Geolocalisation);
                                break;
                            case "boutique":
                                drawer.close();                                
                                setNode(Boutique);
                                break; 
                            case "questionreponse":
                                drawer.close();                                
                                setNode(QuestionReponse);
                                break;
                            case "sondage":
                                drawer.close();                                
                                setNode(Sondage);
                                break;  
                            case "bienetre":
                                drawer.close();                                
                                setNode(BienEtre);
                                break;
                            case "evenement":
                                drawer.close();                                
                                setNode(Evenement);
                                break;
                            case "article":
                                drawer.close();                                
                                setNode(Article);
                                break;                                   
                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        
        holderPane.getChildren().add((Node) node);
    } 
    
}
