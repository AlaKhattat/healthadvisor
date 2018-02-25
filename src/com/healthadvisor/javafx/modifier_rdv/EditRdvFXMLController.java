/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.modifier_rdv;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
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
    private ComboBox<String> hourMinCombobox;
    @FXML
    private Button btnValiderRdv;

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
    }    

  
    
}
