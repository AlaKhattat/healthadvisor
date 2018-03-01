package com.healthadvisor.javafx.evenement;


import com.healthadvisor.entities.Evenement;
import com.healthadvisor.service.impl.GestionEvenement;
import com.sun.prism.impl.Disposer.Record;
import java.sql.Date;
import java.sql.Time;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
                String endroit=currentEvt.getEndroit();
                int max=currentEvt.getNbrMax();
                Date date=currentEvt.getDate();
                Time heure=currentEvt.getHeure();
                String img=currentEvt.getImage();
                String nom=currentEvt.getNom();
                String type=currentEvt.getType();
                Stage st = new Stage();
                main.startModif(st, id, max, date, heure, img, nom, endroit, type);
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
