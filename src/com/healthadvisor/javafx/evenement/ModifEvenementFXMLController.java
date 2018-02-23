package com.healthadvisor.javafx.evenement;

import com.healthadvisor.service.impl.GestionEvenement;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTimePicker;
import static java.awt.SystemColor.info;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ModifEvenementFXMLController implements Initializable {

    @FXML
    private TextField nomF;
    @FXML
    private Button img;
    @FXML
    private DatePicker dateF;
    @FXML
    private JFXTimePicker timeF;
    @FXML
    private TextField endroitF;
    @FXML
    private ComboBox typeCombo;
    @FXML
    private TextField maxF;
    @FXML
    private Button modifEvtBut;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView imgView;

    private int id;
    private static Stage stage;
    private String url_image;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeCombo.getItems().addAll("Marathon", "Tournoi sportif", "Conférence", "Journée de sensibilisation");
    }

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
    private void modifFx(ActionEvent event) {
        String nom = nomF.getText();
        Date date = Date.valueOf(dateF.getValue());
        Time heure = Time.valueOf(timeF.getValue());
        String endroit = endroitF.getText();
        String type = typeCombo.getSelectionModel().getSelectedItem().toString();
        int max = Integer.parseInt(maxF.getText());
        try {
            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
            a1.setTitle("Modification Article");
            a1.setHeaderText(null);
            a1.setContentText("Etes-vous vraiment sur de vouloir modifier ");
            Optional<ButtonType> button = a1.showAndWait();
            if (button.get() == ButtonType.OK) {
                GestionEvenement ge = new GestionEvenement();
                ge.modifierEvenement(id, nom, date, heure, endroit, type, max, url_image);
                ListeEvenementFXMain.stage.close();
            }
        } catch (Exception e) {
        }
    }

}
