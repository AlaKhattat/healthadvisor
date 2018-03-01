package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.service.impl.GestionEvenement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
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

public class ModifEvenementFXMLController implements Initializable {

    @FXML
    private JFXTextField nomF;
    @FXML
    private JFXDatePicker dateF;
    @FXML
    private JFXTimePicker timeF;
    @FXML
    private JFXTextField endroitF;
    @FXML
    private JFXComboBox typeCombo;
    @FXML
    private JFXTextField maxF;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView imgView;
    @FXML
    private Label avertNom;
    @FXML
    private Label avertMax;

    private int id;
    private static Stage stage;
    private String url_image;
    private String retour;
    private boolean permission;
    Date d2 = new Date(1970, 9, 9);
    Patient p=new Patient(); //SESSION PATIENT
    @FXML
    private JFXButton modifBut;
    @FXML
    private JFXButton imgBut;
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private Label avertEndroit;

    public void setId(int id) {
        this.id = id;
    }

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
                new File("C:\\Users\\Tarek\\Desktop\\")
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
        if (permission == true) {
            try {
                Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
                a1.setTitle("Modification Article");
                a1.setHeaderText(null);
                a1.setContentText("Etes-vous vraiment sur de vouloir modifier ");
                Optional<ButtonType> button = a1.showAndWait();
                if (button.get() == ButtonType.OK) {
                    GestionEvenement ge = new GestionEvenement();
                    if (url_image == null) {
                        for (Evenement e : ge.afficherEvenement()) {
                            if (e.getId() == this.id) {
                                ge.modifierEvenement(id, nom, date, heure, endroit, type, max, e.getImage());
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
                                details.setImg(e.getImage());
                                details.setId(e.getId());
                                details.setRetour(retour);
                                Scene scene = anchor.getScene();
                                scene.setRoot(root);
                            }
                        }
                    } else {
                        ge.modifierEvenement(id, nom, date, heure, endroit, type, max, url_image);
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
                        details.setId(id);
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
            al.setTitle("MOdification événement");
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir tous les champs correctement !");
            al.showAndWait();
        }
    }

    public void setNomF(String nomF) {
        this.nomF.setText(nomF);
    }

    public void setDateF(Date dateF) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(dateF);
        this.dateF.setPromptText(date);
    }

    public void setTimeF(Time timeF) {
        Format formatter = new SimpleDateFormat("HH:mm");
        String heure = formatter.format(timeF);
        this.timeF.setPromptText(heure);
    }

    public void setEndroitF(String endroitF) {
        this.endroitF.setText(endroitF);
    }

    public void setTypeCombo(String typeCombo) {
        this.typeCombo.setPromptText(typeCombo);
    }

    public void setMaxF(int maxF) {
        String max = Integer.toString(maxF);
        this.maxF.setText(max);
    }

    public void setImgView(String imgView) {
        System.out.println("jjjjjjjjjj"+imgView);
        FileInputStream input;
        try {
            input = new FileInputStream(imgView);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imgView.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, io);
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
            permission = false;
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
    private void redirectBack(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LireEvenementFXML.fxml"));
        try {
            Parent root = loader.load();
            GestionEvenement ge = new GestionEvenement();
            Evenement evt = ge.rechercherID(id);
            LireEvenementFXMLController lire = loader.getController();
            lire.setDateLab(evt.getDate());
            lire.setEndroitLab(evt.getEndroit());
            lire.setEvt(evt);
            lire.setHeureLab(evt.getHeure());
            lire.setImg(evt.getImage());
            lire.setMaxLab(evt.getNbrMax());
            lire.setNomLab(evt.getNom());
            lire.setTypeLab(evt.getType());
            lire.setCreateurLab(evt.getLogCreateur());
            lire.setRetour(retour);
            lire.setWarning(evt);
            lire.dispoEvent(evt, p);
            lire.ecrireMessage(evt, p);
            Scene sc = anchor.getScene();
            sc.setRoot(root);
        } catch (IOException i) {
            Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, i);
        }
    }

    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
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
