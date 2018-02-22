/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.UserReponse;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.javafx.questionreponse.QuestionController;
import com.healthadvisor.service.impl.GestionReponsesPossibles;
import com.healthadvisor.service.impl.GestionSondage;
import com.healthadvisor.service.impl.GestionUserReponse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class ConsulterSondageController implements Initializable {

    @FXML
    private Label labelSondage;
    @FXML
    private Label SondageText;
    @FXML
    private Button Retour;
    @FXML
    private AnchorPane AnchorID;
    @FXML
    private Button btnEnvoyer;
    ToggleGroup g =new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        VBox box = new VBox();
        box.setPadding(new Insets(10, 50, 50, 10));
        box.setSpacing(15);
        box.setPrefWidth(161);
        box.setPrefHeight(131);
        box.setLayoutX(46);
        box.setLayoutY(114);
        SondageText.setText(SondageController.sondage.getNom());
        GestionReponsesPossibles grp = new GestionReponsesPossibles();
        
        for(int i=0;i< grp.ListReponsesPossibles(SondageController.sondage.getId()).size();i++){
            
            RadioButton r = new RadioButton();
            r.setText(grp.ListReponsesPossibles(SondageController.sondage.getId()).get(i).getReponse());
            r.setToggleGroup(g);
            r.setSelected(false);
            r.setUserData(grp.ListReponsesPossibles(SondageController.sondage.getId()).get(i).getId_reponse());
            
            /*r.setOnAction((event) -> {
                System.out.println(r.getUserData());
            });*/
            
            box.getChildren().add(r);
            
            /*g.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
                
                public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggles, Toggle new_toggle){
                    if(g.getSelectedToggle()!= null){
                        System.out.println(g.getSelectedToggle().getUserData().toString());
                    }
                }
                
            });*/
        }
        AnchorID.getChildren().add(box);
        
    }    

    @FXML
    private void RetourBtnAction(ActionEvent event) throws IOException {
        
        SondageMain sm = new SondageMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Sondage.fxml"));
        Parent root=loader.load();
        Scene s = AnchorID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void btnEnvoyerAction(ActionEvent event) {
        
        UserReponse ur = new UserReponse(FXMLLoginController.pseudo, (Integer)g.getSelectedToggle().getUserData() );
        GestionReponsesPossibles grp = new GestionReponsesPossibles();
        GestionUserReponse gur = new GestionUserReponse();
        gur.ajouterUserReponse(ur);
        
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Dialogue d'information");
        alerte.setHeaderText("Succès !");
        alerte.setContentText("Votre réponse à été envoyée avec succès...");
        alerte.show();
        
        
        
    }
    
}
