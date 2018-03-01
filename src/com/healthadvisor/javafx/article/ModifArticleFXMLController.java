package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ModifArticleFXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView imageview;
    @FXML
    private JFXTextField titreF;
    @FXML
    private JFXTextArea contF;
    @FXML
    private Label avertCont;
    @FXML
    private Label avertTitre;
    @FXML
    private JFXTextField tag1;
    @FXML
    private Label avertTags;
    @FXML
    private JFXTextField tag2;
    @FXML
    private JFXTextField tag3;
    @FXML
    private JFXTextArea descF;
    @FXML
    private Label avertDesc;

    private String url_image;
    private int ref;
    private boolean permission;
    public static boolean affichage = true;
    private String retour;
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private JFXButton imgBut;
    @FXML
    private JFXButton modifBut;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back.setVisible(affichage);
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
            imageview.setImage(img_produit);

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
    public void modifierFx(ActionEvent event) {
        String titre = titreF.getText();
        String desc = descF.getText();
        String cont = contF.getText();
        String tag = tag1.getText() + "," + tag2.getText() + "," + tag3.getText();
        if (permission == true) {
            try {
                Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
                a1.setTitle("Modification Article");
                a1.setHeaderText(null);
                a1.setContentText("Etes-vous vraiment sur de vouloir modifier ");
                Optional<ButtonType> button = a1.showAndWait();
                if (button.get() == ButtonType.OK) {
                    GestionArticle ga = new GestionArticle();
                    if (url_image == null) {
                        for (Article a : ga.afficherArticle()) {
                            if (a.getReference() == this.ref) {
                                ga.modifierArticle(ref, titre, desc, tag, cont, a.getImage());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsArticlesFXML.fxml"));
                                Parent root;
                                root = loader.load();
                                DetailsArticlesFXMLController details = loader.getController();
                                details.setContL(cont);
                                details.setTitreL(titre);
                                details.setDescL(desc);
                                details.setTagsL(tag);
                                details.setId(ref);
                                details.setImgView(url_image);
                                details.setRetour(retour);
                                Scene scene = anchor.getScene();
                                scene.setRoot(root);
                            }
                        }
                    } else {
                        ga.modifierArticle(ref, titre, desc, tag, cont, url_image);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsArticlesFXML.fxml"));
                        Parent root;
                        root = loader.load();
                        DetailsArticlesFXMLController details = loader.getController();
                        details.setContL(cont);
                        details.setTitreL(titre);
                        details.setDescL(desc);
                        details.setTagsL(tag);
                        details.setId(ref);
                        details.setImgView(url_image);
                        details.setRetour(retour);
                        Scene scene = anchor.getScene();
                        scene.setRoot(root);
                    }
                }
            } catch (Exception e) {
            }
        } else {
            Alert al;
            al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Modification événement");
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir tous les champs correctement !");
            al.showAndWait();
        }
    }

    public void setContF(String contF) {
        this.contF.setText(contF);
    }

    public void setImgView(String imgView) {
        FileInputStream input;
        try {
            input = new FileInputStream(imgView);
            Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imageview.setImage(img_produit);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    public void setTitreF(String titreF) {
        this.titreF.setText(titreF);
    }

    public void setDescCombo(String desc) {
        this.descF.setText(desc);
    }

    public void setTagsF(String tagsF) {
        String[] parts = tagsF.split(",");
        this.tag1.setText(parts[0]);
        this.tag2.setText(parts[1]);
        this.tag3.setText(parts[2]);
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
    private void contController(KeyEvent event) {
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

    @FXML
    private void redirectBack(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LireArticleFXML.fxml"));
        try {
            Parent root = loader.load();
            GestionArticle ga = new GestionArticle();
            Article art = ga.rechercheRef(ref);
            LireArticleFXMLController lire = loader.getController();
            lire.setId(ref);
            lire.setTitre(titreF.getText());
            lire.setDesc(descF.getText());
            lire.setCont(contF.getText());
            lire.setIdmed(art.getIdMed());
            lire.setRating(art.getNote());
            lire.setTagsL(art.getTags());
            lire.setImg(art.getImage());
            lire.setUrl(url_image);
            lire.setRetour(retour);
            Scene sc = anchor.getScene();
            sc.setRoot(root);
        } catch (IOException i) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, i);
        }
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }

}
