package com.healthadvisor.javafx.article;


import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import com.sun.prism.impl.Disposer.Record;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn modifCol;
    @FXML
    private TableColumn affichCol;

    GestionArticle ga = new GestionArticle();
    ArrayList<Article> listA = (ArrayList<Article>) ga.afficherArticle();
    private ObservableList<Article> articleData = FXCollections.observableArrayList(listA);
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

    /*public void refresh() {
        articleData.clear();
        List<Article> la = new ArrayList<>();
        la = ga.afficherArticle();
        articleData.setAll(la);
        tabArt.setItems(articleData);
    }*/
    public void setTabArt(TableView<Article> tabArt) {
        this.tabArt = tabArt;
    }

    public TableColumn<Article, Integer> getRefCol() {
        return refCol;
    }

    public void setRefCol(TableColumn<Article, Integer> refCol) {
        this.refCol = refCol;
    }

    public TableColumn<Article, String> getTitreCol() {
        return titreCol;
    }

    public void setTitreCol(TableColumn<Article, String> titreCol) {
        this.titreCol = titreCol;
    }

    public TableColumn<Article, String> getDescCol() {
        return descCol;
    }

    public void setDescCol(TableColumn<Article, String> descCol) {
        this.descCol = descCol;
    }

    public TableColumn<Article, String> getContCol() {
        return contCol;
    }

    public void setContCol(TableColumn<Article, String> contCol) {
        this.contCol = contCol;
    }

    public TableColumn<Article, String> getMedCol() {
        return medCol;
    }

    public void setMedCol(TableColumn<Article, String> medCol) {
        this.medCol = medCol;
    }

    public ObservableList<Article> getArticleData() {
        return articleData;
    }

    public TableColumn<Article, String> getImgCol() {
        return imgCol;
    }

    public void setImgCol(TableColumn<Article, String> imgCol) {
        this.imgCol = imgCol;
    }

    public void setArticleData(ObservableList<Article> articleData) {
        this.articleData = articleData;
    }

    public TableView<Article> getTabArt() {
        return tabArt;
    }

    @FXML
    private void retour(ActionEvent event) {
    }

}
