package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.service.impl.GestionEvenement;
import com.sun.prism.impl.Disposer.Record;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class AffichButton extends TableCell<Record, Boolean> {

    ListeEvenementFXMLController l = new ListeEvenementFXMLController();
    final Button affich = new Button("Afficher");

    public AffichButton() {

        affich.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                GestionEvenement ge = new GestionEvenement();
                ListeEvenementFXMain main = new ListeEvenementFXMain();
                Evenement currentEvt = (Evenement) AffichButton.this.getTableView().getItems().get(AffichButton.this.getIndex());
                int id = currentEvt.getId();
                Stage st = new Stage();
                String nom = currentEvt.getNom();
                Date date = currentEvt.getDate();
                Time heure = currentEvt.getHeure();
                String endroit = currentEvt.getEndroit();
                String type = currentEvt.getType();
                int nbrMax = currentEvt.getNbrMax();
                String url=currentEvt.getImage();
                String createur=currentEvt.getLogCreateur();
                LireEvenementFXMLController le=new LireEvenementFXMLController();
                Date d2 = new Date(1970, 9, 9);
                Patient p=new Patient(); //SESSION PATIENT
                main.startAffich(st, nom, date, heure, endroit, type, nbrMax, url, currentEvt, createur, p);
            }
        });
    }
    
    
    
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(affich);
        }
    }
    
}
