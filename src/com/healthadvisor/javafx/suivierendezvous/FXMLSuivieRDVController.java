/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.suivierendezvous;

import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.healthadvisor.javafx.editstatutrdv.FXMLEditStatutRDVController;
import com.healthadvisor.service.impl.GestionRendezVous;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLSuivieRDVController implements Initializable {
    ObservableList<Rendez_Vous> list = FXCollections.observableArrayList();
    GestionRendezVous gr= new GestionRendezVous();

    @FXML
    private TableView<Rendez_Vous> tableView;
    @FXML
    private TableColumn<Rendez_Vous, Date> dateCol;
    @FXML
    private TableColumn<Rendez_Vous, String> docteurCol;
    @FXML
    private TableColumn<Rendez_Vous, String> patientCol;
    @FXML
    private TableColumn<Rendez_Vous, String> statutCol;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        loadData();
    }    
   private void initCol() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date_heure"));
        docteurCol.setCellValueFactory(new PropertyValueFactory<>("docteur"));
        patientCol.setCellValueFactory(new PropertyValueFactory<>("patient"));
        statutCol.setCellValueFactory(new PropertyValueFactory<>("statut_rendezvous"));
    }

    private void loadData() {
        list.clear();
        gr.ListRendez_Vous().stream().map((r) -> {
            String docteur=gr.RecupererMedecin(r.getMedecin_id());
            String patient=gr.RecupererPatient(r.getPatient_id());
            Date date=r.getDate_heure();
            String statut =r.getStatut_rendezvous();
            Rendez_Vous rdv =new Rendez_Vous();
            rdv.setId(r.getId());
            rdv.setDate_heure(date);
            rdv.setMedecin_id(r.getMedecin_id());
            rdv.setPatient_id(r.getPatient_id());
            rdv.setDocteur(docteur);
            rdv.setPatient(patient);
            rdv.setStatut_rendezvous(statut);
            return rdv;
        }).forEachOrdered((rdv) -> {
            list.add(rdv);
        });
     
        System.out.println();
        tableView.setItems(list);
    }
    
    Image img=new Image("/com/healthadvisor/ressources/question.png");
       Notifications notif=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Aucun Rendez_Vous n'est sélectionné")
                    .text("  Sélectionnez un Rendez_Vous")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
    Image imgsucces=new Image("/com/healthadvisor/ressources/checked.png");
       Notifications notifsucces=Notifications.create()
               .graphic(new ImageView(imgsucces))
                    .title("Suppression Rendez_Vous")
                    .text("Rendez-Vous Supprimé")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();

    @FXML
    private void modifierStatut(ActionEvent event) {
       //Fetch the selected row
        Rendez_Vous selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            notif.show();
            //AlertMaker.showErrorMessage("Aucun Rendez_Vous n'est sélectinoné", "Sélectionnez un Rendez_Vous !");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/healthadvisor/javafx/editstatutrdv/FXMLEditStatutRDV.fxml"));
            Parent parent = loader.load();

            FXMLEditStatutRDVController controller = (FXMLEditStatutRDVController) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Modifier Statut Rendez_Vous");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);

            stage.setOnCloseRequest((e) -> {
                refresh(new ActionEvent());
            });

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerStatut(ActionEvent event) {
        Rendez_Vous selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            //JFXButton b= new JFXButton("OK");
            //AlertMaker.showMaterialDialog(root, tableView, b, "Aucun Rendez_Vous n'est sélectinoné", null);
                       notif.show();

            //AlertMaker.showErrorMessage("Aucun Rendez_Vous n'est sélectinoné", "Sélectionnez un Rendez_Vous !");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression  Rendez_vous");
        alert.setContentText("Etes_Vous sur de supprimer le Rendez_Vous du patient " + selectedForDeletion.getPatient()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = gr.supprimerRendezVous(selectedForDeletion.getId());
            if (result) {
            notifsucces.show();
            //AlertMaker.showSimpleAlert("Rendez_vous supprimer", selectedForDeletion.getPatient()+ "a été supprimer avec succès");
                list.remove(selectedForDeletion);
            } else {
                AlertMaker.showSimpleAlert("Erreur", selectedForDeletion.getPatient()+ " n'a pas été supprimé");
            }
        } else {
            AlertMaker.showSimpleAlert("Suppression annulé", "Processus de suppression annulé");
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
                loadData();         
    }
    
}
