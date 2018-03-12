/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.editstatutrdv;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.healthadvisor.javafx.suivierendezvous.AlertMaker;
import com.healthadvisor.javamail.SendEmail;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionRendezVous;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.healthadvisor.sms.sendSMS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLEditStatutRDVController implements Initializable {

    @FXML
    private JFXTextField date;
    @FXML
    private JFXTextField docteur;
    @FXML
    private JFXTextField patient;
    @FXML
    private JFXComboBox<String> statut;
    private int id;
    private String patient_id;
    private String medecin_id;
    private Date dater;
    String[] sexelist={StatutRendezVousEnum.ANNULE.name(),StatutRendezVousEnum.ENCOURS.name(),StatutRendezVousEnum.VALIDE.name()};
    GestionRendezVous gr=new GestionRendezVous();
    GestionUtilisateur gu=new GestionUtilisateur();
    GestionMedecin gm=new GestionMedecin();
    GestionPatient gp=new GestionPatient();
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton annuler;
    @FXML
    private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> sl=FXCollections.observableArrayList(sexelist);
        statut.setItems(sl); 
    }    
    
     public void inflateUI(Rendez_Vous rdv) {
       date.setText(rdv.getDate_heure().toString());
       dater=rdv.getDate_heure();
       docteur.setText(rdv.getDocteur());
       patient.setText(rdv.getPatient());
       statut.setValue(rdv.getStatut_rendezvous());
       medecin_id=rdv.getMedecin_id();
       patient_id=rdv.getPatient_id();
       id=rdv.getId();
    }
      Image imgsucces=new Image("/com/healthadvisor/ressources/checked.png");
       Notifications notifsucces=Notifications.create()
               .graphic(new ImageView(imgsucces))
                    .title("Modification Statut Rendez_Vous")
                    .text("Statut RDV Modifié")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                ;
      private void handleEditOperation() {
         Rendez_Vous r=new Rendez_Vous(id,dater, patient_id, medecin_id,statut.getValue());
          System.out.println(r);
          if (gr.ModifierRendezVous(r)) {
            //AlertMaker.showSimpleAlert("Succes", "Rendez_Vous Mis a Jour");
            notifsucces.show();
            if(statut.getValue().equalsIgnoreCase(StatutRendezVousEnum.VALIDE.name())){
                // Info Patient 
                Patient p=gp.AfficherPatientLogin(patient_id);
                Utilisateur pu=gu.AfficherUtilisateurCin(p.getCin_user());
                // Info medecin
                Medecin m=gm.AfficherMedecinLogin(medecin_id);
                Patient pm=gp.AfficherPatientLogin(m.getLogin_med());
                Utilisateur pmu=gu.AfficherUtilisateurCin(pm.getCin_user());
                
                System.out.println("Envoie SMS et Email ... ");
                System.out.println("Envoie Email ... ");
                SendEmail se=new SendEmail();
                String sub="Rendez_Vous Confirmé";
                String msg="Bonjour "+pu.getPrenom()+",\nVotre rendez_vous avec le docteur "+pmu.getNom()+" "+pmu.getPrenom()+" est confirmé\nLe praticien vous attend le"+dater.toString()+".Si Vous avez un empechement, pensez a prevenir le praticien que vous allez annuler 24h avant au moins.";
                int sendMail = se.sendMail(" healthadvisoresprit@gmail.com","projetpidev","alaeddine.khattat@esprit.tn",sub,msg);  

      if(sendMail == 0){
          System.out.println("OK Email");}
else{
          System.out.println("Not OK");}
                      System.out.println("Envoie SMS ... ");
                      sendSMS ss=new sendSMS();
                        StringBuilder sb = new StringBuilder();
                        sb.append("");
                        sb.append(pu.getNum_tel());
                        String str = sb.toString();
                        String mess="Votre rendez_vous est confirmé le"+dater.toString();
                        String sendsms=ss.sendSms(mess,str);
                if(sendsms!=null){
                    System.out.println("OK sms");
                    AlertMaker.showSimpleAlert("Succés", "Patient Bien notifé");

                }
            }
        } else {
            AlertMaker.showErrorMessage("Erreur", "Erreur lors de la mise à jour");
        }
    }

    @FXML
    private void modifierStatut(MouseEvent event) {
        handleEditOperation();
    }

    @FXML
    private void quitter(MouseEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
}
