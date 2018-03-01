/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import static com.healthadvisor.javafx.questionreponse.QuestionController.question;
import com.healthadvisor.service.impl.GestionQuestion;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class QuestionUserController implements Initializable {

    @FXML
    private ScrollPane paneID;
    @FXML
    private VBox Vcontainer;
    @FXML
    private Button btnPlus;
    public static Question questionStatic;
    public static Patient patient = new Patient("tarek", "pass", "1","");
    public static Medecin m = new Medecin("fifi", "Gériatrie", "azerty", "azerty",5,0.0,0.0,"VALIDE", "fifi", "pass", "18","");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GestionQuestion gq = new GestionQuestion();
        ObservableList<Question> listq = FXCollections.observableArrayList();
            for (Question q : gq.ListQuestion() ){
                listq.add(q);
                
            HBox h =new HBox();
            h.setMinWidth(650);
            h.setMinHeight(150);
            h.setAlignment(Pos.CENTER_LEFT);
            h.setSpacing(2);
            h.setId("flatbee-card");
            Label user=new Label(q.getId_patient());
            user.setMinWidth(103);
            
            user.setWrapText(true);
            Hyperlink question = new Hyperlink(q.getQuestion());
            question.setBorder(Border.EMPTY);
            question.setMinWidth(320);
            question.setWrapText(true);
            question.setUserData(q);
            question.setOnAction((event) -> {
                    try {
                        questionStatic=(Question)question.getUserData();
                        
                        ScrollPane a = FXMLLoader.load(getClass().getResource("ConsulterQuestionUser.fxml"));
                        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
                        
                        
                    } catch (IOException ex) {
                        Logger.getLogger(QuestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
            /*Label l2 = new Label("Détails");
            l2.setStyle("-fx-font-weight:bold;");
            l2.setWrapText(true);
            l2*/
            Label details = new Label("Date publication : "+q.getDate_publication()+"\nSpécialité : "+q.getSpecialite());
            details.setWrapText(true);
            details.setMinWidth(163);
            
            
           /* Label l1 = new Label("Question\n");
            l1.setStyle("-fx-font-weight:bold;");
            l1.setWrapText(true);
            */

            
            h.getChildren().add(user);
            h.getChildren().add(question);
            //v.getChildren().add(l2);
            h.getChildren().add(details);
            h.setPadding(new Insets(10, 0, 10, 10));
            
            Vcontainer.getChildren().add(h);
            
            
            }   
            
            
    
    }

    @FXML
    private void btnPlusAction(ActionEvent event) throws IOException {
        
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("AjouterQuestion.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
    }
}
    
