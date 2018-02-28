/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.login_fx;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.enumeration.StatutMedecinEnum;
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
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    public static boolean patient=false;
    public static Double LAT_P_Co;
    public static Double LONG_P_Co;

                public static ArrayList<ArrayList> panier;
    @FXML
    private AnchorPane holderLogin;
           

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
     public DateCell call(final DatePicker datePicker) {
         return new DateCell() {
             @Override public void updateItem(LocalDate item, boolean empty) {
                 super.updateItem(item, empty);

                 if (Year.from(item).isAfter(Year.of(Year.now().getValue()-18))||Year.from(item).isBefore(Year.of(Year.now().getValue()-100))) {
                     setTooltip(new Tooltip("Votre Age doit etre compris entre 18 et 80 ans"));
                     setStyle("-fx-background-color: #ff4444;");
                     setDisable(true);
                 }
                
             }
         };
     }
 };
       date.setDayCellFactory(dayCellFactory);
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
        if(username.getText().isEmpty()||passwordsiginin.getText().isEmpty()){
              Image img=new Image("/com/healthadvisor/ressources/cancel.png");
        Notifications notif=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Champs Invalide")
                    .text("Il faut remplir tous les champs")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();  
        notif.show();
            
        }else {
        String usernamesigin=this.username.getText();
        pseudo=usernamesigin;
        String password=this.passwordsiginin.getText();
        Patient p= gp.AfficherPatientLogin(pseudo);
       if(p!=null){

            Identifiant=p.getCin_user();
            patient=true;
            if (p.getPassword().equalsIgnoreCase(password)) {
           
            try{
            Medecin m=gm.AfficherMedecinLogin(p.getLogin());
            if(m.getLogin_med()!=null){
                System.out.println("Medecin ...");
                LAT_P_Co=m.getLat_p();
                LONG_P_Co=m.getLong_p();
                System.out.println("LAtitude"+LAT_P_Co+"Longitude"+LONG_P_Co);
                patient=false;
                docteur=true;
                
            }
            }catch(NullPointerException e){
                e.getMessage();
            }
          

            FXMLLoader loader=new FXMLLoader(getClass().getResource(Routes.HOMEVIEW)); 
            Parent root=loader.load();
            Scene s = holderLogin.getScene(); 
            s.setRoot(root);
            /*
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(root));
            stage.show();*/
            }      
        }
    }
    }

    @FXML
    private void homesignupAction(MouseEvent event) throws IOException {
        Image img=new Image("/com/healthadvisor/ressources/cancel.png");
        Notifications notif=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Champs Invalide")
                    .text("Il faut remplir tous les champs")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();   
         try{

 String cin=this.cin.getText();
 Identifiant=cin;
 String nom=this.nom.getText();
 String prenom=this.prenom.getText();
 String email=this.email.getText();
 LocalDate localDate =date.getValue();
 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
 Date date = Date.from(instant);
 //Pick on bounds
 this.date.isPickOnBounds();
 String sexe=this.sexe.getValue();
 String pays=this.pays.getText();
 String ville=this.ville.getText();
 String num_tel=this.numtel.getText();
 int num=Integer.parseInt(num_tel);
        GestionUtilisateur gu=new GestionUtilisateur();
        Utilisateur u=new Utilisateur(cin, nom, prenom, email, date, sexe, pays, ville,num);
        gu.AjouterUtilisateur(u);
   
            
            AnchorPane choixuser = FXMLLoader.load(getClass().getResource(Routes.ChoixUser));
            FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,choixuser);
      
         }catch(Exception e){
        notif.show();
 
        }
       
  
}

    @FXML
    private void SigniInAction(MouseEvent event) {
         AnchorSignIn.toFront();
         AnchorSignIn.setOpacity(1.0);
         AnchorSignUp.setOpacity(0.0);
    }

    @FXML
    private void nomControl(KeyEvent event) {
        if(nom.getText().isEmpty()){
            nom.setFocusColor(Color.RED);
        }else {
            nom.setFocusColor(Color.BLUE);
        }
    }

    @FXML
    private void prenomControl(KeyEvent event) {
          if(prenom.getText().isEmpty()){
            prenom.setFocusColor(Color.RED);
        }else {
            prenom.setFocusColor(Color.BLUE);
        }
    }

    @FXML
    private void emailControl(KeyEvent event) {
        String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email.getText());
        if (controler.matches()){
        //Ok : la saisie est bonne
        email.setFocusColor(Color.BLUE);
        }else{email.setFocusColor(Color.RED);
        //La c'est pas bon
        }
    }

    @FXML
    private void paysControl(KeyEvent event) {
            if(pays.getText().isEmpty()){
            pays.setFocusColor(Color.RED);
        }else {
            pays.setFocusColor(Color.BLUE);
        }
    }

    @FXML
    private void cinControl(KeyEvent event) {
       String num=cin.getText();
       if(num.length()<8 || num.length() >8 ){
       cin.setFocusColor(Color.RED);
       }else {
       try{
       cin.setFocusColor(Color.BLUE);
       int numcin=Integer.parseInt(num);
       }catch(NumberFormatException ex)  {
       cin.setFocusColor(Color.RED);
       }
       }
    }

    @FXML
    private void villeControl(KeyEvent event) {
                if(ville.getText().isEmpty()){
            ville.setFocusColor(Color.RED);
        }else {
            ville.setFocusColor(Color.BLUE);
        }
    }

    @FXML
    private void sexeControl(InputMethodEvent event) {
                if(sexe.getValue().isEmpty()){
            sexe.setFocusColor(Color.RED);
        }else {
            sexe.setFocusColor(Color.BLUE);
        }
    }

 

    @FXML
    private void telControl(KeyEvent event) {
       String num=numtel.getText();
       try{
       numtel.setFocusColor(Color.BLUE);
       int numtel=Integer.parseInt(num);
       }catch(NumberFormatException ex)  {
       numtel.setFocusColor(Color.RED);

       }}

    @FXML
    private void pseudoControl(KeyEvent event) {
                if(username.getText().isEmpty()){
            username.setFocusColor(Color.RED);
        }else {
            username.setFocusColor(Color.BLUE);
        }
    }

    @FXML
    private void mdpControl(KeyEvent event) {
                 if(passwordsiginin.getText().isEmpty()){
            passwordsiginin.setFocusColor(Color.RED);
                 }else {
            passwordsiginin.setFocusColor(Color.BLUE);    
    }
        }

    @FXML
    private void dateControl(ActionEvent event) {
   
 
}
    
}
