/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.prendrerdv;

import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.healthadvisor.javafx.affichermedecin.FXMLAfficherMedecinController;
import static com.healthadvisor.javafx.article.ListeArticleFXMain.stage;
import com.healthadvisor.service.impl.GestionRendezVous;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Firassov
 */
public class PrendreRDVFXMLController implements Initializable {

    @FXML
    private DatePicker datePickerRDV;
    @FXML
    private JFXComboBox<String> hourMinCombobox;
    @FXML
    private JFXButton btnValiderRdv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(FXMLAfficherMedecinController.med.getLogin_med());
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
if(LocalTime.now().isAfter(LocalTime.of(17, 30)) && LocalTime.now().isBefore(LocalTime.of(23, 59)) ){
datePickerRDV.setValue(LocalDate.now().plusDays(1));
hourMinCombobox.getSelectionModel().select("09:30");
}
else if(LocalTime.now().isBefore(LocalTime.of(7, 30)) && LocalTime.now().isAfter(LocalTime.of(00,01))){
datePickerRDV.setValue(LocalDate.now());
hourMinCombobox.getSelectionModel().select("09:30");   
}
else{
datePickerRDV.setValue(LocalDate.now());
hourMinCombobox.getSelectionModel().select((LocalTime.now().getHour()+2)+":00");
}
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

    }    

    @FXML
    private void btnValiderRdvAction(ActionEvent event) throws ParseException {
        //Preparation Objet RDV
        Rendez_Vous rdv=new Rendez_Vous();
        rdv.setMedecin_id(FXMLAfficherMedecinController.med.getLogin_med());
        rdv.setPatient_id("firas");
        rdv.setStatut_rendezvous(StatutRendezVousEnum.ENCOURS.name());
        //Manipulation de date
            String prepDate = datePickerRDV.getValue().toString()+","+hourMinCombobox.getValue();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm");
            Date dateRDV = formatter.parse(prepDate);
            rdv.setDate_heure(dateRDV);
            System.out.println(dateRDV);
        //Insertion dans BDD
        GestionRendezVous grdv=new GestionRendezVous();
        grdv.AjouterRendezVous(rdv);
        Stage stage = (Stage) btnValiderRdv.getScene().getWindow();
        stage.close();
        Alert a=new Alert(Alert.AlertType.NONE,"Vous recevez une notificatin lorsque le rensdez vous est confirmé",ButtonType.OK);
        a.show();
        
    }
    
}
