/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.modifier_rdv;

import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionRendezVous;
import com.healthadvisor.service.impl.GestionUtilisateur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firassov
 */
public class ModifierRdvFXMLController implements Initializable {

    @FXML
    private VBox Vcontainer;
    static Rendez_Vous RDV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GestionRendezVous grdv =new GestionRendezVous();
        for(Rendez_Vous rdv :grdv.ListRendez_Vous()){
            HBox h =new HBox();
            h.minWidth(600);
            h.minHeight(100);
            h.setAlignment(Pos.CENTER);
            h.setSpacing(2);
            FontAwesomeIconView point=new FontAwesomeIconView();
            point.glyphNameProperty().setValue("CIRCLE");
            point.glyphSizeProperty().setValue(12);
            point.setLayoutX(5);
            point.setLayoutY(45);
            GestionPatient gp=new GestionPatient();
            GestionUtilisateur gu=new GestionUtilisateur();
            String nomPrenomDr=gu.AfficherUtilisateurCin(gp.AfficherPatientLogin(rdv.getMedecin_id()).getCin_user()).getNom()+" "+gu.AfficherUtilisateurCin(gp.AfficherPatientLogin(rdv.getMedecin_id()).getCin_user()).getPrenom();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyy à HH:mm");
            String date=simpleDateFormat.format(rdv.getDate_heure());
            Label description=new Label("Rendez Vous avec Dr "+nomPrenomDr+" Le "+date);
            description.setMinWidth(485);
            description.setMinHeight(100);
            description.prefWidth(485);
            description.prefHeight(100);
            description.setWrapText(true);
            FontAwesomeIconView X=new FontAwesomeIconView();
            X.setGlyphName("TIMES");
            X.setGlyphSize(25);
            Button annule=new Button();
            annule.setGraphic(X);
            annule.setUserData(rdv);
            annule.setOnMouseClicked((event) -> {
                Alert a =new Alert(Alert.AlertType.NONE,"Vous voulez annuler votre rendez vous\nNB : si le rendez vous a été valider par le Medecin vous ne pouvez pas depasser les 24 heures pour annuler",ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> result=a.showAndWait();
                if (result.get()==ButtonType.YES){
                    Rendez_Vous r=(Rendez_Vous)annule.getUserData();
                    if(r.getStatut_rendezvous().equals("VALIDE") && DifférenceDates(r.getDate_validation())>24){
                        System.out.println("RDV VALIDE");
                        Alert impo=new Alert(Alert.AlertType.ERROR,"Le rendez vous est Valider par votre medecin il y plus que 24 Heures");
                        impo.show();
                    }
                    else{
                        
                            grdv.supprimerRendezVous(r.getId());
                            try {
                            FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierRdvFXML.fxml"));
                            Parent root=loader.load();
                            annule.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ModifierRdvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
                else{
                    a.close();
                }
            });
            FontAwesomeIconView E=new FontAwesomeIconView();
            E.setGlyphName("PENCIL");
            E.setGlyphSize(25);
            Button modif=new Button();
            modif.setGraphic(E);
            modif.setOnMouseClicked((event) -> {
                Alert a =new Alert(Alert.AlertType.NONE,"Vous voulez modifier votre rendez vous\n NB : si le rendez vous a été valider par le Medecin vous ne pouvez pas depasser les 24 heures pour Modifier",ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> result=a.showAndWait();
                if (result.get()==ButtonType.YES){
                    Rendez_Vous r=(Rendez_Vous)annule.getUserData();
                    if(r.getStatut_rendezvous().equals("VALIDE")&& DifférenceDates(r.getDate_validation())>24){
                        System.out.println("RDV VALIDE"); 
                        Alert impo=new Alert(Alert.AlertType.ERROR,"Le rendez vous est Valider par votre medecin il y plus que 24 Heures");
                        impo.show();
                    }
                    else{
                        try {
                            //grdv.supprimerRendezVous(r.getId());
                            RDV=r;
                            FXMLLoader loader=new FXMLLoader(getClass().getResource("EditRdvFXML.fxml"));
                            Parent root=loader.load();
                            Scene s=new Scene(root);
                            Stage stage=new Stage();
                            stage.setScene(s);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ModifierRdvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    }
                else{
                    a.close();
                }
            });
            h.getChildren().add(point);
            h.getChildren().add(description);
            h.getChildren().add(annule);
            h.getChildren().add(modif);
            Vcontainer.getChildren().add(h);
        }
    }    
    public static long DifférenceDates(Date date_mise){
        long diff=0;
        try {
            DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd,hh:mm");
            Date date = new Date();
            Date date_sys= date_format.parse(date_format.format(date));
             diff = date_sys.getTime() - date_mise.getTime();
            System.out.println ("Hours: " + TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS));
             } catch (ParseException ex) {
            Logger.getLogger(ModifierRdvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
    }

    
}
