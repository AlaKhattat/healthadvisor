package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class NaviguerArticlesFXMLController implements Initializable {

    @FXML
    private ScrollPane scroll;

    GestionArticle ga = new GestionArticle();
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VBox grosvbox = new VBox();
        grosvbox.setPrefWidth(550);
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
        List<Article> liste = new ArrayList<>();
        liste = ga.afficherArticle();
        for (int i = 0; i < liste.size(); i++) {
            Hyperlink titre = new Hyperlink();
            ListeArticleFXMain main = new ListeArticleFXMain();
            Article a = liste.get(i);
            titre.setText(a.getNom());
            titre.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int ref = a.getReference();
                    Stage st = new Stage();
                    String titre = a.getNom();
                    String desc = a.getDescription();
                    String cont = a.getContenu();
                    String idmed = a.getIdMed();
                    String img = a.getImage();
                    main.startAffich(st, titre, desc, cont, idmed, img);
                }
            });
            ImageView img = new ImageView();
            FileInputStream input;
            try {
                input = new FileInputStream(liste.get(i).getImage());
                Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
                img.setImage(img_evt);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException io) {
                Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, io);
            }
            img.setFitHeight(400);
            img.setFitWidth(450);
            titre.setFont(Font.font("verdana", 25));
            VBox vbox = new VBox();
            vbox.getChildren().setAll(titre, img);
            grosvbox.getChildren().add(vbox);
        }
        scroll.setContent(grosvbox);
    }

    @FXML
    private void retour(ActionEvent event) {
    }

}
