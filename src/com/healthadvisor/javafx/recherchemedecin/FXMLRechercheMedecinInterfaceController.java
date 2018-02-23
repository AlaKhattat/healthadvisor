/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.recherchemedecin;

import com.healthadvisor.javafx.affichermedecin.FXMLAfficherMedecinController;
import com.healthadvisor.javafx.inscrimedecin.ComboBoxAutoComplete;
import com.healthadvisor.javafx.routes.Routes;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField adresse2;
    @FXML
    private JFXTextField recherche;
    
    public static String spec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            String[] specialitelist={
"Anesthésiologie",
"Biochimie médicale",
"Cardiologie",
"Chirurgie cardiaque",
"Chirurgie colorectale",
"Chirurgie générale",
"Chirurgie générale oncologique",
"Chirurgie générale pédiatrique",
"Chirurgie orthopédique",
"Chirurgie plastique",
"Chirurgie thoracique",
"Chirurgie vasculaire",
"Dermatologie",
"Endocrinologie et métabolisme",
"Gastro-entérologie",
"Génétique médicale",
"Gériatrie",
"Gérontopsychiatrie",
"Hématologie",
"Hématologie/oncologie pédiatrique",
"Immunologie clinique et allergie",
"Maladies infectieuses",
"Médecine d'urgence",
"Médecine d'urgence pédiatrique",
"Médecine de l'adolescence",
"Médecine de soins intensifs",
"Médecine du travail",
"Médecine interne",
"Médecine interne générale",
"Médecine maternelle et fœtale",
"Médecine néonatale et périnatale",
"Médecine nucléaire",
"Médecine physique et réadaptation",
"Microbiologie médicale",
"Néphrologie",
"Neurochirurgie",
"Neurologie",
"Neuropathologie",
"Obstétrique et gynécologie",
"Oncologie gynécologique",
"Oncologie médicale",
"Ophtalmologie",
"Pathologie générale",
"Pathologie hématologique",
"Pathologie judiciaire",
"Pédiatrie",
"Pédiatrie du développement",
"Pneumologie",
"Psychiatrie",
"Psychiatrie légale",
"Radio-oncologie",
"Radiologie diagnostique",
"Rhumatologie",
"Santé publique et médecine préventive",
"Urologie"};
        ObservableList<String> sl=FXCollections.observableArrayList(specialitelist);
        specialite.setItems(sl);
        new ComboBoxAutoComplete<String>(specialite);
    }    

    @FXML
    private void rdvSpecialite(MouseEvent event) {
        spec=this.specialite.getValue();
        try {
         
            FXMLLoader loader=new FXMLLoader(getClass().getResource(Routes.AFFICHERMEDECIN)); 
            Parent root=loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    
    
    
}
