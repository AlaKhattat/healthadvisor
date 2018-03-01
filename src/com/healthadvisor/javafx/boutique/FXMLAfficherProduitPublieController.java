package com.healthadvisor.javafx.boutique;


import com.healthadvisor.entities.Produit;
import static com.healthadvisor.javafx.boutique.FXMLAfficherProduitController.panier;
import com.healthadvisor.javamail.SendEmail;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.ServiceProduit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.util.Pair;
import javax.imageio.ImageIO;


public class FXMLAfficherProduitPublieController implements Initializable {

    @FXML
    private Button btnInterface_Ajout;
    @FXML
    private Button btnInterface_Filtre;
    @FXML
    private Button btnInterface_Panier;
    @FXML
    private AnchorPane pan_publie;

    
    private Desktop desktop = Desktop.getDesktop();
    private ScrollPane scroll =new ScrollPane();
    private GridPane   grid=new GridPane();
    private GridPane g;
    private ScrollPane s;
    private String url_image;

    ServiceProduit servP=new ServiceProduit();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        List<Produit> lst_P=servP.ListProduits_User(FXMLProduitController.login_user); //**** je dois la changer avec la variable de login
        ///*************************pour afficher notif d'un prod un jour avant l'expiration du produit
        NotificationEmail_Expiration(lst_P);
        //**********************Pour supprimer un produit déja expiré
        SupprimerProduits_Expirés(lst_P);
        

        
        Configurer_GridScroll(grid,scroll);
        for(int i=0;i<lst_P.size();i++)
            {
                CreerProduit(grid, lst_P.get(i), 0, i, 1, i, 2, i,i);
            }
        pan_publie.getChildren().addAll(scroll);
    }    

    //creere instanciation d'un produit
    public void CreerProduit(GridPane grid ,Produit p,int columnIMG,int rowIMG,int columnV1,int rowV1,int columnV2,int rowV2,int indice_prod_publie){
        ServiceProduit sp=new ServiceProduit();
        ImageView img1 = new ImageView();
        FileInputStream input,input1;
      try {
             input = new FileInputStream(p.getUrl_image());
             Image img_produit = SwingFXUtils.toFXImage(ImageIO.read(input), null);
              img1.setImage(img_produit);
              img1.setFitHeight(100);
              img1.setFitWidth(100);
              grid.add(img1,columnIMG,rowIMG);
              
      
              } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                    Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         VBox v1=new VBox();
   
         Label espace1=new Label();
         espace1.setText("");
         
        Label espace2=new Label();
        espace2.setText("");
        
        Label nom_produit=new Label();
        nom_produit.setText("Nom: "+p.getNom());
        
        Label espace3=new Label();
        espace3.setText("");
        
        Label prix_produit=new Label();
        prix_produit.setText("Prix: "+p.getPrix_vente()+"DT");
        
        Label espace4=new Label();
        espace4.setText("");
        
        Label date_mise=new Label();
        date_mise.setText("Date mise produit: "+p.getDate_mise());
        
        Label esp6=new Label();
        esp6.setText("");
        
        Label date_expiration=new Label();
        long nb_jours= (30-DifférenceDates(p.getDate_mise()));
        if(nb_jours<0){
            date_expiration.setText("Le produit expire dans 30 jours");
        }
        else
        {
         date_expiration.setText("Le produit expire dans "+nb_jours+" jours");
        }
        v1.getChildren().addAll(espace1,espace2,nom_produit,espace3,prix_produit,espace4,date_mise,esp6,date_expiration);
        grid.add(v1, columnV1, rowV1);
      
     
        VBox v2=new VBox();
        
        Label esp1=new Label();
        esp1.setText("");
        
        Label nb_produits=new Label();
        nb_produits.setText("Nombre de "+p.getNom()+" est :"+p.getQuantite());
        
        Label esp4=new Label();
        esp1.setText("");
        
        Label prix_total=new Label();
        prix_total.setText("Prix total est "+p.getQuantite()*p.getPrix_vente()); 
        
        
        Label esp3=new Label();
        esp3.setText("");
        
        HBox h1=new HBox();
        
        JFXButton Modif_Produit=new JFXButton();
        Modif_Produit.setText("Modifier Produit");
        Modifier_Produit(Modif_Produit,p,url_image);
       
        
        JFXButton btnMAJ_date=new JFXButton();
        btnMAJ_date.setText("MAJ Date");
        RenouvellerDate_Publication(btnMAJ_date,p,date_expiration);
        
        h1.getChildren().addAll(Modif_Produit,btnMAJ_date);
        
        JFXButton Supp_Produit=new JFXButton();
        Supp_Produit.setText("Supprimer Produit");
        Supprimer_Produit(Supp_Produit, p);
       
         Label esp5=new Label();
        esp5.setText("");
        
        v2.getChildren().addAll(esp1,nb_produits,esp4,prix_total,esp3,h1,esp5,Supp_Produit);
        
        grid.add(v2, columnV2, rowV2);
        
    }
    
    //fonction pour configurer la structure du gridpane et scroll pane
    public  void Configurer_GridScroll(GridPane grid,ScrollPane scroll){
           //configuration grid
           grid.setHgap(10); 
           grid.setVgap(10);
           grid.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");
           grid.setMaxHeight(pan_publie.getHeight());
           grid.setMaxWidth(pan_publie.getPrefWidth());   
           grid.setPrefWidth(scroll.getPrefWidth());
           
           //configuration scroll
           scroll.setMaxHeight(pan_publie.getPrefHeight());
           scroll.setMaxWidth(pan_publie.getPrefWidth());
           scroll.setPrefWidth(500);
           scroll.setContent(grid);
           scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }
    
    //fonction pour supprimer un produits de la liste des produits publiés
    public void Supprimer_Produit(JFXButton btn,Produit p){
        ServiceProduit sp=new ServiceProduit();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sp.SupprimerProduit(p.getReference());
                pan_publie.getChildren().removeAll(scroll);
                pan_publie.getChildren().removeAll(s);
                g=new GridPane();
                s=new ScrollPane();
                Configurer_GridScroll(g,s);
                List<Produit> lst_P=sp.ListProduits_User(p.getId_user());
                for(int i=0;i<lst_P.size();i++)
                {
                CreerProduit(g, lst_P.get(i), 0, i, 1, i, 2, i,i);
                }
                pan_publie.getChildren().addAll(s);
            }
        }); 
    }
    
    //pour modifier l'un des produits publiés
    public void Modifier_Produit(JFXButton btn,Produit p,String url){
        ServiceProduit sp=new ServiceProduit();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Login Dialog");
            dialog.setHeaderText("Look, a Custom Login Dialog");
            // Set the button types.
            ButtonType loginButtonType = new ButtonType("Modifier", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);


            // Create the username and password labels and fields.
            GridPane grid_pane = new GridPane();
            grid_pane.setHgap(10);
            grid_pane.setVgap(10);
            grid_pane.setPadding(new Insets(20, 150, 10, 10));

            Label lbl_nom=new Label();
            lbl_nom.setText("Nom");
            TextField txt_nom = new TextField();
            
            Label lbl_prix=new Label();
            lbl_prix.setText("Prix");
            TextField txt_prix = new TextField();
            
            Label lbl_image=new Label();
            lbl_image.setText("Image");
            JFXButton btn_image=new JFXButton();
            btn_image.setText("Parcourir...");
            ParcourirImage(btn_image);
            Label lbl_url=new Label();
            lbl_url.setText(url);
            Label lbl_espace=new Label();
            lbl_espace.setText("");
            
            Label lbl_desc=new Label();
            lbl_desc.setText("Description");
            TextArea txt_description=new TextArea();
            
            Label lbl_type=new Label();
            lbl_type.setText("Type");
            ComboBox<String> combo_type=new ComboBox<>();
            combo_type.getItems().addAll("Sante","Bien etre");
        
            Label lbl_promotion=new Label();
            lbl_promotion.setText("Promotion");
            TextField txt_promotion = new TextField();
            
            Label lbl_quantite=new Label();
            lbl_quantite.setText("Quantite");
            TextField txt_quantite = new TextField();
            
            grid_pane.add(lbl_nom, 0, 0);
            grid_pane.add(lbl_prix, 0, 1);
            grid_pane.add(lbl_image, 0, 2);
            grid_pane.add(lbl_espace, 0, 3);
            grid_pane.add(lbl_desc, 0, 4);
            grid_pane.add(lbl_type, 0, 5);
            grid_pane.add(lbl_promotion, 0, 6);
            grid_pane.add(lbl_quantite, 0, 7);
            
            grid_pane.add(txt_nom, 1, 0);
            grid_pane.add(txt_prix, 1, 1);
            grid_pane.add(btn_image, 1, 2);
            grid_pane.add(lbl_url, 1, 3);
            grid_pane.add(txt_description, 1, 4);
            grid_pane.add(combo_type, 1, 5);
            grid_pane.add(txt_promotion, 1, 6);
            grid_pane.add(txt_quantite, 1, 7);
            dialog.getDialogPane().setContent(grid_pane);



            dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                p.setNom(txt_nom.getText());
                p.setPrix_vente(Float.parseFloat(txt_prix.getText()));
                p.setUrl_image(url_image);
                p.setDescription(txt_description.getText());
                p.setType(combo_type.getValue());
                p.setPromotion(Float.parseFloat(txt_promotion.getText()));
                p.setQuantite(Integer.parseInt(txt_quantite.getText()));
                sp.UpdateProduit(p);
                pan_publie.getChildren().removeAll(scroll);
                pan_publie.getChildren().removeAll(s);
                g=new GridPane();
                s=new ScrollPane();
                Configurer_GridScroll(g,s);
                List<Produit> lst_P=sp.ListProduits_User(p.getId_user());
                for(int i=0;i<lst_P.size();i++)
                {
                CreerProduit(g, lst_P.get(i), 0, i, 1, i, 2, i,i);
                }
                pan_publie.getChildren().addAll(s);
                }
                    return null;
});

