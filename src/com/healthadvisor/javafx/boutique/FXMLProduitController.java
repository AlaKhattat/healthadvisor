package com.healthadvisor.javafx.boutique;


import com.healthadvisor.entities.Produit;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.ServiceProduit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import health_advisor.FXMLHomeViewController;
import java.awt.Desktop;
import java.awt.Menu;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Date.parse;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyValue;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class FXMLProduitController implements Initializable {

     @FXML
    private AnchorPane pan_ajoutP;

    @FXML
    private JFXComboBox<String> type_p;

    @FXML
    private Label lbl_url;

    @FXML
    private Button btnInterface_filtre;

    @FXML
    private Button btnInterface_panier;

    @FXML
    private Button btnInterface_Publie;

    @FXML
    private BorderPane pan_notif;

    @FXML
    private JFXTextField ref;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXTextField txt_promotion;

    @FXML
    private JFXTextField txt_quantite;

    @FXML
    private JFXButton btn;

    @FXML
    private JFXTextArea desc;

    @FXML
    private JFXButton btn_img;
    
    
    private GridPane alertMessageContainer;
    private Label alertMessageLabel;
    private Label alertHelpMessageLabel;
    private Label alertIcon;
    private Button closePopupButton;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Timelines">
    private Timeline timerStoppedTimeline;
    private Timeline showAlertTimeline;
    private Timeline hideAlertTimeline;
    private Timeline showPopupTimeline;
    private Timeline hidePopupTimeline;
    Boolean popupState = false;
    
    
    
    private boolean ref_C=false,nom_C=false,prix_C=false,type_Ctrl=false,prom_C=false,qnte_C=false;
    private Desktop desktop = Desktop.getDesktop();
    ServiceProduit servP=new ServiceProduit();
    private Path url_destination,url_source;
    //public static String login_user="login";
 
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       type_p.getItems().addAll("Sante","Bien etre");
       txt_promotion.setText("0");
       txt_quantite.setText("1");
      
       
       
       
    }    

     @FXML
      private void AjouterProduit(ActionEvent event) {
          if(nom_C==false ||ref_C==false || prix_C==false || prom_C==false || qnte_C==false || type_Ctrl==false){
             showPopup("Erreur de saisie","veuillez corriger",0);
         }
          else{
         try {
             String reference=ref.getText();
             String nom_p=nom.getText();
             float prix_P=Float.parseFloat(prix.getText());
             
             String type_P=type_p.getValue();
             String url_P=lbl_url.getText();
             System.err.println("rien   "+url_P);
              if(lbl_url.getText().equalsIgnoreCase("")){
              if(type_P.equalsIgnoreCase("sante")){
                   
                  url_P="C:\\wamp64\\www\\HealthAdvisorImages\\DefaultSante.png";
                   url_source= Paths.get(url_P);
                   url_destination= Paths.get(url_P);
              
              }   
               if(type_P.equalsIgnoreCase("bien etre")){    
                  url_P="C:\\wamp64\\www\\HealthAdvisorImages\\DefaultBienEtre.png";
                  url_source=Paths.get(url_P);
                  url_destination=Paths.get(url_P);
              }
             } 
              
               
             String ID_patient=FXMLLoginController.pseudo;//*************héthi lézém nsobha mél variable li bch n7otha awél ma tét7al lapp
             String description=desc.getText();
             float promotion=Float.parseFloat(txt_promotion.getText());
             int quantite= Integer.parseInt(txt_quantite.getText());
             //int signalisation=0;
                     
             DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
             Date date = new Date();
             
             Files.copy(url_source,url_destination, StandardCopyOption.REPLACE_EXISTING);
             
             Produit p=new Produit(reference,nom_p,prix_P,url_P,type_P,ID_patient,description,date_format.parse(date_format.format(date)),promotion,quantite,0);
             servP.AjouterProduit(p);
         } catch (ParseException ex) {
             
         } catch (IOException ex) {
             Logger.getLogger(FXMLProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
          }
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
            new File("C:\\Users\\aaa\\Desktop\\")
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
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("FXMLAfficherProduit.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InterfacePanier(ActionEvent event) {
        try {
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("FXMLAfficherPanier.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InterfacePublie(ActionEvent event) {
         try {
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("FXMLAfficherProduitPublie.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refControl(KeyEvent event) {
         if(ref.getText().isEmpty()|| (servP.VerifierExistance_Produit(ref.getText())==false)){
            ref.setFocusColor(Color.RED);
            ref_C=false;
        }else {
            ref.setFocusColor(Color.BLUE);
            ref_C=true;
        }
    }

    @FXML
    private void nomControl(KeyEvent event) {
        if(nom.getText().isEmpty()){
            nom.setFocusColor(Color.RED);
            nom_C=false;
        }
        else{
            nom.setFocusColor(Color.BLUE);
            nom_C=true;
        }
    }

    @FXML
    private void prixControl(KeyEvent event) {
        ControleSaisie_Float(prix,prix_C,0);
    
    }

    @FXML
    private void promControl(KeyEvent event) {
         ControleSaisie_Float(txt_promotion,prom_C,1);
    }

    @FXML
    private void qteControl(KeyEvent event) {
        ControleSaisie_Integer(txt_quantite,qnte_C);
    }

   
    
    public void ControleSaisie_Float(JFXTextField txt,boolean verif,int x){
        String num=txt.getText();
        
       try{
           if(txt.getText().isEmpty()){
                 txt.setFocusColor(Color.RED);
                 verif=false;
           }
           else{
       txt.setFocusColor(Color.BLUE);
       verif=true;
       float numtel=Float.parseFloat(num);
       if(x==1){
           if(numtel>100){
               verif=false;
           }
       }
           }
       }catch(NumberFormatException ex)  {
       txt.setFocusColor(Color.RED);
       verif=false;
    }
    }
     public void ControleSaisie_Integer(JFXTextField txt,boolean  verif){
        String num=txt.getText();
       try{
           if(txt.getText().isEmpty()){
                 txt.setFocusColor(Color.RED);
                 verif=false;
           }
           else{
       txt.setFocusColor(Color.BLUE);
       int numtel=Integer.parseInt(num);
       verif=true;
           }
       }catch(NumberFormatException ex)  {
       txt.setFocusColor(Color.RED);
       verif=false;
    }
    }
     
     @FXML
    private void typeControl(KeyEvent event) {
        if(type_p.getValue().isEmpty()){
            type_Ctrl=false;
        }
        else{
            type_Ctrl=true;
        }
    }
    
     
     
     
     private  void showPopup(String message, String helpText, int alertType) {
        //Alert message layout
        alertMessageContainer = new GridPane();
        alertMessageContainer.setHgap(10);
        alertMessageContainer.setPrefHeight(50);
        alertMessageContainer.setPadding(new Insets(0, 0, 0, 50));
        alertMessageContainer.getStyleClass().add(alertType == 1 ? "alert-success" : "alert-danger");

        //Creating row constraints
        ColumnConstraints firstColumn = new ColumnConstraints();
        firstColumn.setPercentWidth(3);
        ColumnConstraints secondColumn = new ColumnConstraints();
        secondColumn.setPercentWidth(20);
        ColumnConstraints thirdColumn = new ColumnConstraints();
        thirdColumn.setPercentWidth(70);
        ColumnConstraints forthColumn = new ColumnConstraints();
        alertMessageContainer.getColumnConstraints().addAll(firstColumn, secondColumn, thirdColumn, forthColumn);

        pan_notif.setBottom(alertMessageContainer);
        pan_notif.setAlignment(alertMessageContainer, Pos.CENTER_LEFT);


        alertIcon = new Label();
        alertIcon.getStyleClass().add("alert-icon");
        alertIcon.getStyleClass().add(alertType == 1 ? "alert-icon-success" : "alert-icon-danger");
        alertMessageContainer.setConstraints(alertIcon, 0, 0);



        alertMessageLabel = new Label(message);
        alertMessageLabel.getStyleClass().add("alert-message");
        alertMessageLabel.getStyleClass().add(alertType == 1 ? "alert-message-success" : "alert-message-danger");
        alertMessageContainer.setConstraints(alertMessageLabel, 1, 0);
        alertMessageContainer.setMargin(alertMessageLabel, new Insets(10, 0, 0, 0));



        alertHelpMessageLabel = new Label(helpText);
        alertHelpMessageLabel.getStyleClass().add("alert-help");
        alertMessageContainer.setConstraints(alertHelpMessageLabel, 2, 0);
        alertMessageContainer.setMargin(alertHelpMessageLabel, new Insets(10, 0, 0, 0));


        closePopupButton = new Button("");
        closePopupButton.setMinHeight(60);
        closePopupButton.setMinWidth(100);
        closePopupButton.getStyleClass().add("button-icon--white");
        closePopupButton.getStyleClass().addAll("close-icon");
        closePopupButton.getStyleClass().add("");
        alertMessageContainer.setConstraints(closePopupButton, 3, 0);

        closePopupButton.setOnAction(e -> {
            hidePopupTimeline.play();
        });


        //Adding the alert in gameScene
        alertMessageContainer.getChildren().addAll(alertIcon, alertMessageLabel, alertHelpMessageLabel, closePopupButton);
        alertMessageContainer.setAlignment(Pos.CENTER_LEFT);

        //Fading animation
        showPopupTimeline = new Timeline();

        KeyValue fromPosition = new KeyValue(alertMessageContainer.translateYProperty(), 50);
        KeyValue toPosition = new KeyValue(alertMessageContainer.translateYProperty(), 0);

        KeyFrame startMove = new KeyFrame(Duration.ZERO, fromPosition);
        KeyFrame finishMove = new KeyFrame(Duration.millis(200), toPosition);

        showPopupTimeline.getKeyFrames().addAll(startMove, finishMove);
        popupState = true;
        showPopupTimeline.play();

        hidePopupTimeline = new Timeline();

        KeyValue fromPositionReverse = new KeyValue(alertMessageContainer.translateYProperty(), 0);
        KeyValue toPositionReverse = new KeyValue(alertMessageContainer.translateYProperty(), 50);

        KeyFrame startMoveReverse = new KeyFrame(Duration.ZERO, fromPositionReverse);
        KeyFrame finishMoveReverse = new KeyFrame(Duration.millis(200), toPositionReverse);
        KeyFrame clear = new KeyFrame(Duration.millis(201), e -> pan_notif.setBottom(null));

        hidePopupTimeline.getKeyFrames().addAll(startMoveReverse, finishMoveReverse, clear);

        //Auto hide the alert
        hideAlertTimeline = new Timeline(new KeyFrame(
                Duration.millis(5000),
                ae -> {
                    hidePopupTimeline.play();
                    popupState = false;
                }
        ));
        hideAlertTimeline.play();
    }

     
     ////////////////////////////////////////////////////////////////////////////
    
    
}
