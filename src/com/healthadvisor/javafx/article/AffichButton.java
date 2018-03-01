package com.healthadvisor.javafx.article;

import com.healthadvisor.entities.Article;
import com.healthadvisor.service.impl.GestionArticle;
import com.sun.prism.impl.Disposer.Record;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

public class AffichButton extends TableCell<Record, Boolean> {
    
    LireArticleFXMLController lire = new LireArticleFXMLController();
    final Button affich = new Button("Afficher");
    
    public AffichButton() {
        
        affich.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent t) {
                GestionArticle ga = new GestionArticle();
                ListeArticleFXMain main = new ListeArticleFXMain();
                Article currentArt = (Article) AffichButton.this.getTableView().getItems().get(AffichButton.this.getIndex());
                DetailsArticlesFXMLController details = new DetailsArticlesFXMLController();
                int ref = currentArt.getReference();
                Stage st = new Stage();
                String titre = currentArt.getNom();
                String desc = currentArt.getDescription();
                String cont = currentArt.getContenu();
                String idmed = currentArt.getIdMed();
                String img = currentArt.getImage();
                String tag = currentArt.getTags();
                double note = currentArt.getNote();
                details.affichage = false;
                main.startAffich(st, ref, titre, desc, cont, idmed, img, note, tag);
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
