/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.login_fx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private JFXButton signin;
    @FXML
    private JFXButton signup;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private FontAwesomeIconView homesignup;
    @FXML
    private AnchorPane AnchorSignUp;
    @FXML
    private AnchorPane AnchorSignIn;
    @FXML
    private JFXButton SignUpButton;
    @FXML
    private Label SignUp;
    @FXML
    private Label SignIn;
    @FXML
    private FontAwesomeIconView homeSignIn;
    @FXML
    private JFXTextField passwordsignup;
    @FXML
    private JFXPasswordField passwordsiginin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    


    @FXML
    private void SignUpAction(ActionEvent event) {
        AnchorSignUp.toFront();
        AnchorSignIn.setOpacity(0.0);
        AnchorSignUp.setOpacity(1.0);
        /*username.setOpacity(1);
        nom.setOpacity(1);
        prenom.setOpacity(1);
        email.setOpacity(1);
        SignUp.setOpacity(1);*/
    }

    @FXML
    private void homeAction(MouseEvent event) {
    }

    @FXML
    private void homesignupAction(MouseEvent event) {
    }

    @FXML
    private void SigniInAction(MouseEvent event) {
         AnchorSignIn.setOpacity(1);
         AnchorSignUp.setOpacity(0);
    }
    
}