Optional<Pair<String, String>> result = dialog.showAndWait();

result.ifPresent(usernamePassword -> {
    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
});

            }
        }); 
    }     
    
    //la fonction mére du filechooser
     private void ParcourirImage(JFXButton btn) {
         btn.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 final FileChooser fileChooser = new FileChooser();
                 configureFileChooser(fileChooser);
                 File file = fileChooser.showOpenDialog(pan_publie.getScene().getWindow());
                    if (file != null) {
                        openFile(file);
                    }
             }
         });
         
    }
   
    
   // Pour ouvrir le filechooser et choisir une image
   private void openFile(File file) {
       try {
            desktop.open(file);
            File dest = new File("C:\\wamp64\\www\\HealthAdvisorImages\\"+file.getName());
             Files.copy(file.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            url_image=dest.toPath().toString();
            System.err.println("je suis laaaaaaaaaaaaaaa"+dest.toPath().toString());
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
    private void InterfaceAjout(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLProduit.fxml")); 
            Parent root=loader.load();
            Scene s = pan_publie.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InterfaceFiltre(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAfficherProduit.fxml")); 
            Parent root=loader.load();
            Scene s = pan_publie.getScene(); 
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
            Scene s = pan_publie.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //calculer différence entre 2 date
    public static long DifférenceDates(Date date_mise){
        long diff=0,nbjours=0;
        try {
            DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            Date date_sys= date_format.parse(date_format.format(date));
             diff = date_sys.getTime()-date_mise.getTime();
             if((30-TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS))<0){
                 nbjours=TimeUnit.DAYS.convert(30, TimeUnit.MILLISECONDS);
             }
             else{
                 nbjours=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
             }
            //System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            
        } catch (ParseException ex) {
            Logger.getLogger(FXMLAfficherProduitPublieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nbjours;
    }
  
    //fonction pour Notifier l'utilisateur chaque semaine aprés la publication du produit pour lui rappeler la date expiration
    public void NotificationEmail_Expiration(List<Produit> lst)
    {   
        SendEmail SE=new SendEmail();
        Timer time=new Timer(true);
        System.out.println("liste:"+lst.size());
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                 
                    for(int i=0;i<lst.size();i++){
                        long nbjours=30-DifférenceDates(lst.get(i).getDate_mise());
                         System.err.println("nbjoursNotif"+nbjours);
                        if(nbjours==20){
                        SE.sendMail("habib.hentati@esprit.tn", "motdepasse58633912","habib-hentati@hotmail.com", "Expiration produit", "Attention votre produit sera expiré dans 20 jours");
                        CreerNotification("Attention votre annonce sera expiré dans 20 jours","Veuillez rennouveller la publication");
                        
                        }
                        else if(nbjours==10){
                        SE.sendMail("habib.hentati@esprit.tn", "motdepasse58633912","habib-hentati@hotmail.com", "Expiration produit", "Attention votre produit sera expiré dans 10 jours");
                        CreerNotification("Attention votre annonce sera expiré dans 10 jours","Veuillez rennouveller la publication");
                        }
                        else if(nbjours==1){
                        SE.sendMail("habib.hentati@esprit.tn", "motdepasse58633912","habib-hentati@hotmail.com", "Expiration produit", "Attention votre produit sera expiré demain");
                        CreerNotification("Attention votre annonce sera expiré demain","Veuillez rennouveller la publication");
                        
                        }
              
             }
                    time.cancel();
                    time.purge();
                    
                    
        });
                 }
        }, 1,5000);
        
        
    }
    
    public void SupprimerProduits_Expirés(List<Produit> lst){
        Timer time=new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    for(int i=0;i<lst.size();i++){
                        long nbjours=30-DifférenceDates(lst.get(i).getDate_mise());
                        System.err.println("nbjourrrsFonction supp"+nbjours);
                        if(nbjours==18){
                        servP.SupprimerProduit(lst.get(i).getReference());
                        CreerNotification("Date expiration achevée", "L'annonce est supprimée");
                        Reload();
            }
                   
        }
                    time.cancel();
                    time.purge();
        });
      }
        }, 1,5000);
        
    }
    
     //fonction reload page
    public void Reload(){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAfficherProduitPublie.fxml")); 
            Parent root=loader.load();
            Scene s = pan_publie.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //creer notification
    public void CreerNotification(String title,String text){
        Image img=new Image("/com/healthadvisor/ressources/question.png");
            org.controlsfx.control.Notifications notif=org.controlsfx.control.Notifications.create()
                    .graphic(new ImageView(img))
                    .title(title)
                    .text(text)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
            notif.show();
        
    }
    
    //renouveller la date de publication du produit de 30jours
    public void RenouvellerDate_Publication(JFXButton btn,Produit p,Label lblNB_jours)
    {   
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Date nv_date = new Date();
                java.sql.Date sqlnv_date = new java.sql.Date(nv_date.getTime());
                Produit pdt=servP.ConsulterProduit(p.getReference());
                pdt.setDate_mise(sqlnv_date);
                servP.UpdateProduit(pdt);
                lblNB_jours.setText("Le produit expire dans 30 jours");
                Reload();
            }
        }); 
    }
    
    
}
