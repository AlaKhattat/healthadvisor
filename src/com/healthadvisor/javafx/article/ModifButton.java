package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import com.sun.prism.impl.Disposer.Record;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class ModifButton extends TableCell<Record, Boolean> {

    ListeArticleFXMLController l = new ListeArticleFXMLController();
    final Button modif = new Button("Modifier");
    
    
    public ModifButton() {

        modif.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                GestionArticle ga=new GestionArticle();
                ListeArticleFXMain main = new ListeArticleFXMain();
                Article currentArt = (Article) ModifButton.this.getTableView().getItems().get(ModifButton.this.getIndex());
                int ref=currentArt.getReference();             
                Stage st = new Stage();
                main.startModif(st, ref);
            }
        });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(modif);
        }
    }
}
