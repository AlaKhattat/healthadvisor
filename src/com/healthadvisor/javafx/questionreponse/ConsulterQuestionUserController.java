/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.javamail.SendEmail;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionQuestion;
import com.healthadvisor.service.impl.GestionReponse;
import com.healthadvisor.service.impl.GestionUtilisateur;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class ConsulterQuestionUserController implements Initializable {

    @FXML
    private AnchorPane paneID;
    @FXML
    private Label Label;
    @FXML
    private Label questionLabel;
    @FXML
    private VBox Vcontainer;
    public static Reponse reponseStatic;
    @FXML
    private Button retourBtn;
    @FXML
    private TextArea textAreaID;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button shareID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.err.println(QuestionUserController.patient.getLogin());
        System.err.println(QuestionUserController.questionStatic.getId_patient());
        
        shareID.setUserData(QuestionUserController.questionStatic);
        btnModifier.setUserData(0);
        questionLabel.setText(QuestionUserController.questionStatic.getQuestion());
        
        if(!QuestionUserController.m.getSpecialite().equals(QuestionUserController.questionStatic.getSpecialite())){
            shareID.setOpacity(0);
        }
        
        if(!QuestionUserController.patient.getLogin().equals(QuestionUserController.questionStatic.getId_patient())){
            btnModifier.setOpacity(0);
        }
        
        if(!QuestionUserController.patient.getLogin().equals(QuestionUserController.questionStatic.getId_patient())){
            btnSupprimer.setOpacity(0);
        }
        
        
        
        
        GestionReponse gr = new GestionReponse();
        ObservableList<Reponse> listr = FXCollections.observableArrayList();
            for (Reponse r : gr.ListReponse(QuestionUserController.questionStatic.getId())){
                listr.add(r);
               
                HBox h =new HBox();
                h.setMinWidth(600);
                h.setMinHeight(150);
                h.setAlignment(Pos.CENTER_LEFT);
                h.setSpacing(2);
                h.setStyle("fx-border-width:2;");
                
                Label user=new Label(r.getId_medecin());
                user.setMinWidth(94);
                user.setWrapText(true);
                
                Label reponse= new Label(r.getReponse());
                reponse.setMinWidth(319);
                reponse.setWrapText(true);
                
                Label date= new Label(r.getDate_publication().toString());
                date.setMinWidth(173);
                date.setWrapText(true);
                
                
                h.getChildren().add(user);
                h.getChildren().add(reponse);
                //v.getChildren().add(l2);
                h.getChildren().add(date);
                
                h.setPadding(new Insets(10, 0, 10, 10));
                
                Button modifier= new Button();
                FontAwesomeIconView f = new FontAwesomeIconView();
                f.setGlyphName("PENCIL");
                f.setGlyphSize(14);
                modifier.setGraphic(f);
                
                modifier.setUserData(r);
                modifier.setOnAction((event) -> {
                    reponseStatic=(Reponse)modifier.getUserData();
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierReponse.fxml"));
                        Parent root=loader.load();
                        Scene s = paneID.getScene();
                        s.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterQuestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                });
                
                Button supprimer= new Button();
                FontAwesomeIconView f1 = new FontAwesomeIconView();
                f1.setGlyphName("TRASH");
                f1.setGlyphSize(14);
                supprimer.setGraphic(f1);
                
                supprimer.setUserData(r);
                
                supprimer.setOnAction((event) -> {
                   
                    
                    try {
                        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
                        alerte.setTitle("Dialogue de confirmation");
                        alerte.setHeaderText("Attetion ! ");
                        alerte.setContentText("Voulez vous vraiment supprimer cette réponse ?");
                        
                        Optional<ButtonType> result = alerte.showAndWait();
                        if (result.get() == ButtonType.OK)
                        {
                            Reponse x = (Reponse)supprimer.getUserData();
                            gr.supprimerReponse(x.getId());
                        }else{
                            
                        }
                        
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterQuestionUser.fxml"));
                        Parent root=loader.load();
                        Scene s = paneID.getScene();
                        s.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(ConsulterQuestionUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                });
            
            h.getChildren().add(modifier);
            h.getChildren().add(supprimer);
            Vcontainer.getChildren().add(h);
            
                
            }
        
        
    }    

    @FXML
    private void retourBtnAction(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("QuestionUser.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void btnModifierAction(ActionEvent event) {
        if ((Integer)btnModifier.getUserData()==0)
        {
            textAreaID.setOpacity(1);
            textAreaID.setText(QuestionUserController.questionStatic.getQuestion());
            btnModifier.setUserData(1);
            
        }else{
            //btnModifier.setText("Valider");
            
           GestionQuestion gq = new GestionQuestion();
            gq.updateQuestion(QuestionUserController.questionStatic.getId(),textAreaID.getText());
            QuestionUserController.questionStatic.setQuestion(textAreaID.getText());
            textAreaID.setOpacity(0);
            questionLabel.setText(textAreaID.getText());
            btnModifier.setUserData(0);
        }
    }

    @FXML
    private void btnSupprimerAction(ActionEvent event) throws IOException {
        
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Dialogue de confirmation");
        alerte.setHeaderText("Attetion ! ");
        alerte.setContentText("Voulez vous vraiment supprimer cette question ?");
        
        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            GestionQuestion gq = new GestionQuestion();
            gq.supprimerQuestion(QuestionUserController.questionStatic.getId());           
        }else{
            
        }
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("QuestionUser.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
        
    }

    @FXML
    private void shareIDAction(ActionEvent event) throws IOException {
        
        
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("RepondreQuestion.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
        
        
    }
    
}
