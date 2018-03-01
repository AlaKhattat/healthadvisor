/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.geolocalisation;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Firassov
 */
public class MapsFXMLController implements Initializable {
    WebView webView ;
    WebEngine webEngine;
    @FXML
    private JFXTextField seachPlacestxt;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem menuItem;
    @FXML
    private JFXRadioButton RdBtnPharmacie;
    @FXML
    private JFXRadioButton RdBtnHopital;
    @FXML
    private JFXRadioButton RdBtnDocteur;
    @FXML
    private JFXRadioButton RdBtnPhysio;
    @FXML
    private JFXRadioButton RdBtnGym;
    @FXML
    private JFXRadioButton RdBtnSpa;
    @FXML
    private StackPane stackPane;
    @FXML
    private ToggleGroup toggleGrp;
    private int Markercount;
    private int nbrRequest;
    private double lat;
    private double lng;
    @FXML
    private Button btnClear;
    /***************************ON Action Methods*********************************************/
    @FXML
    private void SearchPlaceskeyPressed(javafx.scene.input.KeyEvent event) {
        String ch="";
             try {
    URL urll = new URL("https://maps.googleapis.com/maps/api/place/autocomplete/json?location=34.0,9.0&radius=550000&strictbounds&language=fr&input="+seachPlacestxt.getText().replaceAll(" ", "%20")+"&key=AIzaSyAEYqaQdCb6HLtaY-As2thm_np77ZhtEOo");
    
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urll.openStream(), "UTF-8"))) {
        for (String line; (line = reader.readLine()) != null;) {
           ch=ch+line;
        }
    }   catch (IOException ex) {
            System.out.println(ex);
        }
                 JSONObject obj = new JSONObject(ch);
                 JSONArray arr=obj.getJSONArray("predictions");
                 ArrayList<MenuItem> listItems=new ArrayList<>();
                    for (int i = 0; i < arr.length(); i++)
                    {
                        MenuItem m =new MenuItem(arr.getJSONObject(i).getString("description"));
                        String placeID=arr.getJSONObject(i).getString("place_id");
                        m.setOnAction((event2) -> {
                            
                            seachPlacestxt.setText(m.getText());
                            String ch2="";
                            try {
                                URL url2 =new URL("https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeID+"&key=AIzaSyAEYqaQdCb6HLtaY-As2thm_np77ZhtEOo");
                                
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream(), "UTF-8"))) {
                                for (String line; (line = reader.readLine()) != null;) {
                                   ch2=ch2+line;
                                }
                            }   catch (IOException ex) {
                                    System.out.println(ex);
                                }
                            JSONObject obj2 = new JSONObject(ch2);
                 lat=obj2.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                 lng=obj2.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                                webEngine.executeScript("map.setCenter({lat: "+lat+",lng:"+lng+"});\n" +
                                                        "map.setZoom(14);");
                                Markercount=PlacesMaker(lat, lng, webEngine,toggleGrp.getSelectedToggle().getUserData().toString(),Markercount);
                                nbrRequest=1;
                            } catch (MalformedURLException ex) {
                                Logger.getLogger(MapsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                           
                        });
                        m.setUserData(arr.getJSONObject(i).getString("place_id"));
                        listItems.add(m);
                    }
                    contextMenu.getItems().clear();
                    contextMenu.getItems().addAll(listItems);
                    contextMenu.show(seachPlacestxt, Side.BOTTOM, 0, 0);
    }catch(MalformedURLException | JSONException e){
                 System.out.println(e);
    }  
    }
    @FXML
    private void menuvalidationAction(Event event) {
        seachPlacestxt.setText(menuItem.getText());
    }
    @FXML
    private void onBtnClearAction(ActionEvent event) {
        if(Markercount>0){
        webEngine.executeScript("deleteMarkers();");
        }
        seachPlacestxt.setText("");
        Markercount=0;
        nbrRequest=0;
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Markercount=0;
        nbrRequest=0;
        lat=0;
        lng=0;
        webView = new WebView();
        webEngine = webView.getEngine();
        URL urlGoogleMaps = getClass().getResource("Map.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        stackPane.getChildren().add(webView);
        RdBtnPharmacie.setUserData("pharmacy");
        RdBtnHopital.setUserData("hospital");
        RdBtnDocteur.setUserData("doctor");
        RdBtnGym.setUserData("gym");
        RdBtnPhysio.setUserData("physiotherapist");
        RdBtnSpa.setUserData("spa");
        toggleGrp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
           public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
        if (nbrRequest>0) {
        Markercount=PlacesMaker(lat, lng, webEngine,toggleGrp.getSelectedToggle().getUserData().toString(),Markercount);    
        }
      } 
        });

    }

    
    
public static int PlacesMaker(Double lat,Double lng,WebEngine webEngine,String type,int markercount){
    
                   String ch="";
             try {
    URL urll = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat+","+lng+"&radius=2000&types="+type+"&key=AIzaSyAEYqaQdCb6HLtaY-As2thm_np77ZhtEOo");
        

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(urll.openStream(), "UTF-8"))) {
        for (String line; (line = reader.readLine()) != null;) {
           ch=ch+line;
        }
    }   catch (IOException ex) {
            System.out.println(ex);
        }
        //parcourir JSON
        JSONObject obj = new JSONObject(ch);
        JSONArray arr=obj.getJSONArray("results");
        for (int i = 0; i < arr.length(); i++)
        {
            
            
            webEngine.executeScript("var marker"+markercount+i+" = new google.maps.Marker({position: new google.maps.LatLng("+arr.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lat")+","
                    +arr.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").getDouble("lng")+"),"
                    + "map: map,"
                    + "draggable: false,"
                    + "icon: 'img/"+type+".png'});"
                    + "markers.push(marker"+markercount+i+");"
                    + "var infobulle"+markercount+i+" = new google.maps.InfoWindow({"
                    + "content: \""+arr.getJSONObject(i).getString("name")+"\"});"
                    + "google.maps.event.addListener(marker"+markercount+i+", 'mouseover', function() {"
                    + "infobulle"+markercount+i+".open(map, marker"+markercount+i+");"
                    + "});"
                    + "google.maps.event.addListener(marker"+markercount+i+", 'mouseout', function() {"
                    + "infobulle"+markercount+i+".close();"
                    + "});");
            markercount++;
            
        }
                
    }  catch(MalformedURLException | JSONException e){
                 System.out.println(e);
    }  
             return markercount;
    }

    
    

    
}
