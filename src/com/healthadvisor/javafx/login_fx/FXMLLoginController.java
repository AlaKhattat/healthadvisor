/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.login_fx;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javafx.routes.Routes;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Label SignUp;
    @FXML
    private Label SignIn;
    @FXML
    private FontAwesomeIconView homeSignIn;
    @FXML
    private JFXPasswordField passwordsiginin;
    @FXML
    private JFXTextField pays;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXComboBox<String> sexe;
    private DatePicker date_naiss;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField numtel;
    GestionPatient gp=new GestionPatient();
    GestionMedecin gm=new GestionMedecin();
    public static String pseudo;
    public static String Identifiant;
    public static boolean docteur=false;

                public static ArrayList<ArrayList> panier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       String[] sexelist={"Homme","Femme"};
        ObservableList<String> sl=FXCollections.observableArrayList(sexelist);
        sexe.setItems(sl);
    }    


    @FXML
    private void SignUpAction(ActionEvent event) {
        AnchorSignUp.toFront();
        AnchorSignIn.setOpacity(0.0);
        AnchorSignUp.setOpacity(1.0);
        
    }

    @FXML
    private void homeAction(MouseEvent event) throws IOException {
        //Sign IN
        
        String usernamesigin=this.username.getText();
        pseudo=usernamesigin;
        String password=this.passwordsiginin.getText();
        Patient p= gp.AfficherPatientLogin(pseudo);
        Identifiant=p.getCin_user();
        if(p!=null){
            Identifiant=p.getCin_user();
            if (p.getPassword().equalsIgnoreCase(password)) {
            panier=new ArrayList<>();
            try{
            Medecin m=gm.AfficherMedecinLogin(pseudo);
            if(m.getLogin_med()!=null){
               docteur=true;
            }
            }catch(NullPointerException e){
                e.getMessage();
            }
            FXMLLoader loader=new FXMLLoader(getClass().getResource(Routes.RechercheMedecin)); 
            Parent root=loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(root));
            stage.show();
            }      
        }
    }

    @FXML
    private void homesignupAction(MouseEvent event) throws IOException {
 String cin=this.cin.getText();
 Identifiant=cin;
 String nom=this.nom.getText();
 String prenom=this.prenom.getText();
 String email=this.email.getText();
 LocalDate localDate =date.getValue();
 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
 Date date = Date.from(instant);
 String sexe=this.sexe.getValue();
 String pays=this.pays.getText();
 String ville=this.ville.getText();
 String num_tel=this.numtel.getText();
 int num=Integer.parseInt(num_tel);
        GestionUtilisateur gu=new GestionUtilisateur();
        Utilisateur u=new Utilisateur(cin, nom, prenom, email, date, sexe, pays, ville,num);
        gu.AjouterUtilisateur(u);
        FXMLLoader loader=new FXMLLoader(getClass().getResource(Routes.ChoixUser)); 
            Parent root=loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(root));
            stage.show();
}

    @FXML
    private void SigniInAction(MouseEvent event) {
         AnchorSignIn.toFront();
         AnchorSignIn.setOpacity(1.0);
         AnchorSignUp.setOpacity(0.0);
    }
    
}
