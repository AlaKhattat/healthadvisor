/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.analysesymptome;


import com.healthadvisor.entities.AccessToken;
import com.healthadvisor.entities.BodyParts;
import com.healthadvisor.entities.Maladie;
import com.healthadvisor.entities.SubBodyParts;
import com.healthadvisor.entities.Symptome;
import com.healthadvisor.service.impl.GestionBodyParts;
import com.healthadvisor.service.impl.GestionMaladie;
import com.healthadvisor.service.impl.GestionSubBodyPartSymptome;
import com.healthadvisor.service.impl.GestionSubBodyParts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firassov
 */
public class SymptomeFXMLController implements Initializable {

    @FXML
    private JFXTextField anne;

    @FXML
    private JFXComboBox<String> sexe;

    @FXML
    private JFXComboBox<BodyParts> bodypart;
    
    @FXML
    private JFXComboBox<SubBodyParts> subbodypart;
    
    @FXML
    private JFXListView<Symptome> listsymptomes;
    
    @FXML
    private JFXButton btnValider;
    
    @FXML
    private Label lb1;

    @FXML
    private Label lb2;

    @FXML
    private Label lb3;

    @FXML
    private Label lb4;

    @FXML
    private Label lb5;
    @FXML
    private Label lb6;
    
    @FXML
    private JFXButton btnAjouter;
     
    @FXML
    private FlowPane PaneBox;

    @FXML
    private Button btnEnvoyer;
    public static ArrayList<Maladie> ResultatAnalyse;
    private int nbtag;
      
      
    @FXML
    void bodypartselected(ActionEvent event) {
        int idbodypart = bodypart.getSelectionModel().getSelectedItem().getId();
System.out.println(idbodypart);
if(subbodypart.disableProperty().getValue()==true){
    subbodypart.setDisable(false);
}
        GestionSubBodyParts gsbp=new GestionSubBodyParts();
        ArrayList<SubBodyParts>lsbp=gsbp.AfficherSubBodyParts(idbodypart);
        ObservableList<SubBodyParts> olsbp = FXCollections.observableArrayList();
        for(SubBodyParts x : lsbp){
            SubBodyParts e=new SubBodyParts(x.getId(), x.getNom());
            olsbp.add(e);
        }
        subbodypart.setItems(olsbp);
    }
    
