/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.inscrimedecin;

import com.healthadvisor.encodedmd5.MD5Password;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.enumeration.StatutMedecinEnum;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.javafx.routes.Routes;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXTextField;
import health_advisor.FXMLHomeViewController;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLInscriMedecinController implements Initializable {

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
    private String url_image;
    private String url_image_profile;
    @FXML
    private ImageView imageview;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label labelDiplome;
    @FXML
    private ImageView imageProfile;
        private Desktop desktop=Desktop.getDesktop();

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
               .graphic(new ImageView(img2))
                    .title("Inscription ")
                    .text("Inscription Avec Succés \n Bienvenue")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
        try {
        String login=this.login.getText();
        String password =MD5Password.getEncodedPassword(this.password.getText());
        String specialite=this.spécialite.getValue();
        String adresse=this.adresse.getText();
         GestionMedecin gm=new GestionMedecin();
         GestionPatient gp= new GestionPatient();
         Patient p=new Patient(login, password,FXMLLoginController.Identifiant,url_image_profile);
         gp.AjouterPatient(p);
         System.out.println(LAT_P+""+LONG_P);
         Medecin medecin=new Medecin(p.getLogin(), specialite, adresse, url_image,0,LAT_P,LONG_P,StatutMedecinEnum.NON_VALIDE.name(),login, password, p.getCin_user(),url_image_profile);
         System.out.println("latitude"+medecin.getLat_p());
         gm.AjouterMedecin(medecin);
         notif2.show();
        AnchorPane loginpane = FXMLLoader.load(getClass().getResource(Routes.LOGINVIEW));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,loginpane);
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
            stage.getIcons().add(new Image("/com/healthadvisor/javafx/inscrimedecin/location.png"));
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

  
    @FXML
    public void ParcourirImage(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(anchor.getScene().getWindow());
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        FileInputStream input;
        try {            desktop.open(file);

            File dest = new File("C:\\wamp64\\www\\HealthAdvisorImages\\" + file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            url_image = dest.toPath().toString();
            System.out.println("Image enregistrée avec succés");
            input = new FileInputStream(url_image);
            javafx.scene.image.Image img_diplome = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            labelDiplome.setText("Votre Diplome : ");
            imageview.setImage(img_diplome);
        } catch (IOException ex) {
            System.err.println("Erreur d'enregistrement d'image");
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choisir une image");
        fileChooser.setInitialDirectory(
                new File("C:\\Users\\aaa\\Desktop\\")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    private void ParcourirImageProfile(ActionEvent event) {
            final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(anchor.getScene().getWindow());
        if (file != null) {
            openFileProfile(file);
        }
    }
      private void openFileProfile(File file) {
        FileInputStream input;
        try {
            File dest = new File("C:\\wamp64\\www\\HealthAdvisorImages\\" + file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            url_image_profile = dest.toPath().toString();
            System.out.println("Image enregistrée avec succés");
            input = new FileInputStream(url_image_profile);
            javafx.scene.image.Image img_profile = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            imageProfile.setImage(img_profile);
        } catch (IOException ex) {
            System.err.println("Erreur d'enregistrement d'image");
        }
    }
    }
    

