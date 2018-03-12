
package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

public class NaviguerEvenementsFXMLController implements Initializable {

    @FXML
    private ScrollPane scroll;

    GestionEvenement ge = new GestionEvenement();
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VBox grosvbox = new VBox();
        grosvbox.setPrefWidth(548);
        grosvbox.setPrefHeight(484);
        grosvbox.setMinWidth(USE_COMPUTED_SIZE);
        grosvbox.setMinHeight(USE_COMPUTED_SIZE);
        grosvbox.setMaxWidth(USE_COMPUTED_SIZE);
        grosvbox.setMaxHeight(USE_COMPUTED_SIZE);
        grosvbox.setLayoutX(35);
        grosvbox.setLayoutY(17);
        grosvbox.setPadding(new Insets(15, 450, 15, 450));
        grosvbox.setSpacing(35);
        grosvbox.setAlignment(Pos.CENTER);
        List<Evenement> liste = new ArrayList<>();
        liste = ge.afficherEvenement();
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
                    LireEvenementFXMLController le = new LireEvenementFXMLController();
                    main.startAffich(st, nom, date, heure, endroit, type, nbrMax, url, e);
                }
            });
            ImageView img = new ImageView();
            FileInputStream input;
            try {
                input = new FileInputStream(liste.get(i).getImage());
                Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
                img.setImage(img_evt);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException io) {
                Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, io);
            }
            img.setFitHeight(200);
            img.setFitWidth(250);
            titre.setFont(Font.font("verdana", 25));
            VBox vbox = new VBox();
            vbox.getChildren().setAll(titre, img);
            grosvbox.getChildren().add(vbox);
        }
        scroll.setContent(grosvbox);
 
    }

}
