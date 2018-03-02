/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.Sondage;
import com.healthadvisor.entities.UserReponse;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.javafx.questionreponse.ConsulterQuestionController;
import com.healthadvisor.javafx.questionreponse.QuestionController;
import com.healthadvisor.service.impl.GestionReponse;
import com.healthadvisor.service.impl.GestionReponsesPossibles;
import com.healthadvisor.service.impl.GestionSondage;
import com.healthadvisor.service.impl.GestionStatistiques;
import com.healthadvisor.service.impl.GestionUserReponse;
import com.jfoenix.controls.JFXRadioButton;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
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
    public ToggleGroup g =new ToggleGroup();
    
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
        SondageText.setText(SondageUserController.sondageStatic.getNom());
        GestionReponsesPossibles grp = new GestionReponsesPossibles();
        
        for(int i=0;i< grp.ListReponsesPossibles(SondageUserController.sondageStatic.getId()).size();i++){
            
            JFXRadioButton r = new JFXRadioButton();
            r.setText(grp.ListReponsesPossibles(SondageUserController.sondageStatic.getId()).get(i).getReponse());
            r.setToggleGroup(g);
            r.setSelected(false);
            r.setUserData(grp.ListReponsesPossibles(SondageUserController.sondageStatic.getId()).get(i).getId_reponse());
            
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
        
        ScrollPane a = FXMLLoader.load(getClass().getResource("SondageUser.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
    }

    @FXML
    private void btnEnvoyerAction(ActionEvent event) {
        
        boolean test;
        String ch="";
        
        GestionStatistiques gs = new GestionStatistiques();
        test = gs.testReponseDeUserSurSondage(FXMLLoginController.pseudo, SondageUserController.sondageStatic.getId());
        GestionUserReponse gur = new GestionUserReponse();
        String x = gur.AfficherUserReponse(FXMLLoginController.pseudo, SondageUserController.sondageStatic.getId());
        if (test==true){
        
            switch (x){
                case "1": ch+="★";
                break;
                case "2": ch+="★★";
                break;
                case "3": ch+="★★★";
                break;
                case "4": ch+="★★★★";
                break;
                case "5": ch+="★★★★★";
                break;
            }
            
            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
            alerte.setTitle("Dialogue de confirmation");
            alerte.setHeaderText("Attention !");
            alerte.setContentText("Vous avez déjà répondu à ce sondage, voulez vous changer votre réponse ? \n votre réponse était : "+ch);
        
            Optional<ButtonType> result = alerte.showAndWait();
            if (result.get() == ButtonType.OK)
            {
            
            
            
            int id_ancienne_reponse =  gur.AfficherIdReponseUser(SondageUserController.sondageStatic.getId(),FXMLLoginController.pseudo);
            int id_nouvelle_reponse = (Integer)g.getSelectedToggle().getUserData();
            gur.updateUserReponse(FXMLLoginController.pseudo,id_ancienne_reponse,id_nouvelle_reponse);
            
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Dialogue d'information");
            a.setHeaderText("Succès !");
            a.setContentText("Votre réponse à été bien mise à jour ...");
            a.show();
            
            }else{
            
            }
        }  
        //FXMLLoginController.pseudo
        else{
        UserReponse ur = new UserReponse(FXMLLoginController.pseudo, (Integer)g.getSelectedToggle().getUserData() );
        GestionReponsesPossibles grp = new GestionReponsesPossibles();
        gur.ajouterUserReponse(ur);
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Dialogue d'information");
        a.setHeaderText("Succès !");
        a.setContentText("Votre réponse à été envoyée avec succès...");
        a.show();
                
        
        
        
        }
    
    }
}