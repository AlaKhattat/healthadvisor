package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
import com.jfoenix.controls.JFXTimePicker;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AjoutEvenementFXMLController implements Initializable {

    @FXML
    private TextField nomF;
    @FXML
    private Button ajoutEvtBut;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField endroitF;
    @FXML
    private ComboBox typeCombo;
    @FXML
    private TextField maxF;
    @FXML
    private JFXTimePicker timeF;
    @FXML
    private Button imgBut;
    @FXML
    private ImageView imgView;
    @FXML
    private AnchorPane anchor;

    private String url_image;
    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeCombo.getItems().addAll("Marathon", "Tournoi sportif", "Conférence", "Journée de sensibilisation");
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
    private void ajoutFx(ActionEvent event) {
        String nom = nomF.getText();
        Date date = Date.valueOf(dateF.getValue());
        Time heure = Time.valueOf(timeF.getValue());
        String endroit = endroitF.getText();
        String type = typeCombo.getSelectionModel().getSelectedItem().toString();
        int max = Integer.parseInt(maxF.getText());

        GestionEvenement ge = new GestionEvenement();
        Evenement e = new Evenement(nom, date, heure, endroit, type, max, url_image);
        ge.ajouterEvenement(e);
    }

    @FXML
    private void retour(ActionEvent event) {
    }

}
