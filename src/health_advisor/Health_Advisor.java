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
import com.healthadvisor.service.impl.GestionReponse;
import com.healthadvisor.service.impl.GestionReponsesPossibles;
import com.healthadvisor.service.impl.GestionSondage;
import com.healthadvisor.service.impl.GestionUserReponse;
import java.security.Provider;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author khattout
 */
public class Health_Advisor extends Application {
    
    private double x = 0;
    private double y = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/healthadvisor/javafx/recherchemedecin/FXMLRechercheMedecin.fxml"));
        
         root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            }
        });
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show(); }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("HELLO WANNA CRY IF YOU SEE THIS THAT MEAN EVERYTHING OR NOTHING JUST THROW UP HAHA");
        //launch(args);

      
        
              
    }       
        
}
