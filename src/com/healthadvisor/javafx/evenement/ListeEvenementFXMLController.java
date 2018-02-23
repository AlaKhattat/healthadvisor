package com.healthadvisor.javafx.evenement;


import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
import com.sun.prism.impl.Disposer.Record;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn supCol;
    @FXML
    private TableColumn modifCol;
    @FXML
    private TableColumn affichCol;

    GestionEvenement ge = new GestionEvenement();
    ArrayList<Evenement> listE = (ArrayList<Evenement>) ge.afficherEvenement();
    private ObservableList<Evenement> evtData = FXCollections.observableArrayList(listE);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

    public TableView<Evenement> getTabEvt() {
        return tabEvt;
    }

    public void setTabEvt(TableView<Evenement> tabEvt) {
        this.tabEvt = tabEvt;
    }

    public TableColumn<Evenement, Integer> getIdCol() {
        return idCol;
    }

    public void setIdCol(TableColumn<Evenement, Integer> idCol) {
        this.idCol = idCol;
    }

    public TableColumn<Evenement, String> getNomCol() {
        return nomCol;
    }

    public void setNomCol(TableColumn<Evenement, String> nomCol) {
        this.nomCol = nomCol;
    }

    public TableColumn<Evenement, Date> getDateCol() {
        return dateCol;
    }

    public void setDateCol(TableColumn<Evenement, Date> dateCol) {
        this.dateCol = dateCol;
    }

    public TableColumn<Evenement, Time> getHeureCol() {
        return heureCol;
    }

    public void setHeureCol(TableColumn<Evenement, Time> heureCol) {
        this.heureCol = heureCol;
    }

    public TableColumn<Evenement, String> getEndroitCol() {
        return endroitCol;
    }

    public void setEndroitCol(TableColumn<Evenement, String> endroitCol) {
        this.endroitCol = endroitCol;
    }

    public TableColumn<Evenement, String> getTypeCol() {
        return typeCol;
    }

    public void setTypeCol(TableColumn<Evenement, String> typeCol) {
        this.typeCol = typeCol;
    }

    public TableColumn<Evenement, Integer> getMaxpCol() {
        return maxpCol;
    }

    public void setMaxpCol(TableColumn<Evenement, Integer> maxpCol) {
        this.maxpCol = maxpCol;
    }

    public ArrayList<Evenement> getListE() {
        return listE;
    }

    public void setListE(ArrayList<Evenement> listE) {
        this.listE = listE;
    }

    public ObservableList<Evenement> getEvtData() {
        return evtData;
    }

    public void setEvtData(ObservableList<Evenement> evtData) {
        this.evtData = evtData;
    }

}
