package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class ListeArticleFXMLController extends TableCell<Record, Boolean> implements Initializable {

    @FXML
    private TableView<Article> tabArt;
    @FXML
    private TableColumn<Article, Integer> refCol;
    @FXML
    private TableColumn<Article, String> titreCol;
    @FXML
    private TableColumn<Article, String> descCol;
    @FXML
    private TableColumn<Article, String> contCol;
    @FXML
    private TableColumn<Article, String> medCol;
    @FXML
    private TableColumn<Article, String> imgCol;
    @FXML
    private TableColumn supCol;
    @FXML
    private TableColumn affichCol;
    @FXML
    private Button back;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXTextField rechF;

    GestionArticle ga = new GestionArticle();
    ArrayList<Article> listA = (ArrayList<Article>) ga.afficherArticle();
    private ObservableList<Article> articleData = FXCollections.observableArrayList(listA);
    @FXML
    private TableColumn<Article, Double> noteCol;
    @FXML
    private TableColumn<Article, String> validCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        RechercheNomArticle(articleData, tabArt, rechF);

        tabArt.setItems(articleData);
        refCol.setCellValueFactory(
                new PropertyValueFactory<Article, Integer>("Reference"));
        titreCol.setCellValueFactory(
                new PropertyValueFactory<Article, String>("Nom"));
        descCol.setCellValueFactory(
                new PropertyValueFactory<Article, String>("Description"));
        contCol.setCellValueFactory(
                new PropertyValueFactory<Article, String>("Contenu"));
        medCol.setCellValueFactory(
                new PropertyValueFactory<Article, String>("IdMed"));
        imgCol.setCellValueFactory(
                new PropertyValueFactory<Article, String>("Image"));
        noteCol.setCellValueFactory(
                new PropertyValueFactory<Article, Double>("Note"));
        validCol.setCellValueFactory(
                new PropertyValueFactory<Article, String>("Valid"));
        supCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        supCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> param) {
                return new DelButton(articleData);
            }
        });
        affichCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        affichCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> param) {
                return new AffichButton();
            }
        });
    }

    @FXML
    private void redirectBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfacePrincipaleFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            InterfacePrincipaleFXMLController interf = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RechercheNomArticle(ObservableList<Article> articleData, TableView<Article> tabArt, JFXTextField txtRecherche) {
        FilteredList<Article> filteredData = new FilteredList<>(articleData, e -> true);
        txtRecherche.setOnKeyReleased(e -> {
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Article>) a -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (a.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Article> sortedArticle = new SortedList<>(filteredData);
            sortedArticle.comparatorProperty().bind(tabArt.comparatorProperty());
            tabArt.setItems(sortedArticle);
        });
    }
    
    public void Reload(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeArticleFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            ListeArticleFXMLController listFX = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
