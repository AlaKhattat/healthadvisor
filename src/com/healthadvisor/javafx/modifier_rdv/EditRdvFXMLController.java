/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.modifier_rdv;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.healthadvisor.javafx.affichermedecin.FXMLAfficherMedecinController;
import com.healthadvisor.javamail.SendEmail;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionRendezVous;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Firassov
 */
public class EditRdvFXMLController implements Initializable {

    @FXML
    private DatePicker datePickerRDV;
    @FXML
    private JFXComboBox<String> hourMinCombobox;
    @FXML
    private JFXButton btnValiderRdv;
    public static boolean editDone=false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(LocalDate.now()) || item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                    }
                };
            }
        };
datePickerRDV.setValue(ModifierRdvFXMLController.RDV.getDate_heure().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
datePickerRDV.setDayCellFactory(dayCellFactory);
String[] hhmm={
"09:30",
"10:00",
"10:30",
"11:00",
"11:30",
"12:00",
"12:30",
"14:30",
"15:00",
"15:30",
"16:00",
"16:30",
"17:00",
"17:30",
"18:00",
"18:30",
"19:00",
"19:30"};
ObservableList<String> sl=FXCollections.observableArrayList(hhmm);
hourMinCombobox.setItems(sl);

DateFormat formatter = new SimpleDateFormat("HH:mm");
String stringheure=formatter.format(ModifierRdvFXMLController.RDV.getDate_heure());
hourMinCombobox.getSelectionModel().select(stringheure);
    }    

    @FXML
    private void btnValiderAction(ActionEvent event) throws ParseException {
        //Preparation Objet RDV
        Rendez_Vous r=ModifierRdvFXMLController.RDV;
        //Manipulation de date et maj rdv
            String prepDate = datePickerRDV.getValue().toString()+","+hourMinCombobox.getValue();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
            Date dateRDV = formatter.parse(prepDate);
            r.setDate_heure(dateRDV);
            System.out.println(dateRDV);
        //MAJ BDD
        GestionRendezVous grdv=new GestionRendezVous();
        grdv.ModifierRendezVousdate(r);
        SendEmail email=new SendEmail();
        GestionMedecin gm=new GestionMedecin();
        GestionUtilisateur gu=new GestionUtilisateur();
        Medecin m=gm.AfficherMedecinLogin(r.getMedecin_id());
        Utilisateur u=gu.AfficherUtilisateurCin(m.getCin_user());
        
        Stage stage = (Stage) btnValiderRdv.getScene().getWindow();
        stage.close();
        editDone=true;
        //email.sendMail("healthadvisoresprit@gmail.com", "projetpidev",u.getEmail(), "Rendez vous modifié", "Le Patient "+r.getPatient_id()+" a modifié son rendez vous pour le "+prepDate);
        /*Alert a=new Alert(Alert.AlertType.NONE,"Votre RDV est à mis à jour",ButtonType.OK);
        a.show();*/
    }

  
    
}
