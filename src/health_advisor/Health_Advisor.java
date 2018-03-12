/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.enumeration.StatutRendezVousEnum;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionQuestion;
import com.healthadvisor.service.impl.GestionRendezVous;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.Sondage;
import com.healthadvisor.entities.UserReponse;
import com.healthadvisor.javafx.routes.Routes;
import com.healthadvisor.javamail.SendEmail;
import com.healthadvisor.service.impl.GestionReponse;
import com.healthadvisor.service.impl.GestionReponsesPossibles;
import com.healthadvisor.service.impl.GestionSondage;
import com.healthadvisor.service.impl.GestionUserReponse;
import com.healthadvisor.sms.sendSMS;
import com.jfoenix.controls.JFXDecorator;
import java.security.Provider;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author khattout
 */
public class Health_Advisor  extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(Routes.HOMEVIEW));     
        Scene scene = new Scene(root);   
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
    

