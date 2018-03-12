package com.healthadvisor.javafx.evenement;


import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
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

    ListeEvenementFXMLController l = new ListeEvenementFXMLController();
    final Button modif = new Button("Modifier");
    
    public ModifButton() {
        modif.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                GestionEvenement ge=new GestionEvenement();
                ListeEvenementFXMain main = new ListeEvenementFXMain();
                Evenement currentEvt = (Evenement) ModifButton.this.getTableView().getItems().get(ModifButton.this.getIndex());
                int id=currentEvt.getId();             
                Stage st = new Stage();
                main.startModif(st, id);
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
