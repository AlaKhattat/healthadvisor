package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
import com.sun.prism.impl.Disposer.Record;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

public class DelButton extends TableCell<Record, Boolean> {

    final Button supp = new Button("Supprimer");

    public DelButton(ObservableList<Evenement> evtList) {

        supp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                GestionEvenement ge = new GestionEvenement();
                try {
                    Evenement currentEvt = (Evenement) DelButton.this.getTableView().getItems().get(DelButton.this.getIndex());
                    Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
                    a1.setTitle("Suppresion Evenement");
                    a1.setHeaderText(null);
                    a1.setContentText("Etes vous vraiment sur de vouloir supprimer " + currentEvt.getNom() + " ?\n");
                    Optional<ButtonType> button = a1.showAndWait();
                    if (button.get() == ButtonType.OK) {
                        evtList.remove(currentEvt);
                        ge.supprimerEvenement(currentEvt.getId());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(supp);
        }
    }

}
