/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.gmap;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javafx.inscrimedecin.FXMLInscriMedecinController;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import gmapsfx.GoogleMapView;
import gmapsfx.MapComponentInitializedListener;
import gmapsfx.javascript.event.GMapMouseEvent;
import gmapsfx.javascript.event.UIEventType;
import gmapsfx.javascript.object.GoogleMap;
import gmapsfx.javascript.object.InfoWindow;
import gmapsfx.javascript.object.InfoWindowOptions;
import gmapsfx.javascript.object.LatLong;
import gmapsfx.javascript.object.LatLongBounds;
import gmapsfx.javascript.object.MapOptions;
import gmapsfx.javascript.object.MapTypeIdEnum;
import gmapsfx.javascript.object.Marker;
import gmapsfx.javascript.object.MarkerOptions;
import gmapsfx.service.geocoding.GeocoderStatus;
import gmapsfx.service.geocoding.GeocodingResult;
import gmapsfx.service.geocoding.GeocodingService;
import gmapsfx.service.geocoding.GeocodingServiceCallback;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import netscape.javascript.JSObject;
import org.controlsfx.control.Notifications;

/**
 *
 * @author khattout
 */
public class FXMLDocumentController implements Initializable, MapComponentInitializedListener {
    
    
    private GoogleMap map;
    GestionPatient gp= new GestionPatient();
    GestionMedecin gm= new GestionMedecin();
    GestionUtilisateur gu= new GestionUtilisateur();
    @FXML
    private Label label;
    @FXML
    private GoogleMapView  mapView;
    @FXML
    private JFXButton validerPosition;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInitializedListener(this);
    }    

    @Override
    public void mapInitialized() {
      //modifier 
            Utilisateur u=gu.AfficherUtilisateurCin(FXMLLoginController.Identifiant);
           

        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(36.8189700, 10.1657900))
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
    
       
        
    
        
         InfoWindowOptions infoWindowOption = new InfoWindowOptions();
         infoWindowOption.content("<div style='float:left;height:70px;width:70px'><img src='https://image.flaticon.com/icons/svg/607/607414.svg'></div><div style='float:right; padding: 10px;'><b> Dr"+u.getNom()+" "+u.getPrenom()+"</b></div>" );  
  

            InfoWindow fredWilkeInfoWindo = new InfoWindow(infoWindowOption);
            map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            
            map.clearMarkers();
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            //lblClick.setText(ll.toString());
            //Recuperer longitude altitude
                FXMLInscriMedecinController.LONG_P=ll.getLongitude();
                FXMLInscriMedecinController.LAT_P=ll.getLatitude();
                FXMLLoginController.LAT_P_Co=ll.getLatitude();
                FXMLLoginController.LONG_P_Co=ll.getLongitude();
            MarkerOptions  markerOpt = new MarkerOptions();
            markerOpt.position(ll);
            
            Marker myMark = new Marker(markerOpt);
            map.addMarker(myMark);   
            
       
        fredWilkeInfoWindo.open(map, myMark);
         Image imgsucces=new Image("/com/healthadvisor/ressources/checked.png");
       Notifications notifsucces=Notifications.create()
               .graphic(new ImageView(imgsucces))
                    .title("Ajout Position")
                    .text("Votre Position a été recuperer avec sucés")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
       notifsucces.show();
        });
    }
 

    @FXML
    private void validerPositionAction(MouseEvent event) {
    }
}
