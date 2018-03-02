package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.entities.Evenement_Participants;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionEvenement;
import com.healthadvisor.service.impl.GestionEvenementParticipants;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import health_advisor.FXMLHomeViewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

public class LireEvenementFXMLController implements Initializable {

    @FXML
    private Label nomLab;
    @FXML
    private ImageView img;
    @FXML
    private Label typeLab;
    @FXML
    private Label dateLab;
    @FXML
    private Label heureLab;
    @FXML
    private Label endroitLab;
    @FXML
    private Label maxLab;
    @FXML
    private Button participeBut;
    @FXML
    private Label disp;
    @FXML
    private Label message;
    @FXML
    private ListView<String> listParticip;
    private Evenement evt;

    Date d2 = new Date(1970, 9, 9);
            GestionPatient gp=new GestionPatient();
            Patient p=gp.AfficherPatientCin(FXMLLoginController.Identifiant);    
    GestionEvenementParticipants gep = new GestionEvenementParticipants();
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label avert;
    @FXML
    private FontAwesomeIconView warning;
    @FXML
    private FontAwesomeIconView fleche;
    @FXML
    private JFXComboBox validCombo;
    @FXML
    private JFXButton validBut;

    GestionEvenement ge = new GestionEvenement();
    @FXML
    private Label createurLab;
    @FXML
    private JFXButton modif;

    private String retour;
    @FXML
    private FontAwesomeIconView back;

    public Evenement getEvt() {
        return evt;
    }

    public void setEvt(Evenement evt) {
        this.evt = evt;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* FXMLLoginController.admin=true;
        if(!FXMLLoginController.admin){
            validCombo.setOpacity(0);
            validBut.setOpacity(0);
            warning.setOpacity(1);
            fleche.setOpacity(1);
            avert.setOpacity(1);
            participeBut.setOpacity(1);
            message.setOpacity(1);
        }else{
            validCombo.setOpacity(1);
            validBut.setOpacity(1);
            warning.setOpacity(0);
            fleche.setOpacity(0);
            avert.setOpacity(0);
            participeBut.setOpacity(0);
            message.setOpacity(0);
        }*/
        validCombo.getItems().addAll("Valider", "Retirer");
    }

