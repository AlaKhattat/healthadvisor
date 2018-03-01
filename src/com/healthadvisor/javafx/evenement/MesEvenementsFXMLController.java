
package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.service.impl.GestionEvenement;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class MesEvenementsFXMLController implements Initializable {

    private ScrollPane scroll;
    @FXML
    private AnchorPane anchor;
    @FXML
    private VBox vb;
    @FXML
    private FontAwesomeIconView back;
    
    Date d2 = new Date(1970, 9, 9);
    Patient p=new Patient(); //SESSION PATIENT
    
    GestionEvenement ge = new GestionEvenement();
    @FXML
    private FontAwesomeIconView ajout;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Evenement> liste = new ArrayList<>();
        for (Evenement i : ge.afficherEvenement()) {
            if (i.getLogCreateur().equals(p.getLogin())) {
                liste.add(i);
            }
        }
        for (int i = 0; i < liste.size(); i++) {
            Hyperlink titre = new Hyperlink();
            ListeEvenementFXMain main = new ListeEvenementFXMain();
            Evenement e = liste.get(i);
            titre.setText(e.getNom());
            titre.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage st = new Stage();
                    int i = e.getId();
                    String nom = e.getNom();
                    Date date = e.getDate();
                    Time heure = e.getHeure();
                    String endroit = e.getEndroit();
                    String type = e.getType();
                    int nbrMax = e.getNbrMax();
                    String url = e.getImage();
                    String createur=e.getLogCreateur();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("LireEvenementFXML.fxml"));
                        Parent root;
                        root = loader.load();
                        LireEvenementFXMLController cnt = loader.getController();
                        DetailsEvenementsFXMLController details=new DetailsEvenementsFXMLController();
                        cnt.setDateLab(date);
                        cnt.setEndroitLab(endroit);
                        cnt.setHeureLab(heure);
                        cnt.setTypeLab(type);
                        cnt.setNomLab(nom);
                        cnt.setImg(url);
                        cnt.setMaxLab(nbrMax);
                        cnt.setCreateurLab(createur);
                        cnt.setRetour("mes");
                        details.setRetour("mes");
                        cnt.setWarning(e);
                        cnt.setEvt(e);
                        cnt.ecrireMessage(e, p);
                        cnt.dispoEvent(e, p);
                        Scene scene = anchor.getScene();
                        scene.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(NaviguerEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            ImageView img = new ImageView();
            FileInputStream input;
            try {
                input = new FileInputStream(liste.get(i).getImage());
                Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
                img.setImage(img_evt);
            } catch (FileNotFoundException ex) {
            } catch (IOException io) {
            }
            img.setFitHeight(200);
            img.setFitWidth(250);
            titre.setFont(Font.font("verdana", 25));
            VBox vbox = new VBox();
            vbox.getChildren().setAll(titre, img);
            vb.getChildren().add(vbox);
        }
    }

    @FXML
    private void redirectBack(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacePrincipaleFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            InterfacePrincipaleFXMLController interf = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajoutEvt(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutEvenementFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            AjoutEvenementFXMLController ajoutE = loader.getController();
            ajoutE.setRetour("mes");
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjoutEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
