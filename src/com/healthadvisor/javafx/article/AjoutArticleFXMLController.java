
package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.service.impl.GestionArticle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.controlsfx.control.textfield.TextFields;

public class AjoutArticleFXMLController implements Initializable {

    @FXML
    private JFXTextField titreF;
    @FXML
    private JFXTextArea contF;
    @FXML
    private ImageView imageview;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label avertCont;
    @FXML
    private Label avertTitre;
    @FXML
    private JFXTextArea descF;
    @FXML
    private Label avertTags;
    @FXML
    private JFXTextField tag1;
    @FXML
    private JFXTextField tag2;
    @FXML
    private JFXTextField tag3;
    @FXML
    private Label avertDesc;

    private String url_image;
    private boolean permission;
    private String retour;
    
    private static List<String> tagsList = new ArrayList<>(Arrays.asList("#Sport", "#Régimes", "#Grossesse", "#Dépression", "#Psychologie", "#Santé", "#Médicaments", "#Bien-Etre", "#Musculation", "#Maladies", "#Blessures",
            "#Inflammations", "#Enfants", "#Adultes", "#Bébés", "#Beauté", "#Remédes", "#Vaccins", "#Chirurgie", "#Symptomes", "#Hopital", "#Clinique", "#Pharmacie", "#Découvertes", "#Dents", "#Esthétique", "#Forme", "#Alimentation"));
    private ObservableList<String> tagsOL = FXCollections.observableArrayList(tagsList);
    
    Date d3 = new Date(1970, 17, 4, 9, 5, 3);
    Medecin m=new Medecin(); //SESSION MEDECIN
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private JFXButton imgBut;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextFields.bindAutoCompletion(tag1, tagsList);
        TextFields.bindAutoCompletion(tag2, tagsList);
        TextFields.bindAutoCompletion(tag3, tagsList);
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
            javafx.scene.image.Image img_article = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            imageview.setImage(img_article);

        } catch (IOException ex) {
            System.err.println("Erreur d'enregistrement d'image");
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choisir une image");
        fileChooser.setInitialDirectory(
                new File("C:\\Users\\Tarek\\Desktop\\")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    private void ajoutArtFx(ActionEvent event) throws IOException {
        String titre = titreF.getText();
        String desc = descF.getText();
        String cont = contF.getText();
        String tag = tag1.getText() + "," + tag2.getText() + "," + tag3.getText();
        Article a = new Article(titre, desc, tag, cont, m, url_image);
        GestionArticle ga = new GestionArticle();
        if (permission == true) {
            ga.ajouterArticle(a);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsArticlesFXML.fxml"));
            Parent root;
            root = loader.load();
            DetailsArticlesFXMLController details = loader.getController();
            details.setTitreL(titre);
            details.setDescL(desc);
            details.setContL(cont);
            details.setTagsL(tag);
            details.setImgView(url_image);
            int id=ga.dernierArt();
            details.setId(id);
            details.setRetour(retour);
            Scene scene = anchor.getScene();
            scene.setRoot(root);
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
    private void redirectBack(MouseEvent event) {
        if (retour.equals("navig")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NaviguerArticlesFXML.fxml"));
            try {
                Parent root;
                root = loader.load();
                NaviguerArticlesFXMLController nav = loader.getController();
                Scene scene = anchor.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(NaviguerArticlesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (retour.equals("mes")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticlesFXML.fxml"));
            try {
                Parent root;
                root = loader.load();
                MesArticlesFXMLController mes = loader.getController();
                Scene scene = anchor.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(MesArticlesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void titreController(KeyEvent event) {
        if (titreF.getText().isEmpty()) {
            titreF.setFocusColor(Color.RED);
            avertTitre.setText("Veuillez attribuer un titre à votre article !");
            permission = false;
        } else if (titreF.getText().length() > 50) {
            titreF.setFocusColor(Color.RED);
            avertTitre.setText("Le titre de votre article ne peut pas dépasser les 50 caractéres !");
            permission = false;
        } else {
            titreF.setFocusColor(Color.rgb(25, 65, 111));
            avertTitre.setText(null);
            permission = true;
        }
    }

    @FXML
    private void contenuController(KeyEvent event) {
        if (contF.getText().length() < 50) {
            contF.setFocusColor(Color.RED);
            avertCont.setText("Un article devrait contenir au minimum 50 caractéres !");
            permission = false;
        } else if (contF.getText().length() > 255) {
            contF.setFocusColor(Color.RED);
            avertCont.setText("Un article ne devrait pas dépasser les 255 caractéres !");
            permission = false;
        } else {
            contF.setFocusColor(Color.rgb(25, 65, 111));
            avertCont.setText(null);
            permission = true;
        }
    }

    @FXML
    private void tagsController(KeyEvent event) {
        if (tag1.getText().isEmpty()) {
            tag1.setFocusColor(Color.RED);
            avertTags.setText("Veuillez remplir les 3 champs !");
            permission = false;
        } else {
            tag1.setFocusColor(Color.rgb(25, 65, 111));
            avertCont.setText(null);
            permission = true;
        }
    }

    @FXML
    private void tag2Controller(KeyEvent event) {
        if (tag2.getText().isEmpty()) {
            tag2.setFocusColor(Color.RED);
            avertTags.setText("Veuillez remplir les 3 champs !");
            permission = false;
        } else {
            tag2.setFocusColor(Color.rgb(25, 65, 111));
            avertCont.setText(null);
            permission = true;
        }
    }

    @FXML
    private void tag3Controller(KeyEvent event) {
        if (tag3.getText().isEmpty()) {
            tag3.setFocusColor(Color.RED);
            avertTags.setText("Veuillez remplir les 3 champs !");
            permission = false;
        } else {
            tag3.setFocusColor(Color.rgb(25, 65, 111));
            avertCont.setText(null);
            permission = true;
        }
    }

    @FXML
    private void descController(KeyEvent event) {
        if (descF.getText().isEmpty()) {
            descF.setFocusColor(Color.RED);
            avertDesc.setText("Veuillez remplir ce champ !");
            permission = false;
        } else {
            descF.setFocusColor(Color.rgb(25, 65, 111));
            avertDesc.setText(null);
            permission = true;
        }
    }

    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }

}
