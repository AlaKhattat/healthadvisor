package com.healthadvisor.javafx.boutique;


import com.healthadvisor.entities.Produit;
import com.healthadvisor.service.impl.ServiceProduit;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


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
    private TableColumn<Produit, Button> supprimer;
    @FXML
    private Button btn_stats;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceProduit sp=new ServiceProduit();
        ObservableList<Produit> listP = FXCollections.observableArrayList();
            for (Produit p : sp.ConsulterListe_Produits() ){
                listP.add(p);
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
                Button btnsupprimer = new Button("Supprimer");
                supprimer.setCellValueFactory(new PropertyValueFactory<Produit,Button>("supprimer"));
            }
        table_produits.setItems(listP);
    }    

    @FXML
    private void Afficher_Stats(ActionEvent event) {
    
        
    
    }

    @FXML
    private void ModifierProduit_Admin(ActionEvent event) {
        
    }

    @FXML
    private void SupprimerProduit_Admin(ActionEvent event) {
    }
    
}

