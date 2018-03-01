
package com.healthadvisor.javafx.evenement;

import com.healthadvisor.entities.Evenement;
import com.healthadvisor.entities.Patient;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ListeEvenementFXMain extends Application {
    
    public static Stage stage;
    
    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ListeEvenementFXML.fxml"));
        try {
            Parent root=loader.load();
            Scene sc=new Scene(root);
            Stage st=new Stage();
            ListeEvenementFXMLController liste=new ListeEvenementFXMLController();
            st.setScene(sc);
            st.show();
        } catch (IOException i) {
        }
    }
    
    public void startModif(Stage primaryStage,int id, int max, Date date, Time heure, String img, String nom, String endroit, String type) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifEvenementFXML.fxml"));
        try {
            Parent root = loader.load();
            Scene sc = new Scene(root);
            stage = new Stage();
            stage.setScene(sc);
            ModifEvenementFXMLController cnt = loader.getController();
            cnt.setId(id);
            cnt.setDateF(date);
            cnt.setEndroitF(endroit);
            cnt.setImgView(img);
            cnt.setTypeCombo(type);
            cnt.setTimeF(heure);
            cnt.setMaxF(max);
            cnt.setNomF(nom);
            stage.show();
        } catch (IOException e) {
        }
    }
    
    public void startAffich(Stage primaryStage, String nom, Date date, Time heure, String endroit, String type, int nbrMax, String url, Evenement e, String createur, Patient p) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LireEvenementFXML.fxml"));
        try {
            Parent root = loader.load();
            LireEvenementFXMLController lire=loader.getController();
            lire.setNomLab(nom);
            lire.setDateLab(date);
            lire.setHeureLab(heure);
            lire.setEndroitLab(endroit);
            lire.setTypeLab(type);
            lire.setMaxLab(nbrMax);
            lire.dispoEvent(e,p);
            lire.ecrireMessage(e, p);
            lire.setEvt(e);
            lire.setImg(url);
            lire.setCreateurLab(createur);
            Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
        } catch (IOException i) {
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
