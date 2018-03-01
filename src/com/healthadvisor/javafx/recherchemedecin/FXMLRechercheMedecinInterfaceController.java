/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.recherchemedecin;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javafx.affichermedecin.FXMLAfficherMedecinController;
import com.healthadvisor.javafx.inscrimedecin.ComboBoxAutoComplete;
import com.healthadvisor.javafx.routes.Routes;
import com.healthadvisor.javafx.suivierendezvous.AlertMaker;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLRechercheMedecinInterfaceController implements Initializable {

    @FXML
    private JFXComboBox<String> specialite;
    
    public static String spec=null;
    public static String nom=null;
    @FXML
    private JFXComboBox<String> rechercheNom;
    GestionMedecin gm= new GestionMedecin();
    GestionUtilisateur gu=new GestionUtilisateur();
    @FXML
    private JFXButton rdvNom;
    @FXML
    private JFXProgressBar progressRecherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        progressRecherche.setOpacity(0);
            String[] specialitelist={
"Allergologie",
"Andrologie",
"Chirurgie cardiaque",
"Chirurgie colorectale",
"Chirurgie générale",
"Chirurgie générale oncologique",
"Chirurgie générale pédiatrique",
"Chirurgie orthopédique",
"Chirurgie plastique",
"Chirurgie thoracique",
"Chirurgie vasculaire",
"Anesthésiologie",
"Angiologie",
"Cardiologie",
"Chirurgie",
"Dentisterie",
"Dermatologie",
"Endocrinologie",
"Gastroentérologie",
"Gériatrie",
"Gynécologie",
"Hématologie",
"Hépatologie",
"Infectiologie",
"Médecine générale",
"Médecine interne",
"Néonatologie",
"Néphrologie",
"Neurologie",
"Obstétrique",
"Odontologie",
"Oncologie",
"Ophtalmologie",
"Orthodontie",
"Orthopédie",
"Oto-rhino-laryngologie",
"Pédiatrie",
"Pneumologie",
"Psychiatrie",
"Radiologie",
"Radiothérapie",
"Rhumatologie",
"Urologie"
};
        ObservableList<String> sl=FXCollections.observableArrayList(specialitelist);
        specialite.setItems(sl);
        new ComboBoxAutoComplete<String>(specialite);
        String[] NomMedecins=new String[gm.ListMedecin().size()];
        System.out.println("Size List M"+gm.ListMedecin().size());
        for(int i=0;i<gm.ListMedecin().size();i++){
            System.out.println("Medecin "+i);
            Utilisateur u=gu.AfficherUtilisateurCin(gm.ListMedecin().get(i).getCin_user());
            NomMedecins[i]=u.getNom()+" "+u.getPrenom();
            
        }
        ObservableList<String> sn=FXCollections.observableArrayList(NomMedecins);
        rechercheNom.setItems(sn);
        new ComboBoxAutoComplete<String>(rechercheNom);
        
    }    

    @FXML
    private void rdvSpecialite(MouseEvent event) throws IOException {
       nom=null;
        spec=this.specialite.getValue();
 progressRecherche.setOpacity(1);   
          Timer tm=new Timer();
          tm.schedule(new TimerTask() {
              @Override
              public void run() {
                  Platform.runLater(()-> {
        AnchorPane affichermed;
                      try {
                          affichermed = FXMLLoader.load(getClass().getResource(Routes.AFFICHERMEDECIN));
                          FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,affichermed);
        progressRecherche.setOpacity(0);

                      } catch (IOException ex) {
                          Logger.getLogger(FXMLRechercheMedecinInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  });
              }
          }, 3000); 
     
        
        
        
    }

    @FXML
    private void rdvNomAction(MouseEvent event) {
        spec=null;
        System.out.println("Value"+this.rechercheNom.getValue());
        nom=this.rechercheNom.getValue().replaceAll(" ", "");
         try {
                   
        AnchorPane affichermed = FXMLLoader.load(getClass().getResource(Routes.AFFICHERMEDECIN));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,affichermed);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
}
