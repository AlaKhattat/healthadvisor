/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.Sondage;
import com.healthadvisor.service.impl.GestionReponsesPossibles;
import com.healthadvisor.service.impl.GestionSondage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class AjouterSondageController implements Initializable {

    @FXML
    private AnchorPane paneID;
    @FXML
    private Label labelID;
    @FXML
    private TextArea textAreaID;
    @FXML
    private Button btnPublier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnPublierAction(ActionEvent event) throws IOException {
        
        
        if(textAreaID.getText().length()<30){
            Alert alerte = new Alert(Alert.AlertType.WARNING);
            alerte.setTitle("Dialogue d'erreur");
            alerte.setHeaderText("Attention !");
            alerte.setContentText("Votre sondage doit avoir au mois 30 caractères...");
            alerte.show();
        }
        
        else{
        
            GestionSondage gs = new GestionSondage();
            Sondage s = new Sondage(0, textAreaID.getText());
            gs.ajouterSondage(s);

            //int x = gs.afficherIdSondage(textAreaID.getText());
            
            int x = gs.afficherIdSondage(s.getNom());

            GestionReponsesPossibles grp = new GestionReponsesPossibles();

            grp.ajouterReponsesPossibles(0,"1", x);
            grp.ajouterReponsesPossibles(0,"2", x);
            grp.ajouterReponsesPossibles(0,"3", x);
            grp.ajouterReponsesPossibles(0,"4", x);
            grp.ajouterReponsesPossibles(0,"5", x);


            FXMLLoader loader=new FXMLLoader(getClass().getResource("SondageAdmin.fxml"));
            Parent root=loader.load();
            Scene sc = paneID.getScene();
            sc.setRoot(root);
        }
    }

    
    
}
