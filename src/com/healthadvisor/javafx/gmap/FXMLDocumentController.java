/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.gmap;
import com.healthadvisor.javafx.inscrimedecin.FXMLInscriMedecinController;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import netscape.javascript.JSObject;

/**
 *
 * @author khattout
 */
public class FXMLDocumentController implements Initializable, MapComponentInitializedListener {
    
    
    private GoogleMap map;

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
         infoWindowOption.content("<h2>Dr Khattat Ala</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );  

        InfoWindow fredWilkeInfoWindo = new InfoWindow(infoWindowOption);
            map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            
            map.clearMarkers();
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
                FXMLInscriMedecinController.LONG_P=ll.getLongitude();
                FXMLInscriMedecinController.LAT_P=ll.getLatitude();
            //lblClick.setText(ll.toString());
            MarkerOptions  markerOpt = new MarkerOptions();
                markerOpt.position(ll);
            
            Marker myMark = new Marker(markerOpt);
            map.addMarker(myMark);   
            
       
        fredWilkeInfoWindo.open(map, myMark);
        
        });
    }
 

    @FXML
    private void validerPositionAction(MouseEvent event) {
    }
}