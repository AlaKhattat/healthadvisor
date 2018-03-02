package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
import com.jfoenix.controls.JFXButton;
import health_advisor.FXMLHomeViewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
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
import static jdk.nashorn.internal.runtime.Debug.id;

public class DetailsEvenementsFXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Label nomLab;
    @FXML
    private Label dateLab;
    @FXML
    private Label heureLab;
    @FXML
    private Label endroitLab;
    @FXML
    private Label typeLab;
    @FXML
    private ImageView imgView;
    @FXML
    private Label maxLab;
    @FXML
    private ImageView confirm;
    @FXML
    private JFXButton modif;
    @FXML
    private JFXButton back;
    
    Evenement e=new Evenement();

    public Evenement getE() {
        return e;
    }

    public void setE(Evenement e) {
        this.e = e;
    }
    
    
    
    private int id;
    private String retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public Label getNomLab() {
        return nomLab;
    }

    public void setNomLab(String nomLab) {
        this.nomLab.setText(nomLab);
    }

    public Label getDateLab() {
        return dateLab;
    }

    public void setDateLab(Date dateLab) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(dateLab);
        this.dateLab.setText(date);
    }

    public Label getHeureLab() {
        return heureLab;
    }

    public void setHeureLab(Time heureLab) {
        Format formatter = new SimpleDateFormat("HH:mm");
        String heure = formatter.format(heureLab);
        this.heureLab.setText(heure);
    }
        public void setId(int id) {
        this.id = id;
    }

    public Label getEndroitLab() {
        return endroitLab;
    }

    public void setEndroitLab(String endroitLab) {
        this.endroitLab.setText(endroitLab);
    }

    public Label getTypeLab() {
        return typeLab;
    }

    public void setTypeLab(String typeLab) {
        this.typeLab.setText(typeLab);
    }
    
    public Label getMaxLab() {
        return maxLab;
    }

    public void setMaxLab(int maxLab) {
        String max = Integer.toString(maxLab);
        this.maxLab.setText(max);
    }

    public ImageView getImg() {
        return imgView;
    }

    public void setImg(String url) {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.imgView.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, io);
        }

    }

    @FXML
    private void redirectModif(ActionEvent event) {
        try {
        
            GestionEvenement ge=new GestionEvenement();
            Evenement evt=ge.rechercherID(id);
            ModifEvenementFXMLController cnt = new ModifEvenementFXMLController();
            Date date=evt.getDate();
            cnt.setDateF(evt.getDate());
            cnt.setEndroitF(evt.getEndroit());
            cnt.setId(evt.getId());
            cnt.setImgView(evt.getImage());
            cnt.setMaxF(evt.getNbrMax());
            cnt.setNomF(evt.getNom());
            cnt.setTimeF(evt.getHeure());
            cnt.setTypeCombo(evt.getType());
            cnt.setRetour(retour);
            AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("ModifEvenementFXML.fxml"));
            FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(ModifEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        if (retour.equals("navig")) {
            try {
                AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("NaviguerEvenementsFXML.fxml"));
                FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
                NaviguerEvenementsFXMLController nav = new NaviguerEvenementsFXMLController();
            } catch (IOException ex) {
                Logger.getLogger(NaviguerEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (retour.equals("mes")) {
            try {
                AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("MesEvenementsFXML.fxml"));
                FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
                MesEvenementsFXMLController nav = new MesEvenementsFXMLController();
            } catch (IOException ex) {
                Logger.getLogger(MesEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getRetour() {
        return retour;
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }
    
    
}
