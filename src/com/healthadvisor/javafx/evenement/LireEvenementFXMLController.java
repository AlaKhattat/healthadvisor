package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.entities.Evenement_Participants;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionEvenementParticipants;
import com.healthadvisor.service.impl.GestionPatient;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    GestionPatient gp= new GestionPatient();
    GestionEvenementParticipants gep = new GestionEvenementParticipants();

    public Evenement getEvt() {
        return evt;
    }

    public void setEvt(Evenement evt) {
        this.evt = evt;
    }

    public void ecrireMessage(Evenement e) {
        int nbr = (int) gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).count();
        List<Evenement_Participants> lep = gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).collect(Collectors.toList());
        List<String> listP = new ArrayList<>();
        Random rd = new Random();
        for (Evenement_Participants ep : lep) {
            listP.add(ep.getpLog());
        }
        if (nbr == 0) {
            message.setText("Soyez le premier à participer !");
        } else if (nbr == 1) {

            message.setText(lep.get(0).getpLog() + " y participe !");
        } else {
            int index = rd.nextInt(listP.size());
            message.setText(lep.get(index).getpLog() + " et " + (listP.size() - 1) + " autre(s) personne(s) \ny seront !");
        }
        ObservableList<String> participData = FXCollections.observableArrayList(listP);
        listParticip.setItems(participData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void dispoEvent(Evenement e) {
        int nbr = (int) gep.afficherEvenementParticipants().stream().filter(ep -> ep.getIdEvent() == e.getId()).count();
        if (nbr < e.getNbrMax()) {
            participeBut.setVisible(true);
            disp.setVisible(false);
        } else {
            participeBut.setVisible(false);
            disp.setVisible(true);
        }

    }

    @FXML
    private void ajoutParticip(ActionEvent event) {
        Date d2 = new Date(1970, 9, 9);
        
        //Patient p =gp.AfficherPatientLogin(FXMLLoginController.pseudo);
        Patient p=gp.AfficherPatientLogin("kiki");
        Evenement_Participants ep =new Evenement_Participants(evt, p);
        if (participeBut.getText().equals("Je participe !")) {
            gep.ajouterEvenementParticipants(ep);
            participeBut.setText("Je ne participe plus");
        } else {
            gep.supprimerEvenementParticipants(ep);
            participeBut.setText("Je participe !");
        }
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

}
