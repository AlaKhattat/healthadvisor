
package com.healthadvisor.javafx.boutique;

import com.healthadvisor.entities.Commande;
import com.healthadvisor.entities.Ligne_Commande;
import com.healthadvisor.javamail.pdf;
import com.healthadvisor.entities.Produit;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import static com.healthadvisor.javafx.login_fx.FXMLLoginController.panier;
import static com.healthadvisor.javamail.EmailAttachmentSender.sendEmailWithAttachments;
import com.healthadvisor.javamail.SendEmail;
import com.healthadvisor.service.impl.ServiceCommande;
import com.healthadvisor.service.impl.ServiceLigne_Commande;
import com.healthadvisor.service.impl.ServiceProduit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import health_advisor.FXMLHomeViewController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;


public class FXMLAfficherPanierController implements Initializable {

    @FXML
    private AnchorPane pan_panier;

    @FXML
    private Button btnInterface_Ajout;
    @FXML
    private Button btnInterface_Filtre;
    
   
    private ScrollPane scroll =new ScrollPane();
    private GridPane   grid=new GridPane();
    private GridPane g;
    private ScrollPane s;
    @FXML
    private Button btnInterface_Publie;
    @FXML
    private Label lblPrix_total;
    
    ServiceProduit servP=new ServiceProduit();
    ServiceCommande servC=new ServiceCommande();
    ServiceLigne_Commande servLC=new ServiceLigne_Commande();
    List<ArrayList> lst_cmd;
    @FXML
    private Button btnPasser_Commande;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //afficher prixtotal des produits du panier
        lblPrix_total.setText("Le prix total est :"+CalculerPrix_Total(FXMLLoginController.panier));
        
        
        List<Produit> lst_P=new ArrayList<>();
        for(int i=0;i<FXMLLoginController.panier.size();i++){
            lst_P.add(servP.ConsulterProduit((String) FXMLLoginController.panier.get(i).get(0)));  
                }
       
       
        Configurer_GridScroll(grid,scroll);
        for(int i=0;i<lst_P.size();i++)
            {
                CreerProduit(grid, lst_P.get(i), 0, i, 1, i, 2, i,i);
                System.err.println("i="+i+""+lst_P.get(i).toString());
            }
        pan_panier.getChildren().addAll(scroll);
      
    
    }    
    
    //creere instanciation d'un produit
    public void CreerProduit(GridPane grid ,Produit p,int columnIMG,int rowIMG,int columnV1,int rowV1,int columnV2,int rowV2,int indice_prod_panier){
    
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
          
          v1.getChildren().addAll(espace1,espace2,nom_produit,espace3,prix_produit,espace4,date_mise);
          grid.add(v1, columnV1, rowV1);
          
          
          VBox v2=new VBox();
          
          Label esp1=new Label();
          esp1.setText("");
          
          Label nb_produits=new Label();
          nb_produits.setText("Nombre de "+p.getNom()+" est :"+FXMLLoginController.panier.get(indice_prod_panier).get(1).toString());
          
          Label esp4=new Label();
          esp1.setText("");
          
          Label prix_total=new Label();
          float prix=servP.ConsulterProduit((String) FXMLLoginController.panier.get(indice_prod_panier).get(0)).getPrix_vente()* Integer.parseInt(FXMLLoginController.panier.get(indice_prod_panier).get(1).toString());
          float promotion=(prix*p.getPromotion())/100;
          prix_total.setText("Prix total est :"+(prix-promotion)+"DT");
          
          
          Label esp3=new Label();
          esp3.setText("");
          
          JFXButton Diminuer_NBprod=new JFXButton();
          Diminuer_NBprod.setText("Diminuer Nbre");
          DisableButton_Diminuer(Diminuer_NBprod,indice_prod_panier);
          DiminuerNB_Produits(Diminuer_NBprod,indice_prod_panier,nb_produits,prix_total,p,Diminuer_NBprod);
          
          JFXButton Supp_Panier=new JFXButton();
          Supp_Panier.setText("Retirer du Panier");
          SupprimerProduit_Panier(Supp_Panier, indice_prod_panier,p);
          
          Label esp5=new Label();
          esp5.setText("");
          
          
          
          v2.getChildren().addAll(esp1,nb_produits,esp4,prix_total,esp3,Diminuer_NBprod,Supp_Panier);
          
          
          grid.add(v2, columnV2, rowV2);
          
     
        
    }
    
    //fonction pour configurer la structure du gridpane et scroll pane
    public  void Configurer_GridScroll(GridPane grid,ScrollPane scroll){
           //configuration grid
           grid.setHgap(10); 
           grid.setVgap(10);
           grid.setMaxHeight(pan_panier.getHeight());
           grid.setMaxWidth(pan_panier.getPrefWidth());   
           grid.setPrefWidth(scroll.getPrefWidth());
           
           //configuration scroll
           scroll.setMaxHeight(pan_panier.getPrefHeight());
           scroll.setMaxWidth(pan_panier.getPrefWidth());
           scroll.setPrefWidth(500);
           scroll.setContent(grid);
           scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
           scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }
    
    public void DiminuerNB_Produits(JFXButton btn,int indice_produit,Label lbl_NBproduits,Label lblPrix,Produit p,JFXButton btn_dimin){
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int nb_prod=(int) FXMLLoginController.panier.get(indice_produit).get(1);
                List<Integer> choices = new ArrayList<>();
                for(int i=1;i<nb_prod;i++){
                    choices.add(i);
                }
               
                ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, choices);
                dialog.setTitle("Choice Dialog");
                dialog.setHeaderText("Look, a Choice Dialog");
                dialog.setContentText("Choisir le nouveau nbre de produit:");

                // Traditional way to get the response value.
                Optional<Integer> result = dialog.showAndWait();
                if (result.isPresent()){
                    
                    lblPrix.setText("Le prix total est"+CalculerPrix_DiminuerQte(p,Integer.parseInt(FXMLLoginController.panier.get(indice_produit).get(1).toString()),result.get()));
                    FXMLLoginController.panier.get(indice_produit).set(1,Integer.parseInt(FXMLLoginController.panier.get(indice_produit).get(1).toString())-result.get());
                    lbl_NBproduits.setText("Nombre de "+p.getNom()+" est :"+FXMLLoginController.panier.get(indice_produit).get(1).toString());
                    lblPrix_total.setText("Le prix total est"+CalculerPrix_Total(FXMLLoginController.panier));
                    Reload();
                    
        }

                // The Java 8 way to get the response value (with lambda expression).
               // result.ifPresent(letter -> System.out.println("Your choice: " + letter));
                 
            }
        });
       
    }
    
    //Supprimer un produit du panier
    public void SupprimerProduit_Panier(JFXButton btn,int indice_produit,Produit p){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                FXMLLoginController.panier.remove(indice_produit);
                lblPrix_total.setText("Le prix total est"+CalculerPrix_Total(FXMLLoginController.panier));
                FXMLLoginController.nb_produits_panier=FXMLLoginController.nb_produits_panier-1;
                pan_panier.getChildren().removeAll(scroll);
                pan_panier.getChildren().removeAll(s);
                g=new GridPane();
                s=new ScrollPane();
                Configurer_GridScroll(g,s);
                List<Produit> lst_P=new ArrayList<>();
                for(int i=0;i<FXMLLoginController.panier.size();i++){
                    lst_P.add(servP.ConsulterProduit((String) FXMLLoginController.panier.get(i).get(0)));  
                }
                for(int i=0;i<lst_P.size();i++)
                {
                CreerProduit(g, lst_P.get(i), 0, i, 1, i, 2, i,i);
                }
                pan_panier.getChildren().addAll(s);
            }
        });
    }
  
    @FXML
    private void InterfaceAjouter(ActionEvent event) {
         try {
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("FXMLProduit.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void InterfacePublie(ActionEvent event) {
        try {
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("FXMLAfficherProduitPublie.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    //Reload page
    public void Reload(){
      
        try {
        AnchorPane afficherproduit = FXMLLoader.load(getClass().getResource("FXMLAfficherPanier.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane,afficherproduit);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //alert a afficher lors du passage de la commande
    public void Alert_PasserCommande()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Impossible de passer une commande via l'application desktop de Health Advisor il faut consulter le site web afin de poursuivre la procédure de payement pour raisons de sécurité");
        alert.showAndWait();
    }

    @FXML
    private void PasserCommande(ActionEvent event) {
        lst_cmd=new ArrayList<>();
         String prix="";
        try {
            Commande cmd=new Commande();
            cmd.setID_client(FXMLLoginController.pseudo);
            DateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            cmd.setDate_commande(date_format.parse(date_format.format(date)));
            cmd.setMode_payement("paypal");
            cmd.setReference_commande("123464"); // ce code doit étre généré aléatoirement
            servC.AjouterCommande(cmd);
            Commande c=servC.ConsulterCommande(cmd.getReference_commande());
            for(int i=0;i<FXMLLoginController.panier.size();i++){
                Ligne_Commande lc=new Ligne_Commande();
                lc.setId_commande(c.getNum_commande());
                Produit p=servP.ConsulterProduit(FXMLLoginController.panier.get(i).get(0).toString());
                lc.setId_produit(p.getId_produit());
                lc.setQuantite(Integer.parseInt(panier.get(i).get(1).toString()));
                lc.setPrix_commande(CalculerPrix_Total(panier));
                prix=""+CalculerPrix_Total(panier);
                ArrayList array=new ArrayList();
                array.add(p.getNom());   
                array.add(lc.getQuantite());
                array.add(p.getPrix_vente());   
                lst_cmd.add(array);
                servLC.AjouterLigne_Commande(lc);
            }
            Alert_PasserCommande();
            
            //il faut preparer l'email de la facture
            pdf.savePdf("facture",lst_cmd,prix);
            String mailFrom = "habib.hentati@esprit.tn";
        String password = "motdepasse58633912";
 
        // message info
        String mailTo = "habib-hentati@hotmail.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";
 
        // attachments
        String[] attachFiles = new String[1];
       // attachFiles[0] = "C:/Users/HABOUB/Desktop/althere.jpeg";
       // attachFiles[1] = "C:/Users/HABOUB/Desktop/Prosit_4_Partie1.pdf";
        attachFiles[0] = "C:/Users/aaa/Documents/NetBeansProjects/healthadvisor/facture.pdf";
 
        try {
            sendEmailWithAttachments( mailFrom, password, mailTo,subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
            
           
        } catch (ParseException ex) {
            Logger.getLogger(FXMLAfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    //fonction calculer prix total panier
    public float CalculerPrix_Total(List<ArrayList> lst){
        float prix=0;
        for(int i=0;i<lst.size();i++){
            Produit p=servP.ConsulterProduit(lst.get(i).get(0).toString());
            if(p.getPromotion()==0.0){
                int Qte=Integer.parseInt(lst.get(i).get(1).toString());
                prix=prix+(Qte*p.getPrix_vente());
            }
            else{
            int Qte=Integer.parseInt(lst.get(i).get(1).toString());
            prix=prix+(Qte*p.getPrix_vente())-(((Integer.parseInt(lst.get(i).get(1).toString())*p.getPrix_vente())*p.getPromotion())/100);
        }
        }
        return prix;
    }
    
    //calculer Prix diminution
    public float CalculerPrix_DiminuerQte(Produit p,int qte_panier,int qte_dimin){
        float prix_final=0;
        if(p.getPromotion()==0.0){
            
            float PrixDimin=qte_dimin*p.getPrix_vente();
            float prixpanier=qte_panier*p.getPrix_vente();
            System.err.println("hihihihihihi"+PrixDimin);
            System.err.println("hihihihihihi"+prixpanier);
            prix_final=prixpanier-PrixDimin;
        }
        else{
           
            float PrixDimin=(qte_dimin*p.getPrix_vente())-(qte_dimin*p.getPrix_vente()*p.getPromotion())/100;
            float prixpanier=((qte_panier*p.getPrix_vente()))-((qte_panier*p.getPrix_vente()*p.getPromotion())/100);
            prix_final=prixpanier-PrixDimin;
        }
        return prix_final;
    }
    
    //griser button diminuer si qte=1
    public void DisableButton_Diminuer(JFXButton btn,int indice_produit){
        if(Integer.parseInt(FXMLLoginController.panier.get(indice_produit).get(1).toString())==1){
            btn.setDisable(true);
        }
    }
}
