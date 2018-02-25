/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.inscrimedecin;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLInscriMedecinController implements Initializable {

    @FXML
    private JFXTextField diplome;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXComboBox<String> spécialite;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;
public static Double LAT_P;
public static Double LONG_P;
    @FXML
    private JFXButton position;
    @FXML
    private Label strenghtP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] specialitelist={
"Allergologie",
"Andrologie",
"Anesthésiologie",
"Chirurgie cardiaque",
"Chirurgie colorectale",
"Chirurgie générale",
"Chirurgie générale oncologique",
"Chirurgie générale pédiatrique",
"Chirurgie orthopédique",
"Chirurgie plastique",
"Chirurgie thoracique",
"Chirurgie vasculaire",
"Angiologie",
"Cardiologie",
"Chirurgie",
"Dentisterie",
"Dermatologie",
"Endocrinologie",
"Gastroentérologie",
"Gériatrie",
"Gynécologie",
"Hématologie",
"Hépatologie",
"Infectiologie",
"Médecine générale",
"Médecine interne",
"Néonatologie",
"Néphrologie",
"Neurologie",
"Obstétrique",
"Odontologie",
"Oncologie",
"Ophtalmologie",
"Orthodontie",
"Orthopédie",
"Oto-rhino-laryngologie",
"Pédiatrie",
"Pneumologie",
"Psychiatrie",
"Radiologie",
"Radiothérapie",
"Rhumatologie",
"Urologie"};
        ObservableList<String> sl=FXCollections.observableArrayList(specialitelist);
        spécialite.setItems(sl);
        new ComboBoxAutoComplete<String>(spécialite);

    }    

    @FXML
    private void validerInscri(MouseEvent event) {
             Image img=new Image("/com/healthadvisor/ressources/cancel.png");
        Notifications notif=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Champs Invalide")
                    .text("Il faut remplir tous les champs")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
            Image img2=new Image("/com/healthadvisor/ressources/user.png");
        Notifications notif2=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Inscription ")
                    .text("Inscription Avec Succés \n Bienvenue")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
        try {
        String login=this.login.getText();
        String password =this.password.getText();
        String specialite=this.spécialite.getValue();
        String adresse=this.adresse.getText();
        String diplome=this.diplome.getText();
         GestionMedecin gm=new GestionMedecin();
         GestionPatient gp= new GestionPatient();
         Patient p=new Patient(login, password,FXMLLoginController.Identifiant);
         gp.AjouterPatient(p);
         System.out.println(LAT_P+""+LONG_P);
         Medecin medecin=new Medecin(p.getLogin(), specialite, adresse, diplome,0,LAT_P,LONG_P,login, password, p.getCin_user());
         System.out.println("latitude"+medecin.getLat_p());
         gm.AjouterMedecin(medecin);
         notif2.show();
           }catch(Exception e){
            notif.show();
        }        
         
    }

    @FXML
    private void positionAction(MouseEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/healthadvisor/javafx/gmap/FXMLDocument.fxml"));
            Parent parent = loader.load();        
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Recuperer Ma Position");
            stage.setScene(new Scene(parent));
            stage.show();
    }

    @FXML
    private void specialiteControl(InputMethodEvent event) {
    }

    @FXML
    private void mdpControl(KeyEvent event) {
        String password;
        password = this.password.getText();

        boolean hasLetter = false;
        boolean hasDigit = false;

        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char x = password.charAt(i);
                if (Character.isLetter(x)) {

                    hasLetter = true;
                }

                else if (Character.isDigit(x)) {

                    hasDigit = true;
                }

                // no need to check further, break the loop
                if(hasLetter && hasDigit){

                    break;
                }

            }
            if (hasLetter && hasDigit) {
                strenghtP.setStyle("-fx-font-size: 15;\n" +
" -fx-text-fill: #ADFF2F;");
                strenghtP.setText("Fort");
            } else {
                strenghtP.setStyle(" -fx-font-size: 15;\n" +
" -fx-text-fill: #F39C12;");
                strenghtP.setText("Faible");
            }
        } else {
            strenghtP.setStyle(" -fx-font-size: 15;\n" +
" -fx-text-fill: #C70039;");
                strenghtP.setText("il faut au moins 8 caractères");
        }
    }
    
}
