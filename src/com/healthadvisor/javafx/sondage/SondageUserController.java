/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.entities.Sondage;
import com.healthadvisor.javafx.questionreponse.QuestionUserController;
import static com.healthadvisor.javafx.questionreponse.QuestionUserController.questionStatic;
import com.healthadvisor.service.impl.GestionQuestion;
import com.healthadvisor.service.impl.GestionSondage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class SondageUserController implements Initializable {

    @FXML
    private VBox Vcontainer;
    @FXML
    private Label label;
    public static Sondage sondageStatic;
    public static Patient patient = new Patient("tarek", "pass", "1","");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GestionSondage gs = new GestionSondage();
        ObservableList<Sondage> lists = FXCollections.observableArrayList();
            for (Sondage s : gs.ListSondage()){
                lists.add(s);
                
                
                
                HBox h =new HBox();
                h.setMinWidth(600);
                h.setMinHeight(100);
                h.setAlignment(Pos.CENTER_LEFT);
                h.setSpacing(2);
                
                Hyperlink sondage = new Hyperlink(s.getNom());
                sondage.setBorder(Border.EMPTY);
                sondage.setMinWidth(527);
                sondage.setWrapText(true);
                sondage.setUserData(s);
                
                sondage.setOnAction((event) -> {
                    try {
                        sondageStatic=(Sondage)sondage.getUserData();
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterSondage.fxml"));
                        Parent root=loader.load();
                        Scene sc = Vcontainer.getScene();
                        sc.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(SondageUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                });
                
                
                
                h.getChildren().add(sondage);
                h.setPadding(new Insets(10, 0, 10, 10));
            
                Vcontainer.getChildren().add(h);
            }
    }    
    
}
