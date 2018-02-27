/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.javafx.routes.Routes;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.StackPane;
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
    public  AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton Signin;
    @FXML
    private JFXButton Signout;
    @FXML
    private JFXButton profile;
    @FXML
    public static AnchorPane mainPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
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
        
if(FXMLLoginController.patient){
     try {
            System.out.println("Initialisation Patient ...");
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/PatientDrawer.fxml"));
            AnchorPane  acceuil= FXMLLoader.load(getClass().getResource(Routes.HOME));
            AnchorPane recherche = FXMLLoader.load(getClass().getResource(Routes.RechercheMedecin));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane Boutique = FXMLLoader.load(getClass().getResource(Routes.BOUTIQUE));
            AnchorPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSE));
            AnchorPane Sondage = FXMLLoader.load(getClass().getResource(Routes.SONDAGE));
            AnchorPane BienEtre = FXMLLoader.load(getClass().getResource(Routes.BIENETRE));
            ScrollPane Article = FXMLLoader.load(getClass().getResource(Routes.ARTICLE));
            ScrollPane Evenement = FXMLLoader.load(getClass().getResource(Routes.EVENEMENT));
       
            setNode(recherche);
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
}else {
    if(FXMLLoginController.docteur){
     try {
            System.out.println("Initialisation Medecin ...");
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/MedecinDrawer.fxml"));
            AnchorPane  acceuil= FXMLLoader.load(getClass().getResource(Routes.HOME));
            AnchorPane recherche = FXMLLoader.load(getClass().getResource(Routes.RechercheMedecin));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane Boutique = FXMLLoader.load(getClass().getResource(Routes.BOUTIQUE));
            AnchorPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSE));
            AnchorPane Sondage = FXMLLoader.load(getClass().getResource(Routes.SONDAGE));
            AnchorPane BienEtre = FXMLLoader.load(getClass().getResource(Routes.BIENETRE));
            ScrollPane Article = FXMLLoader.load(getClass().getResource(Routes.ARTICLE));
            ScrollPane Evenement = FXMLLoader.load(getClass().getResource(Routes.EVENEMENT));
            AnchorPane SuivieRDV = FXMLLoader.load(getClass().getResource(Routes.SuivieRDV_M));

       
            setNode(recherche);
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
                            case "suivierdv":
                                drawer.close();                                
                                setNode(SuivieRDV);
                                break; 
                                                                           
                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    else {
        try {
            System.out.println("Initialisation Utilisateur ...");
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/UtilisateurDrawer.fxml"));
            AnchorPane acceuil= FXMLLoader.load(getClass().getResource(Routes.HOME));
            AnchorPane recherche = FXMLLoader.load(getClass().getResource(Routes.RechercheMedecin));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSE));
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
                          
                            case "symptome":
                                drawer.close();                                
                                setNode(Symptome);
                                break;    
                            case "geolocalisation":
                                drawer.close();                                
                                setNode(Geolocalisation);
                                break; 
                            case "questionreponse":
                                drawer.close();                                
                                setNode(QuestionReponse);
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
}
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();        
        holderPane.getChildren().add((Node) node);
    } 

    @FXML
    private void SigninAction(MouseEvent event) throws IOException {
    AnchorPane login = FXMLLoader.load(getClass().getResource(Routes.LOGINVIEW));
    setNode(login);
    }

    @FXML
    private void SignoutAction(MouseEvent event) throws IOException {
        FXMLLoginController.pseudo=null;
        FXMLLoginController.Identifiant=null;
        AnchorPane login = FXMLLoader.load(getClass().getResource(Routes.LOGINVIEW));
        setNode(login);
    }

    @FXML
    private void profileAction(MouseEvent event) throws IOException {
        if(FXMLLoginController.docteur==true){
                AnchorPane ProfileMedecin = FXMLLoader.load(getClass().getResource(Routes.PROFILEMEDECIN));
                setNode(ProfileMedecin);
        }else{
                 AnchorPane ProfilePatient = FXMLLoader.load(getClass().getResource(Routes.PROFILEPATIENT));
                setNode(ProfilePatient);

        }
    }
    
}
