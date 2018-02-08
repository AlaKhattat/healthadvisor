/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.impl.service.GestionMedecin;
import com.healthadvisor.impl.service.GestionUtilisateur;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Rendez_Vous;
import com.healthadvisor.impl.service.GestionPatient;
import com.healthadvisor.impl.service.GestionQuestion;
import com.healthadvisor.impl.service.GestionRendezVous;
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
        //launch(args);
        
    }
    
}
/*
 GestionUtilisateur gest_ut=new GestionUtilisateur();
        GestionMedecin get_med=new GestionMedecin();
        GestionPatient get_p=new GestionPatient();
        get_med.SupprimerMedecinCin("124");
 Utilisateur u=new Utilisateur("324", "skander", "lengliz", "ska@ska", date, "homme", "tunisie", "ariana");
        gest_ut.AjouterUtilisateur(u);
        Patient p =new Patient("324", "skander","324");
        get_p.AjouterPatient(p);
        Medecin m= new Medecin("324", "generaliste", "rue2", "alala", 12458,"324","skander","324");
        get_med.AjouterMedecin(m);
        System.out.println(gest_ut.ListUtilisateurs());
        for(int i=0;i<gest_ut.ListUtilisateurs().size();i++){
            System.out.println("utilisateur :"+gest_ut.ListUtilisateurs().get(i)+"\n");
        }
         for(int i=0;i<get_med.ListMedecin().size();i++){
            System.out.println("medecins :"+get_med.ListMedecin().get(i)+"\n");
        }
          for(int i=0;i<get_p.ListPatient().size();i++){
            System.out.println("patients :"+get_p.ListPatient().get(i)+"\n");
        }

 java.util.Date date=new java.util.Date("13/10/1995");
        GestionRendezVous gr=new GestionRendezVous();
        Rendez_Vous r=new Rendez_Vous(0,date, "456", "324");
        
        gr.AjouterRendezVous(r);
*/