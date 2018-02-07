/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.impl.service.GestionQuestion;
import com.healthadvisor.impl.service.GestionReponse;
import java.security.Provider;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author khattout
 */
public class Health_Advisor extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        java.util.Date myDate = new java.util.Date("13/02/2018");
        Patient p = new Patient("tarek", "pass", "1", "tarek", "kilani", "tarek@kilani", myDate, "homme", "tunisie", "tunis");
        Question q = new Question(3, "ma question", p);
        
        
        
        GestionReponse gr = new GestionReponse();
        Medecin m = new Medecin("tarek", "spec", "addr", "dip", 5, "1", "tarek", "kilani", "tarek@kilani", myDate, "homme", "tunisie", "tunis");
        Reponse r= new Reponse(5, "ma reponse", m, q);
        gr.ajouterReponse(r);
        //gr.supprimerReponse(r);
        
        gr.updateReponse(5, "mynew answer");
        gr.afficherReponse();
    }
    
}