    @FXML
    void subbodypartselected(ActionEvent event) {
        String gender="";
        if(sexe.getValue().equals("Homme"))
            gender="man";
        else if(sexe.getValue().equals("Femme"))
            gender="woman";
        else if(sexe.getValue().equals("Garçon"))
            gender="boy";
        else
            gender="girl";
        if(subbodypart.getSelectionModel().getSelectedItem()!=null){
        int output = subbodypart.getSelectionModel().getSelectedItem().getId();
        System.out.println(output);
        if(listsymptomes.isDisabled()){
            listsymptomes.setDisable(false);
        }
                GestionSubBodyPartSymptome gsbps=new GestionSubBodyPartSymptome();
                ArrayList<Symptome>ls=gsbps.AfficherSubBodySymptome(output,gender);
                ObservableList<Symptome> ols = FXCollections.observableArrayList();
                for(Symptome x : ls){
                    Symptome e=new Symptome(x.getId(), x.getNom());
                    ols.add(e);
                }
                listsymptomes.setItems(ols);
                 }
        else{
            listsymptomes.getItems().clear();
        }
    }
    @FXML
    void btnSuivantAction(ActionEvent event) {
        
        
        if(!anne.getText().equals("")&& sexe.getValue()!=null){
                if(Integer.parseInt(anne.getText())>Calendar.getInstance().get(Calendar.YEAR) || Integer.parseInt(anne.getText())<1900){
            Alert ageerror =new Alert(Alert.AlertType.ERROR, "Année de naissance incorrecte!!", ButtonType.OK);
            ageerror.show();
        }
                else{
        if(anne.disableProperty().getValue()==true && sexe.disableProperty().getValue()==true){
        anne.setDisable(false);
        sexe.setDisable(false);
        lb1.setOpacity(0);
        lb2.setOpacity(0);
        lb3.setOpacity(0);
        lb4.setOpacity(0);
        lb5.setOpacity(0);
        lb6.setOpacity(0);
        bodypart.setOpacity(0);
        subbodypart.setOpacity(0);
        listsymptomes.setOpacity(0);
        btnAjouter.setOpacity(0);
        PaneBox.setOpacity(0);
        btnValider.setText("Suivant");
        }
        else{
        anne.setDisable(true);
        sexe.setDisable(true); 
        lb1.setOpacity(1.0);
        lb2.setOpacity(1.0);
        lb3.setOpacity(1.0);
        lb4.setOpacity(1.0);
        lb5.setOpacity(1.0);
        lb6.setOpacity(1.0);
        bodypart.setOpacity(1.0);
        subbodypart.setOpacity(1.0);
        listsymptomes.setOpacity(1.0);
        btnAjouter.setOpacity(1.0);
        PaneBox.setOpacity(1.0);
        btnValider.setText("Précédent");
        }
        }
        }
        else{
            System.out.println("Erreur");
            Alert a =new Alert(Alert.AlertType.ERROR, "Remlir les champs Année de naissance et Sexe", ButtonType.OK);
            a.show();
        }
    }
    @FXML
    void btnAjouterAction(ActionEvent event) {
        btnEnvoyer.setOpacity(1);
        btnEnvoyer.setDisable(false);
        ObservableList ol=listsymptomes.getSelectionModel().getSelectedItems();
        for(Object x : ol){
            nbtag++;
            Tag t=new Tag((Symptome)x);
            t.setUserData(x);
            /*Button b =new Button();
            b.setText(x.toString()+" X");*/
            t.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    nbtag--;
                    PaneBox.getChildren().remove(t);
                    if(nbtag==0){
                        btnEnvoyer.setOpacity(0);
                    }
                }
                
            });
            PaneBox.getChildren().add(t);
            
        }
    }
      @FXML
    void listviewelementselected(MouseEvent event) {
        btnAjouter.setDisable(false);
    }
    @FXML
    void btnEnvoyerAction(ActionEvent event) throws IOException{
        String Symptomes="";
        ObservableList<Node>ols=PaneBox.getChildren();
        for(Node x : ols){
            Symptome s=(Symptome)x.getUserData();
           Symptomes=Symptomes+"\""+s.getId()+"\",";
        }
         StringBuilder sb = new StringBuilder(Symptomes);
         sb.delete(Symptomes.length()-1,Symptomes.length());
         Symptomes=sb.toString();
        System.out.println(Symptomes);
        GestionMaladie gm =new GestionMaladie();
        AccessToken token=new AccessToken();
        ArrayList<Maladie> l=gm.Diagnostique(token.getToken(), Integer.parseInt(anne.getText()), sexe.getValue(), Symptomes);
        ResultatAnalyse=l;
        ScrollPane a=FXMLLoader.load(getClass().getResource("ResultatAnalyseFXML.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,a);
        
       /* String Result="";
        for(Maladie x : l){
            Result=Result+"Maladie : "+x.getNom()+" Precision : "+x.getPrecision()+"%\n";
            Result=Result+"Spéciaite de la maladie : \n";
            for(String y : x.getListSpécialite()){
                Result=Result+y+"\n";
            }
        }
        Alert a =new Alert(Alert.AlertType.INFORMATION, Result, ButtonType.OK);
        a.show();*/
    }
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        nbtag=0;
        /*ResultatAnalyse=new ArrayList<>();
        ArrayList<String> list=new ArrayList<>();
        list.add("Cardiologie");
        list.add("Dentisterie");
        list.add("Chirurgie");
        list.add("Infectiologie");
        Maladie m =new Maladie("Maladie xxyyzz",75,list);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);*/
        listsymptomes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        PaneBox.setVgap(4);
        PaneBox.setHgap(4);
        String[] sexelist={"Homme","Femme","Garçon","Fille"};
        ObservableList<String> ObservListsexe = FXCollections.observableArrayList(sexelist);
        sexe.setItems(ObservListsexe);
        ObservableList<BodyParts> ObservList = FXCollections.observableArrayList();
        GestionBodyParts gbp =new GestionBodyParts();
        ArrayList<BodyParts> l=gbp.AfficherBodyParts();
        for(BodyParts x : l){
            BodyParts e=new BodyParts(x.getId(), x.getNom());
            ObservList.add(e);
        }
        bodypart.setItems(ObservList);
        anne.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                anne.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (anne.getText().length() > 4) {
                String s = anne.getText().substring(0, 4);
                anne.setText(s);
            }
        });
        
        
    }  
    
}
