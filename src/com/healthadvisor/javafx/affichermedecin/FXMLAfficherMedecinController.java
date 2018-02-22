/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.affichermedecin;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.javafx.recherchemedecin.FXMLRechercheMedecinInterfaceController;
import com.healthadvisor.service.impl.GestionMedecin;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLAfficherMedecinController implements Initializable {
  private Pagination pagination;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initializePage(FXMLRechercheMedecinInterfaceController.spec);
    }    
    
    public void initializePage(String specialite){
        AnchorPane anchor = new AnchorPane();
    anchor.setPrefWidth(1000);
    anchor.setPrefHeight(600);
    anchor.setMinSize(anchor.USE_COMPUTED_SIZE, anchor.USE_COMPUTED_SIZE);
  
    ScrollPane p=new ScrollPane();
    p.setPrefSize(550, 600);

    p.setContent(createPageSpécialite(specialite));
    anchor.getChildren().add(p);
    Stage stage = new Stage(StageStyle.DECORATED);
    Scene scene = new Scene(anchor);
    stage.setScene(scene);
    stage.setTitle("List Medecin");
    stage.show();
    }
  public VBox createPage() {
    VBox box = new VBox();
        GestionMedecin gm=new GestionMedecin();
        for(Medecin m:gm.ListMedecin()){
       
        VBox element = new VBox();
        element.setPadding(new Insets(10, 50, 50, 50));
        element.setSpacing(10);
        element.setFillWidth(true);
        JFXButton prdv=new JFXButton("Prendre RDV");
        prdv.setPrefWidth(142);
        prdv.setPrefHeight(40);
        prdv.setLayoutX(254);
        prdv.setLayoutY(5);
        Rating r=new Rating();
        r.setPartialRating(true);
        r.setRating(3);
        Pane p=new Pane();
        p.setPrefWidth(410);
        p.setPrefHeight(49);
        p.setLayoutX(-1);
        p.setLayoutY(192);
        p.getChildren().add(prdv);
        p.getChildren().add(r);
            Label nom=new Label("Dr "+m.getLogin_med());
        nom.setPrefWidth(211);
        nom.setPrefHeight(32);
        nom.setMinWidth(nom.USE_COMPUTED_SIZE);
        nom.setLayoutX(168);
        nom.setLayoutY(32);
            Label specialite=new Label(m.getSpecialite());
        specialite.setPrefWidth(211);
        specialite.setPrefHeight(32);
        specialite.setLayoutX(168);
        specialite.setLayoutY(71); 
            Label adresse=new Label(m.getAdresse());
        adresse.setPrefWidth(211);
        adresse.setPrefHeight(32);
        adresse.setLayoutX(168);
        adresse.setLayoutY(111);
        Circle c=new Circle();
        c.setRadius(56);
        c.setLayoutX(70);
        c.setLayoutY(71);
        c.setFill(Color.AQUAMARINE);
            AnchorPane anc = new AnchorPane();
            anc.setPrefWidth(410);
            anc.setPrefHeight(241);
            anc.getChildren().addAll(p,nom,specialite,adresse,c);

      element.getChildren().add(anc);
      box.getChildren().add(element);
    }
  
    return box;
  }
    
  public VBox createPageSpécialite(String spec) {
    VBox box = new VBox();
        GestionMedecin gm=new GestionMedecin();
        for(Medecin m:gm.AfficherMedecinSpecialite(spec)){
       
        VBox element = new VBox();
        element.setPadding(new Insets(10, 50, 50, 50));
        element.setSpacing(10);
        element.setFillWidth(true);
        JFXButton prdv=new JFXButton("Prendre RDV");
        prdv.setPrefWidth(142);
        prdv.setPrefHeight(40);
        prdv.setLayoutX(254);
        prdv.setLayoutY(5);
        Rating r=new Rating();
        r.setPartialRating(true);
        r.setRating(3);
        Pane p=new Pane();
        p.setPrefWidth(410);
        p.setPrefHeight(49);
        p.setLayoutX(-1);
        p.setLayoutY(192);
        p.getChildren().add(prdv);
        p.getChildren().add(r);
            Label nom=new Label("Dr "+m.getLogin_med());
        nom.setPrefWidth(211);
        nom.setPrefHeight(32);
        nom.setMinWidth(nom.USE_COMPUTED_SIZE);
        nom.setLayoutX(168);
        nom.setLayoutY(32);
            Label specialite=new Label(m.getSpecialite());
        specialite.setPrefWidth(211);
        specialite.setPrefHeight(32);
        specialite.setLayoutX(168);
        specialite.setLayoutY(71); 
            Label adresse=new Label(m.getAdresse());
        adresse.setPrefWidth(211);
        adresse.setPrefHeight(32);
        adresse.setLayoutX(168);
        adresse.setLayoutY(111);
        Circle c=new Circle();
        c.setRadius(56);
        c.setLayoutX(70);
        c.setLayoutY(71);
        c.setFill(Color.AQUAMARINE);
            AnchorPane anc = new AnchorPane();
            anc.setPrefWidth(410);
            anc.setPrefHeight(241);
            anc.getChildren().addAll(p,nom,specialite,adresse,c);

      element.getChildren().add(anc);
      box.getChildren().add(element);
    }
  
    return box;
  }
    
}
