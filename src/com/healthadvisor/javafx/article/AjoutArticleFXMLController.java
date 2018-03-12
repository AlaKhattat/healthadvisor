package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionArticle;
import com.healthadvisor.service.impl.GestionMedecin;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AjoutArticleFXMLController implements Initializable {

    @FXML
    private TextField titreF;
    @FXML
    private ComboBox descCombo;
    @FXML
    private TextField contF;
    @FXML
    private Button ajoutArtBut;
    @FXML
    private ImageView imageview;
    @FXML
    private Button imgBut;
    @FXML
    private AnchorPane anchor;

    private String url_image;

    Date d3 = new Date(1970, 17, 4, 9, 5, 3);
    @FXML
    private Button back;
    GestionMedecin gm= new GestionMedecin();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descCombo.getItems().addAll("Santé", "Médicaments", "Bien-Etre", "Régimes");
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
            javafx.scene.image.Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            imageview.setImage(img_produit);

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
    private void ajoutArtFx(ActionEvent event) {
        String titre = titreF.getText();
        String desc = descCombo.getSelectionModel().getSelectedItem().toString();
        String cont = contF.getText();
        //Medecin m= gm.AfficherMedecinLogin(FXMLLoginController.pseudo);
        Medecin m= gm.AfficherMedecinLogin("haboub");
        Article a = new Article(titre, desc, cont,m, url_image);
        GestionArticle ga = new GestionArticle();
        ga.ajouterArticle(a);
    }

    @FXML
    private void retour(ActionEvent event) {
    }

}
