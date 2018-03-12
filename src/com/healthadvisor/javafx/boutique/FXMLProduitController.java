
package com.healthadvisor.javafx.boutique;


import com.healthadvisor.entities.Produit;
import com.healthadvisor.service.impl.ServiceProduit;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Date.parse;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FXMLProduitController implements Initializable {

 
     @FXML
    private TextField ref;

    @FXML
    private TextField prix;

    @FXML
    private TextField nom;
     
    @FXML
    private TextField id_patient;
    @FXML
    private Button btn;
    @FXML
    private TextArea desc;
    @FXML
    private ChoiceBox<String> type_p;
    @FXML
    private Button btn_modif;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_img;
    
    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private AnchorPane pan_ajoutP;
    @FXML
    private Label lbl_url;
    
    private Path url_destination,url_source;
    @FXML
    private TextField txt_promotion;
    @FXML
    private TextField txt_quantite;
    
    @FXML
    private Button btnInterface_filtre;
    @FXML
    private Button btnInterface_panier;
    @FXML
    private Button btnInterface_Publie;
    
    public static String login_user="login";
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       type_p.getItems().addAll("Sante","Bien etre");
       txt_promotion.setText("0");
       txt_quantite.setText("1");
       
       
    }    

      @FXML
      private void AjouterProduit(ActionEvent event) {
         try {
             String reference=ref.getText();
             String nom_p=nom.getText();
             float prix_P=Float.parseFloat(prix.getText());
             String url_P=lbl_url.getText();
             String type_P=type_p.getValue();
             String ID_patient=login_user;//*************héthi lézém nsobha mél variable li bch n7otha awél ma tét7al lapp
             String description=desc.getText();
             float promotion=Float.parseFloat(txt_promotion.getText());
             int quantite= Integer.parseInt(txt_quantite.getText());
             //int signalisation=0;
                     
             DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
             Date date = new Date();
             
             Files.copy(url_source,url_destination, StandardCopyOption.REPLACE_EXISTING);
             
             Produit p=new Produit(reference,nom_p,prix_P,url_P,type_P,ID_patient,description,date_format.parse(date_format.format(date)),promotion,quantite,0);
             ServiceProduit serP=new ServiceProduit();
             serP.AjouterProduit(p);
         } catch (ParseException ex) {
             
         } catch (IOException ex) {
             Logger.getLogger(FXMLProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void ModifierProduit(ActionEvent event) {
             String reference=ref.getText();
             String nom_p=nom.getText();
             float prix_P=Float.parseFloat(prix.getText());
             String url_P=lbl_url.getText();
             String type_P=type_p.getValue();
             String description=desc.getText();
             float promotion=Float.parseFloat(txt_promotion.getText());
             
             ServiceProduit sp=new ServiceProduit();
             Produit p=sp.ConsulterProduit(reference);
             p.setDescription(description);
             p.setNom(nom_p);
             p.setPrix_vente(prix_P);
             p.setType(type_P);
             p.setUrl_image(url_P);
             p.setPromotion(promotion);
             sp.UpdateProduit(p);
    }

    @FXML
    private void SupprimerProduit(ActionEvent event) {
        String reference=ref.getText();
        ServiceProduit sp=new ServiceProduit();
        sp.SupprimerProduit(reference);
    }

    @FXML
    private void ParcourirImage(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(pan_ajoutP.getScene().getWindow());
                    if (file != null) {
                        openFile(file);
                    }
    }
   
    
   // Pour ouvrir le filechooser et choisir une image
   private void openFile(File file) {
        try {
            desktop.open(file);
            File dest = new File("C:\\wamp64\\www\\HealthAdvisorImages\\"+file.getName());
            lbl_url.setText(dest.toPath().toString());
            url_source=file.toPath();
            url_destination=dest.toPath();
            System.out.println(file.toPath().toString());
            System.out.println("Images Enregistrés avec succés");
        } catch (IOException ex) {
            System.err.println("Erreur d'enregistrement d'image");
        }
    }
   
   //Pour configurer le cadre du file chooser et le dossier initial qui s'ouvre
   private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
            new File("C:\\Users\\HABOUB\\Desktop\\")
        ); 
        fileChooser.getExtensionFilters().addAll(
               // new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    }


    @FXML
    private void InterfaceFiltre(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAfficherProduit.fxml")); 
            Parent root=loader.load();
            Scene s = pan_ajoutP.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InterfacePanier(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAfficherPanier.fxml")); 
            Parent root=loader.load();
            Scene s = pan_ajoutP.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InterfacePublie(ActionEvent event) {
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAfficherProduitPublie.fxml")); 
            Parent root=loader.load();
            Scene s = pan_ajoutP.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
