package com.healthadvisor.javafx.boutique;


import com.healthadvisor.entities.Commande;
import com.healthadvisor.entities.Produit;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.service.impl.ServiceCommande;
import com.healthadvisor.service.impl.ServiceProduit;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class FXMLGererProduit_AdminController implements Initializable {

    @FXML
    private AnchorPane pan_gerer_produits;
    @FXML
    private TableView<Produit> table_produits;
    @FXML
    private TableColumn<Produit, String> Reference;
    @FXML
    private TableColumn<Produit, String> Nom;
    @FXML
    private TableColumn<Produit, Float> Prix;
    @FXML
    private TableColumn<Produit, String> URL;
    @FXML
    private TableColumn<Produit, String> Description;
    @FXML
    private TableColumn<Produit, String> Type;
    @FXML
    private TableColumn<Produit, Date> Date_Mise;
    @FXML
    private TableColumn<Produit, Float> Promotion;
    @FXML
    private TableColumn<Produit, String> ID_User;
    @FXML
    private TableColumn<Produit, Integer> Quantite;
    @FXML
    private TableColumn<Produit, Integer> Signaler;
    @FXML
    private MenuItem btnMenu_Supprimer;
    @FXML
    private TextField txtReference;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrix;
    @FXML
    private TextField txtUrl;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtPromotion;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TextField txtSignaler;
    @FXML
    private TableView<Commande> table_commande;
    @FXML
    private TableColumn<Commande,String> C_Ref;
    @FXML
    private TableColumn<Commande,Date> C_Date;
    @FXML
    private TableColumn<Commande,String> C_Mode;
    @FXML
    private TableColumn<Commande,String> C_User;
    
    private  ObservableList<Produit> listP;
    private List<Produit> lst_produits;
    private List<Produit> lst_prod;
    
    private ObservableList<Commande> listCmd;
    private List<Commande> lst_commandes;
    
    ServiceProduit servP=new ServiceProduit();
    ServiceCommande ServC=new ServiceCommande();
    @FXML
    private Button btn_stat;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listP = FXCollections.observableArrayList();
         lst_produits=servP.ConsulterListe_Produits();
        AfficherProduits_Admin(listP, lst_produits);
        table_produits.setItems(listP);
        
        listCmd=FXCollections.observableArrayList();
        lst_commandes=ServC.ConsulterListe_Commandes();
        AfficherCommandes_Admin(listCmd, lst_commandes);
        table_commande.setItems(listCmd);
        
        RechercheDate(listP, table_produits, txtDate);
        RechercheDescription(listP, table_produits, txtDescription);
        RechercheNom(listP, table_produits, txtNom);
        RecherchePrix(listP, table_produits, txtPrix);
        RecherchePromotion(listP, table_produits, txtPromotion);
        RechercheQte(listP, table_produits, txtQuantite);
        RechercheReference(listP, table_produits, txtReference);
        RechercheSignaler(listP, table_produits, txtSignaler);
        RechercheType(listP, table_produits, txtType);
        RechercheURL(listP, table_produits, txtUrl);
        RechercheUser(listP, table_produits, txtUser);
    }    



    @FXML
    private void SupprimerProduit_Admin(ActionEvent event) {
        btnMenu_Supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                servP.SupprimerProduit(table_produits.getSelectionModel().getSelectedItem().getReference());
                try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererProduit_Admin.fxml")); 
                        Parent root=loader.load();
                        Scene s = pan_gerer_produits.getScene(); 
                        s.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }); 
         
         
    }
    
    //fonction affichage des produits
    public void AfficherProduits_Admin(ObservableList<Produit> OL,List<Produit> lst_produits)
    {     
            for (Produit p : lst_produits ){
                OL.add(p);
                Reference.setCellValueFactory(new PropertyValueFactory<Produit,String>("Reference"));
                Nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("Nom"));
                Prix.setCellValueFactory(new PropertyValueFactory<Produit,Float>("prix_vente"));
                URL.setCellValueFactory(new PropertyValueFactory<Produit,String>("url_image"));
                Description.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
                Type.setCellValueFactory(new PropertyValueFactory<Produit,String>("type"));
                Date_Mise.setCellValueFactory(new PropertyValueFactory<Produit,Date>("date_mise"));
                Promotion.setCellValueFactory(new PropertyValueFactory<Produit,Float>("promotion"));
                ID_User.setCellValueFactory(new PropertyValueFactory<Produit,String>("id_user"));
                Quantite.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("quantite"));
                Signaler.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("signaler"));
            }
 
    }
   
    //Recherche avancée sur tous les produits
    public void RechercheReference(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getReference().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }/*else if(r.getId_medecin().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } */
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
    
    //Recherche Nom
    public void RechercheNom(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getNom().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
    
    //recherche avancée prix
    public void RecherchePrix(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Float.toString(r.getPrix_vente()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
    
    //recherche URL
     public void RechercheURL(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getUrl_image().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
     
     //recherche Description
     public void RechercheDescription(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
     
     //recherche type
     public void RechercheType(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getType().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
     
     //recherche Date publication
     public void RechercheDate(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getDate_mise().toString().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
    
     //recherche promotion
     public void RecherchePromotion(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    Float lowerCaseFilter = Float.parseFloat(newValue.toLowerCase());
                    if (r.getPromotion() == lowerCaseFilter){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
     
     //recherche USer
     public void RechercheUser(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getId_user().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
    
     //recherche URL
     public void RechercheQte(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    int lowerCaseFilter = Integer.parseInt(newValue.toLowerCase());
                    if (r.getQuantite()==lowerCaseFilter){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
     
     //recherche Signaler
     public void RechercheSignaler(ObservableList<Produit> OL_Produit,TableView<Produit> tab_produit,TextField txtRecherche){
        FilteredList<Produit> filteredData = new FilteredList<>(OL_Produit, e -> true);
        txtRecherche.setOnKeyReleased(e->{
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Produit>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    int lowerCaseFilter = Integer.parseInt(newValue.toLowerCase());
                    if (r.getSignaler()==lowerCaseFilter){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Produit> sortedProduit = new SortedList<>(filteredData);
            sortedProduit.comparatorProperty().bind(tab_produit.comparatorProperty());
            tab_produit.setItems(sortedProduit);
        });
    }
     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        //Scripts de commande
   
     //fonction affichage des commandes des utilisateurs
    public void AfficherCommandes_Admin(ObservableList<Commande> OL,List<Commande> lst_cmd)
    {     
            for (Commande cmd : lst_cmd){
                OL.add(cmd);
                C_Ref.setCellValueFactory(new PropertyValueFactory<Commande,String>("reference_commande"));
                C_Date.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date_commande"));
                C_Mode.setCellValueFactory(new PropertyValueFactory<Commande,String>("mode_payement"));
                C_User.setCellValueFactory(new PropertyValueFactory<Commande,String>("ID_client"));
            }
 
    }

    @FXML
    private void InterfaceStatistiques(ActionEvent event) {
        try {
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("FXMLStatistiques_Admin.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    

}

