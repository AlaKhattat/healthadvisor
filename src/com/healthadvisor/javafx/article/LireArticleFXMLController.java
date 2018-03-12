package com.healthadvisor.javafx.article;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class LireArticleFXMLController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label cont;
    @FXML
    private Label desc;
    @FXML
    private Label idmed;
    @FXML
    private ImageView imgView;
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Label getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    public Label getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont.setText(cont);
    }

    public Label getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.setText(desc);
    }

    public Label getIdmed() {
        return idmed;
    }

    public void setIdmed(String idmed) {
        this.idmed.setText("Par : " + idmed);
    }

    public ImageView getImg() {
        return imgView;
    }

    public void setImg(String url) {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imgView.setImage(img_produit);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(LireArticleFXMLController.class.getName()).log(Level.SEVERE, null, io);

        }

    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("NaviguerArticlesFXML.fxml"));
        try {
            Parent root=loader.load();
            Scene sc=new Scene(root);
            Stage st=new Stage();
            st.setScene(sc);
            st.show();
        } catch (Exception e) {
        }  
    }

}
