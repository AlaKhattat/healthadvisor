/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.editstatutrdv;

import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.healthadvisor.javafx.suivierendezvous.AlertMaker;
import com.healthadvisor.service.impl.GestionRendezVous;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
     
      private void handleEditOperation() {
         Rendez_Vous r=new Rendez_Vous(id,dater, patient_id, medecin_id,statut.getValue());
          if (gr.ModifierRendezVous(r)) {
            AlertMaker.showSimpleAlert("Success", "Rendez_Vous Mis a Jour");
        } else {
            AlertMaker.showErrorMessage("Failed", "Erreur lors de la mise à jour");
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
