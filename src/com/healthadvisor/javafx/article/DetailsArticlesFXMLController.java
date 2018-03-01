package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

public class DetailsArticlesFXMLController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private ImageView confirm;
    @FXML
    private Label titreL;
    @FXML
    private Label descL;
    @FXML
    private Label contL;
    @FXML
    private Label tagsL;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton modif;
    @FXML
    private AnchorPane anchor;

    private int id;
    public static boolean affichage;
    private String retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //back.setVisible(affichage);
    }

    public void setImgView(String url) {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imgView.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        if (retour.equals("navig")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NaviguerArticlesFXML.fxml"));
            try {
                Parent root;
                root = loader.load();
                NaviguerArticlesFXMLController nav = loader.getController();
                Scene scene = anchor.getScene();
                scene.setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void redirectModif(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifArticleFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            GestionArticle ga = new GestionArticle();
            Article art = ga.rechercheRef(id);
            ModifArticleFXMLController cnt = loader.getController();
            cnt.setRef(id);
            cnt.setContF(contL.getText());
            cnt.setImgView(art.getImage());
            cnt.setDescCombo(descL.getText());
            cnt.setTagsF(tagsL.getText());
            cnt.setTitreF(titreL.getText());
            cnt.setRetour(retour);
            //cnt.affichage = true;
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTitreL(String titreL) {
        this.titreL.setText(titreL);
    }

    public void setDescL(String descL) {
        this.descL.setText(descL);
    }

    public void setContL(String contL) {
        this.contL.setText(contL);
    }

    public void setTagsL(String tagsL) {
        this.tagsL.setText(tagsL);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }
    
}
