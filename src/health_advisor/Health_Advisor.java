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
import java.security.Provider;
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
        Parent root = FXMLLoader.load(getClass().getResource("/com/healthadvisor/javafx/FXMLRechercheMedecin.fxml"));
        
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
        launch(args);
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
