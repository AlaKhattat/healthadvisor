/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.prendrerdv;

import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.javafx.affichermedecin.FXMLAfficherMedecinController;
import static com.healthadvisor.javafx.article.ListeArticleFXMain.stage;
import com.healthadvisor.service.impl.GestionRendezVous;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private ComboBox<String> hourMinCombobox;
    @FXML
    private Button btnValiderRdv;

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
                           
                            if (item.isBefore(
                                    LocalDate.now())
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                    }
                };
            }
        };
datePickerRDV.setValue(LocalDate.now());
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
        rdv.setPatient_id("sdqqsd");
        rdv.setStatut_rendezvous("ENCOURS");
        //Manipulation de date
            String prepDate = datePickerRDV.getValue().toString()+","+hourMinCombobox.getValue();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd,hh:mm");
            Date dateRDV = formatter.parse(prepDate);
            rdv.setDate_heure(dateRDV);
            System.out.println(dateRDV);
        //Insertion dans BDD
        GestionRendezVous grdv=new GestionRendezVous();
        grdv.AjouterRendezVous(rdv);
        Alert a=new Alert(Alert.AlertType.NONE,"Vous recevez une notificatin lorsque le rensdez vous est confirmé",ButtonType.OK);
        a.show();
        
    }
    
}
