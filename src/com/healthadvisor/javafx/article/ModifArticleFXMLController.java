package com.healthadvisor.javafx.article;

import com.healthadvisor.service.impl.GestionArticle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ModifArticleFXMLController implements Initializable {

    @FXML
    private TextField titreF;

    @FXML
    private TextField contF;
    @FXML
    private Button modifBut;
    @FXML
    private Button imgBut;
    @FXML
    private ImageView imgView;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ComboBox descCombo;

    private static Stage stage;
    private String url_image;
    private int ref;

    public void setRef(int ref) {
        this.ref = ref;
    }

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
    public void modifierFx(ActionEvent event) {
        String titre = titreF.getText();
        String desc = descCombo.getSelectionModel().getSelectedItem().toString();
        String cont = contF.getText();
        try {
            Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
            a1.setTitle("Modification Article");
            a1.setHeaderText(null);
            a1.setContentText("Etes-vous vraiment sur de vouloir modifier ");
            Optional<ButtonType> button = a1.showAndWait();
            if (button.get() == ButtonType.OK) {
                GestionArticle ga = new GestionArticle();
                ga.modifierArticle(ref, titre, desc, cont, url_image);
                ListeArticleFXMain.stage.close();
            }
        } catch (Exception e) {
        }
    }

}
