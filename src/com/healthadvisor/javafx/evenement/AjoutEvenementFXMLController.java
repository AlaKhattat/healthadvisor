package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.entities.Evenement_Participants;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionEvenement;
import com.healthadvisor.service.impl.GestionEvenementParticipants;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import health_advisor.FXMLHomeViewController;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.imageio.ImageIO;

public class AjoutEvenementFXMLController implements Initializable {

    @FXML
    private JFXTextField nomF;
    @FXML
    private Button ajoutEvtBut;
    @FXML
    private JFXDatePicker dateF;
    @FXML
    private JFXTextField endroitF;
    @FXML
    private JFXComboBox typeCombo;
    @FXML
    private JFXTextField maxF;
    @FXML
    private JFXTimePicker timeF;
    @FXML
    private Button imgBut;
    @FXML
    private ImageView imgView;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label avertNom;
    @FXML
    private Label avertMax;

    private String url_image;
    private boolean permission;
    private Desktop desktop = Desktop.getDesktop();
    Date d2 = new Date(1970, 9, 9);
    
    private String retour;
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private Label avertEndroit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeCombo.getItems().addAll("Marathon", "Tournoi sportif", "Conférence", "Journée de sensibilisation");
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now()) || item.isEqual(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        dateF.setDayCellFactory(dayCellFactory);
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
        try {
            File dest = new File("C:\\wamp64\\www\\HealthAdvisorImages\\" + file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            url_image = dest.toPath().toString();
            System.out.println("Image enregistrée avec succés");
            System.out.println(url_image);
            input = new FileInputStream(url_image);
            Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            imgView.setImage(img_produit);
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
    private void ajoutFx(ActionEvent event) throws IOException {
        String nom = nomF.getText();
        Date date = Date.valueOf(dateF.getValue());
        Time heure = Time.valueOf(timeF.getValue());
        String endroit = endroitF.getText();
        String type = typeCombo.getSelectionModel().getSelectedItem().toString();
        int max = Integer.parseInt(maxF.getText());
        if (permission == true) {
            GestionPatient gp=new GestionPatient();
            Patient p=gp.AfficherPatientCin(FXMLLoginController.Identifiant);
            GestionEvenement ge = new GestionEvenement();
            Evenement e = new Evenement(nom, date, heure, endroit, type, max, url_image,p );
            ge.ajouterEvenement(e);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsEvenementsFXML.fxml"));
            Parent root;
            root = loader.load();
            DetailsEvenementsFXMLController details = loader.getController();
            details.setNomLab(nom);
            details.setDateLab(date);
            details.setHeureLab(heure);
            details.setEndroitLab(endroit);
            details.setTypeLab(type);
            details.setMaxLab(max);
            details.setImg(url_image);
            details.setRetour(retour);
            int id = ge.dernierEvt();
            details.setId(id);
            AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("DetailsEvenementsFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } else {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Ajout événement");
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir tous les champs correctement !");
            al.showAndWait();
        }
    }

    @FXML
    private void numController(KeyEvent event) {
        if (maxF.getText().isEmpty()) {
            maxF.setFocusColor(Color.RED);
            avertMax.setText("Veuillez remplir ce champ !");
            permission = false;
        } else {
            try {
                int max = Integer.parseInt(maxF.getText());
                if (max < 30) {
                    maxF.setFocusColor(Color.RED);
                    avertMax.setText("Le nombre max de participants ne doit pas étre inférieur à 30 !");
                    permission = false;
                } else {
                    maxF.setFocusColor(Color.rgb(25, 65, 111));
                    avertMax.setText(null);
                    permission = true;
                }
            } catch (NumberFormatException e) {
                maxF.setFocusColor(Color.RED);
                avertMax.setText("Veuillez n'entrer que des caractéres de type numérique !");
                permission = false;
            }
        }
    }

    @FXML
    private void nomController(KeyEvent event) {
        if (nomF.getText().isEmpty()) {
            nomF.setFocusColor(Color.RED);
            avertNom.setText("Veuillez attribuer un nom à votre événement !");
            permission = false;
        } else if (nomF.getText().length() > 50) {
            nomF.setFocusColor(Color.RED);
            avertNom.setText("Le nom de votre événement ne peut pas dépasser les 50 caractéres !");
            permission = false;
        } else {
            nomF.setFocusColor(Color.rgb(25, 65, 111));
            avertNom.setText(null);
            permission = true;
        }
    }

    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }

    @FXML
    private void redirectBack(MouseEvent event) {
        if (retour.equals("navig")) {
            try {
                AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("NaviguerEvenementsFXML.fxml"));
                FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
            } catch (IOException ex) {
                Logger.getLogger(NaviguerEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (retour.equals("mes")) {
            try{
            AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("MesEvenementsFXML.fxml"));
                FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
            } catch (IOException ex) {
                Logger.getLogger(MesEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void endroitController(KeyEvent event) {
        if (endroitF.getText().isEmpty()) {
            endroitF.setFocusColor(Color.RED);
            avertEndroit.setText("Veuillez remplir ce champ !");
            permission = false;
        } else if (endroitF.getText().length() > 50) {
            endroitF.setFocusColor(Color.RED);
            avertEndroit.setText("Ce champ ne peut pas dépasser les 50 caractéres !");
            permission = false;
        } else {
            endroitF.setFocusColor(Color.rgb(25, 65, 111));
            avertEndroit.setText(null);
            permission = true;
        }
    }

}
