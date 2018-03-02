package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import health_advisor.FXMLHomeViewController;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class NaviguerArticlesFXMLController implements Initializable {

    @FXML
    private ScrollPane scroll;

    GestionArticle ga = new GestionArticle();
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private FontAwesomeIconView ajout;
    @FXML
    private VBox vb;
    @FXML
    private AnchorPane anchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Article> liste = new ArrayList<>();
        for (Article a : ga.afficherArticle()) {
            if (ga.isValid(a.getReference())) {
                liste.add(a);
            }
        }
        for (int i = 0; i < liste.size(); i++) {
            Hyperlink titre = new Hyperlink();
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
                    String tag = a.getTags();
                    double note = a.getNote();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("LireArticleFXML.fxml"));
                
                        LireArticleFXMLController cnt = loader.getController();
                        DetailsArticlesFXMLController details=new DetailsArticlesFXMLController();
                        AjoutArticleFXMLController ajout=new AjoutArticleFXMLController();
                        cnt.setId(ref);
                        cnt.setCont(cont);
                        cnt.setImg(a.getImage());
                        cnt.setDesc(desc);
                        cnt.setIdmed(idmed);
                        cnt.setTagsL(tag);
                        cnt.setTitre(titre);
                        cnt.setRating(note);
                        cnt.setUrl(a.getImage());
                        cnt.setRetour("navig");
                        details.setRetour("navig");             
                           AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("LireArticleFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
                    } catch (IOException ex) {
                        Logger.getLogger(NaviguerArticlesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException io) {
                Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, io);
            }
            img.setFitHeight(300);
            img.setFitWidth(350);
            titre.setFont(Font.font("verdana", 25));
            VBox vbox = new VBox();
            vbox.getChildren().setAll(titre, img);
            vb.getChildren().add(vbox);
        }
    }

    @FXML
    private void redirectBack(MouseEvent event) {
        try {
                  AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("InterfacePrincipaleFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajoutArt(MouseEvent event) {
        try {
       
            AjoutArticleFXMLController ajoutA = new AjoutArticleFXMLController();
            ajoutA.setRetour("navig");
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("AjoutArticleFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
