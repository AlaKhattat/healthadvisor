/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.gererutilisateur;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javafx.detailsm.FXMLDetailsMController;
import com.healthadvisor.javafx.detailsp.FXMLDetailsPController;
import com.healthadvisor.javafx.editstatutrdv.FXMLEditStatutRDVController;
import com.healthadvisor.javafx.suivierendezvous.AlertMaker;
import com.healthadvisor.javafx.suivierendezvous.LibraryAssistantUtil;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLGererUtilController implements Initializable {
  ObservableList<Patient> list = FXCollections.observableArrayList();
  ObservableList<Medecin> listM = FXCollections.observableArrayList();

    GestionPatient gp= new GestionPatient();
    GestionUtilisateur gu=new GestionUtilisateur();
    GestionMedecin gm=new GestionMedecin();
    @FXML
    private TableColumn<Patient, String> nomCol;
    @FXML
    private TableColumn<Patient, String> prenomCol;
    @FXML
    private TableColumn<Patient, String> sexeCol;
    @FXML
    private TableColumn<Patient, String> paysCol;
    @FXML
    private TableView<Patient> tableViewP;
    @FXML
    private TableColumn<Medecin, String> nomMCol;
    @FXML
    private TableColumn<Medecin, String> prenomMCol;
    @FXML
    private TableColumn<Medecin, String> sexeMCol;
    @FXML
    private TableView<Medecin> tableViewM;
    @FXML
    private TableColumn<Medecin, String> specialiteCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initColP();
        loadDataP();
        TableFilter<Patient> tableFilter = new TableFilter<>(tableViewP);
        tableFilter.setSearchStrategy((input,nomCol) -> {
        
    try {
        return nomCol.contains(input);
    } catch (Exception e) {
        return false;
    }
});
        initColM();
        loadDataM(); 
            TableFilter<Medecin> tableFilterM = new TableFilter<>(tableViewM);
        tableFilterM.setSearchStrategy((input,nomMCol) -> {
        
    try {
        return nomMCol.contains(input);
    } catch (Exception e) {
        return false;
    }
});
    }    
    
   private void initColP() {
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        paysCol.setCellValueFactory(new PropertyValueFactory<>("pays"));
    }
    private void loadDataP() {
         list.clear();
          try            {

         gp.AfficherQPatient().stream().map((r) -> {
             Medecin m=gm.AfficherMedecinLogin(r.getLogin());
             Utilisateur u=gu.AfficherUtilisateurCin(r.getCin_user());
             Patient p=new Patient();
             p.setLogin(r.getLogin());
             p.setNom(u.getNom());
             p.setPrenom(u.getPrenom());
             p.setPays(u.getPays());
             p.setCin_user(r.getCin_user());
             p.setSexe(u.getSexe());
             
             return p;
           
      }).forEachOrdered((p) -> {
          list.add(p);
      });
          }catch(Exception e){
              
          }
     
        tableViewP.setItems(list);
    }
    
     private void initColM() {
        nomMCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomMCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeMCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        specialiteCol.setCellValueFactory(new PropertyValueFactory<>("specialite"));
    }

    private void loadDataM() {
        listM.clear();
        gm.ListMedecin().stream().map((m) -> {
            Patient p=gp.AfficherPatientLogin(m.getLogin_med());
            Utilisateur u=gu.AfficherUtilisateurCin(p.getCin_user());
            Medecin med=new Medecin();
            med.setNom(u.getNom());
            med.setPrenom(u.getPrenom());
            med.setLogin_med(m.getLogin_med());
            med.setSexe(u.getSexe());
            med.setSpecialite(m.getSpecialite());
            med.setAdresse(m.getAdresse());
            med.setStatut_compte(m.getStatut_compte());
            med.setDiplome(m.getDiplome());
          return med;
      }).forEachOrdered((med) -> {
          listM.add(med);
      });
        tableViewM.setItems(listM);
    }
       Image img=new Image("/com/healthadvisor/ressources/question.png");
       Notifications notif=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Aucun Patient n'est sélectionné")
                    .text("  Sélectionnez un Patient SVP")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
    @FXML
    private void afficherDetailsP(ActionEvent event) {
             //Fetch the selected row
        Patient selectedForEdit = tableViewP.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
         
            notif.show();
            //AlertMaker.showErrorMessage("Aucun Rendez_Vous n'est sélectinoné", "Sélectionnez un Rendez_Vous !");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/healthadvisor/javafx/detailsp/FXMLDetailsP.fxml"));
            Parent parent = loader.load();

            FXMLDetailsPController controller = (FXMLDetailsPController) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Details Patient");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);

            stage.setOnCloseRequest((e) -> {
                refreshP(new ActionEvent());
            });

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 Image imgsucces=new Image("/com/healthadvisor/ressources/checked.png");
       Notifications notifsucces=Notifications.create()
               .graphic(new ImageView(imgsucces))
                    .title("Suppression Patient")
                    .text("Patient Supprimé")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
    @FXML
    private void SupprimerP(ActionEvent event) {
         Patient selectedForDeletion = tableViewP.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            //JFXButton b= new JFXButton("OK");
            //AlertMaker.showMaterialDialog(root, tableView, b, "Aucun Rendez_Vous n'est sélectinoné", null);
                       notif.show();

            //AlertMaker.showErrorMessage("Aucun Rendez_Vous n'est sélectinoné", "Sélectionnez un Rendez_Vous !");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression  Patient");
        alert.setContentText("Etes_Vous sur de supprimer le patient " + selectedForDeletion.getNom()+" "+selectedForDeletion.getPrenom()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = gp.SupprimerPatientLogin(selectedForDeletion.getLogin());
            if (result) {
            notifsucces.show();
            list.remove(selectedForDeletion);
            } else {
                AlertMaker.showSimpleAlert("Erreur", selectedForDeletion.getNom()+" "+selectedForDeletion.getPrenom()+ " n'a pas été supprimé");
            }
        } else {
            AlertMaker.showSimpleAlert("Suppression annulé", "Processus de suppression annulé");
        }
    }

    @FXML
    private void refreshP(ActionEvent event) {
                        loadDataP();

    }

    @FXML
    private void afficherDetailsM(ActionEvent event) {
                 //Fetch the selected row
        Medecin selectedForEdit = tableViewM.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            notif.show();
            //AlertMaker.showErrorMessage("Aucun Rendez_Vous n'est sélectinoné", "Sélectionnez un Rendez_Vous !");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/healthadvisor/javafx/detailsm/FXMLDetailsM.fxml"));
            Parent parent = loader.load();

            FXMLDetailsMController controller = (FXMLDetailsMController) loader.getController();
            controller.inflateUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Details Medecin");
            stage.setScene(new Scene(parent));
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);

            stage.setOnCloseRequest((e) -> {
                refreshP(new ActionEvent());
            });

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void SupprimerM(ActionEvent event) {
        Medecin selectedForDeletion = tableViewM.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            //JFXButton b= new JFXButton("OK");
            //AlertMaker.showMaterialDialog(root, tableView, b, "Aucun Rendez_Vous n'est sélectinoné", null);
            notif.show();
            //AlertMaker.showErrorMessage("Aucun Rendez_Vous n'est sélectinoné", "Sélectionnez un Rendez_Vous !");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression  Medecin");
        alert.setContentText("Etes_Vous sur de supprimer le medecin " + selectedForDeletion.getNom()+" "+selectedForDeletion.getPrenom()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = gm.SupprimerMedecinLogin(selectedForDeletion.getLogin_med());
            if (result) {
            notifsucces.show();
            listM.remove(selectedForDeletion);
            } else {
                AlertMaker.showSimpleAlert("Erreur", selectedForDeletion.getNom()+" "+selectedForDeletion.getPrenom()+ " n'a pas été supprimé");
            }
        } else {
            AlertMaker.showSimpleAlert("Suppression annulé", "Processus de suppression annulé");
        }
    }

    @FXML
    private void refreshM(ActionEvent event) {
                        loadDataM();
    }
    
}