    public void dispoEvent(Evenement e, Patient p) {
        List<Evenement_Participants> lep = gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).collect(Collectors.toList());
        List<String> listP = new ArrayList<>();
        int nbr = (int) gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).count();
        for (Evenement_Participants ep : lep) {
            listP.add(ep.getpLog());
        }
        System.out.println(listP);
        if (nbr < e.getNbrMax()) {
            participeBut.setVisible(true);
            disp.setVisible(false);
            setWarning(e);
        } else {
            participeBut.setVisible(false);
            disp.setVisible(true);
        }
        if (listP.contains(p.getLogin())) {
            participeBut.setText("Je ne participe plus !");
            avert.setVisible(false);
            warning.setVisible(false);
            fleche.setVisible(false);
        } else {
            setWarning(e);
        }
    }

    public void ecrireMessage(Evenement e, Patient p) {
        int nbr = (int) gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).count();
        List<Evenement_Participants> lep = gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).collect(Collectors.toList());
        List<String> listP = new ArrayList<>();
        List<String> autres = new ArrayList<>();
        Random rd = new Random();
        for (Evenement_Participants ep : lep) {
            listP.add(ep.getpLog());
        }
        for (String s : listP) {
            if (s.equals(p.getLogin()) == false) {
                autres.add(s);
            }
        }
        if (nbr == 0) {
            message.setText("Soyez le premier à participer !");
        } else if (nbr == 1 && listP.get(0).equals(p.getLogin()) == false) {
            message.setText(lep.get(0).getpLog() + " y participe !");
        } else if (nbr == 1 && listP.get(0).equals(p.getLogin())) {
            message.setText("Vous étes le seul à y participer pour l'instant.");
        } else if (nbr == 2 && listP.contains(p.getLogin())) {
            message.setText("Vous et " + autres.get(0) + " participez à cet événement !");
        } else if (nbr > 2 && listP.contains(p.getLogin())) {
            int index = rd.nextInt(autres.size());
            message.setText("Vous, " + lep.get(index).getpLog() + " et " + (listP.size() - 2) + " autre(s) personne(s) \nallez participer à cet événement !");
        } else {
            int index = rd.nextInt(listP.size());
            message.setText(lep.get(index).getpLog() + " et " + (listP.size() - 1) + " autre(s) personne(s) \ny seront !");
        }
        ObservableList<String> participData = FXCollections.observableArrayList(listP);
        listParticip.setItems(participData);
    }

    @FXML
    private void ajoutParticip(ActionEvent event) {
        Evenement_Participants ep = new Evenement_Participants(evt, p);
        if (participeBut.getText().equals("Je participe !")) {
            gep.ajouterEvenementParticipants(ep);
            participeBut.setText("Je ne participe plus");
            warning.setVisible(false);
            avert.setVisible(false);
            fleche.setVisible(false);
        } else {
            gep.supprimerEvenementParticipants(ep);
            participeBut.setText("Je participe !");
            setWarning(evt);
        }
        ecrireMessage(evt, p);
    }

    public Label getNomLab() {
        return nomLab;
    }

    public void setNomLab(String nomLab) {
        this.nomLab.setText(nomLab);
    }

    public Label getTypeLab() {
        return typeLab;
    }

    public void setTypeLab(String typeLab) {
        this.typeLab.setText(typeLab);
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

    public Label getEndroitLab() {
        return endroitLab;
    }

    public void setEndroitLab(String endroitLab) {
        this.endroitLab.setText(endroitLab);
    }

    public Label getMaxLab() {
        return maxLab;
    }

    public void setMaxLab(int maxLab) {
        String max = Integer.toString(maxLab);
        this.maxLab.setText(max);
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(String url) {
        FileInputStream input;
        try {
            input = new FileInputStream(url);
            Image img_evt = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            this.img.setImage(img_evt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException io) {
            Logger.getLogger(LireEvenementFXMLController.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    public void setCreateurLab(String createurLab) {
        this.createurLab.setText(createurLab);
    }

    public void setWarning(Evenement e) {

        int nbr = (int) gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).count();
        int restant = e.getNbrMax() - nbr;
        if (restant <= 10 && restant > 0) {
            warning.setVisible(true);
            fleche.setVisible(true);
            avert.setVisible(true);
            avert.setText("Plus que " + restant + " places à prendre ! Dépéchez-vous, c'est pas la !");
        } else {
            avert.setVisible(false);
            warning.setVisible(false);
            fleche.setVisible(false);
        }
    }

    @FXML
    private void validEvt(ActionEvent event) {
        String statut = validCombo.getSelectionModel().getSelectedItem().toString();
        if (statut == "Valider") {
            Boolean b = ge.isValid(evt.getId());
            if (b.equals(false)) {
                ge.validationEvenement(evt.getId(), "Validé");
            } else {
                System.out.println("déja validé !");
            }
        } else if (statut == "Retirer") {
            Boolean b = ge.isValid(evt.getId());
            if (b.equals(true)) {
                ge.validationEvenement(evt.getId(), "Retiré");
            } else {
                System.out.println("déja retiré !");
            }
        }
    }

    @FXML
    private void redirectModif(ActionEvent event) {
        try {
            GestionEvenement ge = new GestionEvenement();
            AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("ModifEvenementFXML.fxml"));
            FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
            ModifEvenementFXMLController cnt = new ModifEvenementFXMLController();
            cnt.setId(evt.getId());
            cnt.setDateF(evt.getDate());
            cnt.setImgView(evt.getImage());
            cnt.setEndroitF(evt.getEndroit());
            cnt.setMaxF(evt.getNbrMax());
            cnt.setNomF(evt.getNom());
            cnt.setTimeF(evt.getHeure());
            cnt.setTypeCombo(evt.getType());
            cnt.setRetour(retour);
            //cnt.affichage = true;
        } catch (IOException ex) {
            Logger.getLogger(ModifEvenementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setRetour(String retour) {
        this.retour = retour;
    }

    @FXML
    private void redirectBack(MouseEvent event) {
        if (retour.equals("navig")) {
            try {
            AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("NaviguerEvenementsFXML.fxml"));
            FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
            } catch (IOException ex) {
                Logger.getLogger(NaviguerEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (retour.equals("mes")) {
            try {
                AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("MesEvenementsFXML.fxml"));
            FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
            } catch (IOException ex) {
                Logger.getLogger(MesEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public FontAwesomeIconView getBack() {
        return back;
    }

}
