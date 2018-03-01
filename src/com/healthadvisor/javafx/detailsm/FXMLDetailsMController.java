/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.detailsm;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.enumeration.StatutMedecinEnum;
import com.healthadvisor.javamail.SendEmail;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLDetailsMController implements Initializable {
    GestionPatient gp=new GestionPatient();
    GestionUtilisateur gu=new GestionUtilisateur();
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField date;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXTextField specialite;
    @FXML
    private JFXTextField sexe;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXButton quitter;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView imageDiplome;
    @FXML
    private JFXComboBox<String> statutCompte;
    String[] statutcmp={StatutMedecinEnum.NON_VALIDE.name(),StatutMedecinEnum.VALIDE.name()};
    GestionMedecin gm=new GestionMedecin();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> sl=FXCollections.observableArrayList(statutcmp);
        statutCompte.setItems(sl); 
    }    
         public void inflateUI(Medecin m) throws FileNotFoundException, IOException {
          Patient p=gp.AfficherPatientLogin(m.getLogin_med());
          Utilisateur u=gu.AfficherUtilisateurCin(p.getCin_user());
             System.out.println(m);
          this.nom.setText(u.getNom());
          this.prenom.setText(u.getPrenom());
          this.cin.setText(u.getCin());
          this.date.setText(u.getDate_naiss().toString());
          this.ville.setText(u.getVille());
          this.specialite.setText(m.getSpecialite());
          this.sexe.setText(u.getSexe());
          this.email.setText(u.getEmail());
          this.statutCompte.setValue(m.getStatut_compte());
             System.out.println("statut"+m.getStatut_compte());
        FileInputStream input;
             System.out.println(m.getDiplome());
        input = new FileInputStream(m.getDiplome());
        Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
        this.imageDiplome.setImage(img_produit);
      
         }

    @FXML
    private void quitter(MouseEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ModifStatutCompte(ActionEvent event) {
           Image imgsucces=new Image("/com/healthadvisor/ressources/checked.png");
       Notifications notifsucces=Notifications.create()
               .graphic(new ImageView(imgsucces))
                    .title("Modification Statut Compte")
                    .text("Statut Compte Modifié")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle()
                ;
       if(gm.ModifierStatutMedecin(this.cin.getText(),this.statutCompte.getValue())){
                if(this.statutCompte.getValue().equalsIgnoreCase(StatutMedecinEnum.VALIDE.name())){

                SendEmail se=new SendEmail();
                String sub="Rendez_Vous Confirmé";
                String msg="Bonjour,\n Votre Compte a été Valide ";
                int sendMail = se.sendMail(" healthadvisoresprit@gmail.com","projetpidev","alaeddine.khattat@esprit.tn",sub,msg);  
           notifsucces.show();

      if(sendMail == 0){
          System.out.println("OK Email");}
       }
       }
    }
}
