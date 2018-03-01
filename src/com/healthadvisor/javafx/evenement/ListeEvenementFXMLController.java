package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
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

public class ListeEvenementFXMLController implements Initializable {

    @FXML
    private TableView<Evenement> tabEvt;
    @FXML
    private TableColumn<Evenement, Integer> idCol;
    @FXML
    private TableColumn<Evenement, String> nomCol;
    @FXML
    private TableColumn<Evenement, Date> dateCol;
    @FXML
    private TableColumn<Evenement, Time> heureCol;
    @FXML
    private TableColumn<Evenement, String> endroitCol;
    @FXML
    private TableColumn<Evenement, String> typeCol;
    @FXML
    private TableColumn<Evenement, Integer> maxpCol;
    @FXML
    private TableColumn<Evenement, String> imgCol;
    @FXML
    private TableColumn<Evenement, String> validCol;
    @FXML
    private TableColumn supCol;
    @FXML
    private TableColumn modifCol;
    @FXML
    private TableColumn affichCol;
    @FXML
    private Button back;
    @FXML
    private AnchorPane anchor;

    GestionEvenement ge = new GestionEvenement();
    ArrayList<Evenement> listE = (ArrayList<Evenement>) ge.afficherEvenement();
    private ObservableList<Evenement> evtData = FXCollections.observableArrayList(listE);
    @FXML
    private JFXTextField rechL;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        RechercheNomEvenement(evtData, tabEvt, rechL);

        tabEvt.setItems(evtData);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, Integer>("Id"));
        nomCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("Nom"));
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, Date>("Date"));
        heureCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, Time>("Heure"));
        endroitCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("Endroit"));
        typeCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("Type"));
        imgCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("Image"));
        maxpCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, Integer>("nbrMax"));
        validCol.setCellValueFactory(
                new PropertyValueFactory<Evenement, String>("Valid"));
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
                return new DelButton(evtData);
            }
        });
        modifCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        modifCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {
            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> param) {
                return new ModifButton();
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

    public void RechercheNomEvenement(ObservableList<Evenement> evtData, TableView<Evenement> tabEvt, JFXTextField txtRecherche) {
        FilteredList<Evenement> filteredData = new FilteredList<>(evtData, e -> true);
        txtRecherche.setOnKeyReleased(e -> {
            txtRecherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Evenement>) a -> {
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
            SortedList<Evenement> sortedEvt = new SortedList<>(filteredData);
            sortedEvt.comparatorProperty().bind(tabEvt.comparatorProperty());
            tabEvt.setItems(sortedEvt);
        });
    }

}
