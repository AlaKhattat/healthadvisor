/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLDrawerController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton doctorBtn;
    @FXML
    private JFXButton appointmentBtn;
    @FXML
    private JFXButton paymentBtn;
    @FXML
    private JFXButton exitBtn1;
    @FXML
    private JFXButton geolocalisation;
    @FXML
    private JFXButton questionreponse;
    @FXML
    private JFXButton sondage;
    @FXML
    private JFXButton bienetre;
    @FXML
    private JFXButton article;
    @FXML
    private JFXButton evenement;
    @FXML
    private JFXButton gererutilisateur;
    @FXML
    private JFXButton suivierdv;
    @FXML
    private JFXButton analyse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exit(ActionEvent event) {
                Platform.exit();

    }

    @FXML
    private void btnGeolocalAction(MouseEvent event) {
    }

    @FXML
    private void questionreponseAction(MouseEvent event) {
    }

    @FXML
    private void sondageAction(MouseEvent event) {
    }

    @FXML
    private void bienetreAction(MouseEvent event) {
    }

    @FXML
    private void articleAction(MouseEvent event) {
    }

    @FXML
    private void evenementAction(MouseEvent event) {
    }

    @FXML
    private void gererutilisateurAction(MouseEvent event) {
    }

    @FXML
    private void suivierdvAction(MouseEvent event) {
    }
    
}
