package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionArticle;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import health_advisor.FXMLHomeViewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

public class MesArticlesFXMLController implements Initializable {

    private ScrollPane scroll;
    @FXML
    private AnchorPane anchor;
    @FXML
    private VBox vb;

    GestionArticle ga = new GestionArticle();
    Date d3 = new Date(1970, 17, 4, 9, 5, 3);
    GestionPatient gp=new GestionPatient();
    
     Patient p=gp.AfficherPatientCin(FXMLLoginController.Identifiant);
    GestionMedecin gm= new GestionMedecin();
    Medecin m=gm.AfficherMedecinLogin(p.getLogin());    
    @FXML
    private FontAwesomeIconView back;
    @FXML
    private FontAwesomeIconView ajout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Article> liste = new ArrayList<>();
        for (Article a : ga.afficherArticle()) {
            if (a.getIdMed().equals(m.getLogin())) {
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
                        DetailsArticlesFXMLController details = new DetailsArticlesFXMLController();
                        cnt.setId(ref);
                        cnt.setCont(cont);
                        cnt.setImg(a.getImage());
                        cnt.setDesc(desc);
                        cnt.setIdmed(idmed);
                        cnt.setTagsL(tag);
                        cnt.setTitre(titre);
                        cnt.setRating(note);
                        cnt.setUrl(a.getImage());
                        cnt.setRetour("mes");
                        details.setRetour("mes");
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
            img.setFitHeight(400);
            img.setFitWidth(450);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutArticleFXML.fxml"));
        try {
        
            AjoutArticleFXMLController ajoutA = loader.getController();
            ajoutA.setRetour("mes");
              AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("AjoutArticleFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
