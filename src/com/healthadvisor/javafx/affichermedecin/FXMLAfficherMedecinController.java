/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.affichermedecin;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javafx.recherchemedecin.FXMLRechercheMedecinInterfaceController;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import gmapsfx.GoogleMapView;
import gmapsfx.MapComponentInitializedListener;
import gmapsfx.javascript.event.UIEventType;
import gmapsfx.javascript.object.GoogleMap;
import gmapsfx.javascript.object.InfoWindow;
import gmapsfx.javascript.object.InfoWindowOptions;
import gmapsfx.javascript.object.LatLong;
import gmapsfx.javascript.object.MapOptions;
import gmapsfx.javascript.object.MapTypeIdEnum;
import gmapsfx.javascript.object.Marker;
import gmapsfx.javascript.object.MarkerOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import netscape.javascript.JSObject;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLAfficherMedecinController implements  Initializable, MapComponentInitializedListener {
  private Pagination pagination;
  private Medecin medecin;
  public static Medecin med;
      @FXML
    private AnchorPane anchorPane;
    /**
     * Initializes the controller class.
     */
    GestionUtilisateur gu= new GestionUtilisateur();
    GestionPatient gp= new GestionPatient();
    GestionMedecin gm= new GestionMedecin();
      private GoogleMap map;
      GoogleMapView mapView=new GoogleMapView();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(FXMLRechercheMedecinInterfaceController.spec!=null)
        try {
            initializePage(FXMLRechercheMedecinInterfaceController.spec);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherMedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        else {
        if(FXMLRechercheMedecinInterfaceController.nom!=null)
        {initializePageNOM(FXMLRechercheMedecinInterfaceController.nom);
        }
        }
    }    
    
    public void initializePage(String specialite) throws IOException{
        mapView.setPrefWidth(348);
        mapView.setPrefHeight(397);
        mapView.setLayoutX(633);
        mapView.setLayoutY(66);
        AnchorPane anchor = new AnchorPane();
    anchor.setPrefWidth(1000);
    anchor.setPrefHeight(600);
    anchor.setMinSize(anchor.USE_COMPUTED_SIZE, anchor.USE_COMPUTED_SIZE);
  
    ScrollPane p=new ScrollPane();
    p.setPrefSize(620, 600);
if(FXMLRechercheMedecinInterfaceController.spec==null){
    p.setContent(createPage());
    }
else{
    p.setContent(createPageSpécialite(specialite));

    anchor.getChildren().addAll(p,mapView);
    anchorPane.getChildren().add(anchor);
  /*  Stage stage = new Stage(StageStyle.DECORATED);
    Scene scene = new Scene(anchor);
    stage.setScene(scene);
    stage.setTitle("List Medecin");
    stage.show();*/
    }
    }
    
        public void initializePageNOM(String nomprenom){
        FXMLRechercheMedecinInterfaceController.spec=null;
        mapView.setPrefWidth(348);
        mapView.setPrefHeight(397);
        mapView.setLayoutX(618);
        mapView.setLayoutY(66);
        AnchorPane anchor = new AnchorPane();
    anchor.setPrefWidth(1000);
    anchor.setPrefHeight(600);
    anchor.setMinSize(anchor.USE_COMPUTED_SIZE, anchor.USE_COMPUTED_SIZE);
  
    ScrollPane p=new ScrollPane();
    p.setPrefSize(700, 600);
if(FXMLRechercheMedecinInterfaceController.nom==null)
    p.setContent(createPage());
else
    p.setContent(createPageNom(nomprenom));


    anchor.getChildren().addAll(p,mapView);
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
        prdv.setLayoutX(391);
        prdv.setLayoutY(6);
        prdv.setUserData(m);
        prdv.setOnMouseClicked((event) -> {
            med=(Medecin)prdv.getUserData();
            
            try {
                Parent root= FXMLLoader.load(getClass().getResource("/com/healthadvisor/javafx/prendrerdv/PrendreRDVFXML.fxml"));
                Scene scene = new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLAfficherMedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        });
        JFXButton position=new JFXButton("Afficher Cabinet");
        position.setPrefWidth(156);
        position.setPrefHeight(40);
        position.setLayoutX(221);
        position.setLayoutY(6);
        AfficherPosition(position, m);
        Rating r=new Rating();
        r.setPartialRating(true);
        r.setRating(3);
        Pane p=new Pane();
        p.setPrefWidth(547);
        p.setPrefHeight(49);
        p.setLayoutX(0);
        p.setLayoutY(192);
        p.getChildren().add(position);
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
    
  public VBox createPageSpécialite(String spec) throws FileNotFoundException, IOException {
           FXMLRechercheMedecinInterfaceController.nom=null;

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
        prdv.setLayoutX(391);
        prdv.setLayoutY(6);
        prdv.setUserData(m);
        prdv.setOnMouseClicked((event) -> {
            med=(Medecin)prdv.getUserData();
            
            try {
                Parent root= FXMLLoader.load(getClass().getResource("/com/healthadvisor/javafx/prendrerdv/PrendreRDVFXML.fxml"));
                Scene scene = new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLAfficherMedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        });
        JFXButton position=new JFXButton("Afficher Cabinet");
        position.setPrefWidth(156);
        position.setPrefHeight(40);
        position.setLayoutX(221);
        position.setLayoutY(6);
        AfficherPosition(position, m);
        Rating r=new Rating();
        r.setPartialRating(true);
        r.setRating(3);
        Pane p=new Pane();
        p.setPrefWidth(547);
        p.setPrefHeight(49);
        p.setLayoutX(0);
        p.setLayoutY(192);
        p.getChildren().add(position);
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
         FileInputStream input;          
            input = new FileInputStream(m.getPhoto_profile());
            Image img_profile = SwingFXUtils.toFXImage(ImageIO.read(input), null);
        Circle c=new Circle();
        c.setRadius(56);
        c.setLayoutX(70);
        c.setLayoutY(71);
        c.setFill(Color.AQUAMARINE);
        c.setFill(new ImagePattern(img_profile));
            AnchorPane anc = new AnchorPane();
            anc.setPrefWidth(410);
            anc.setPrefHeight(241);
            anc.getChildren().addAll(p,nom,specialite,adresse,c);

      element.getChildren().add(anc);
      box.getChildren().add(element);
    }
  
    return box;
  }

    @Override
    public void mapInitialized() {
  
        System.out.println("Initialisation MAP ...");
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(medecin.getLat_p(),medecin.getLong_p()))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);
                    //Add markers to the map
            System.out.println("Affichage Position Medecin ....");
            System.out.println(medecin);
            System.out.println("long"+medecin.getLong_p());
            LatLong medecinlocation = new LatLong(medecin.getLat_p(),medecin.getLong_p());
        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(medecinlocation);
                Marker medecinMarker = new Marker(markerOptions1);
        map.addMarker( medecinMarker );
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        Utilisateur u=gu.AfficherUtilisateurCin(medecin.getCin_user());
         System.out.println(u);
         infoWindowOptions.content("<div style='float:left;height:70px;width:70px'><img src='https://image.flaticon.com/icons/svg/607/607414.svg'></div><div style='float:right; padding: 10px;'><b> Dr"+u.getNom()+" "+u.getPrenom()+"</b><br/>"+medecin.getAdresse()+"<br/> "+u.getPays()+","+u.getVille()+"</div>" );  

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, medecinMarker);
        
    }
    
    //fonction prendre rdv
    public void AfficherPosition(JFXButton btn,Medecin m){
          System.out.println("Afficher Position ... "+m);
          btn.setOnAction((event) -> {
          System.out.println("Recuperation Position ..."); 
          medecin=m;
          mapInitialized();
      });
    }
    
      
  public VBox createPageNom(String nomprenom) {    
        VBox box = new VBox();
        GestionMedecin gm=new GestionMedecin();
        for(Medecin m:gm.AfficherMedecinSnomprenom(nomprenom)){
       
        VBox element = new VBox();
        element.setPadding(new Insets(10, 50, 50, 50));
        element.setSpacing(10);
        element.setFillWidth(true);
        JFXButton prdv=new JFXButton("Prendre RDV");
        prdv.setPrefWidth(142);
        prdv.setPrefHeight(40);
        prdv.setLayoutX(391);
        prdv.setLayoutY(6);
        prdv.setUserData(m);
        prdv.setOnMouseClicked((event) -> {
            med=(Medecin)prdv.getUserData();
            
            try {
                Parent root= FXMLLoader.load(getClass().getResource("/com/healthadvisor/javafx/prendrerdv/PrendreRDVFXML.fxml"));
                Scene scene = new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLAfficherMedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        });
        JFXButton position=new JFXButton("Afficher Cabinet");
        position.setPrefWidth(156);
        position.setPrefHeight(40);
        position.setLayoutX(221);
        position.setLayoutY(6);
        AfficherPosition(position, m);
        Rating r=new Rating();
        r.setPartialRating(true);
        r.setRating(3);
        Pane p=new Pane();
        p.setPrefWidth(547);
        p.setPrefHeight(49);
        p.setLayoutX(0);
        p.setLayoutY(192);
        p.getChildren().add(position);
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
