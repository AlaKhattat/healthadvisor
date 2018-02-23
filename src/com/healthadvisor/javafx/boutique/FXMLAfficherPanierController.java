/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.boutique;

import com.healthadvisor.entities.Produit;
import static com.healthadvisor.javafx.login_fx.FXMLLoginController.panier;
import com.healthadvisor.service.impl.ServiceProduit;
import com.jfoenix.controls.JFXTextArea;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceProduit sp=new ServiceProduit();
        List<Produit> lst_P=new ArrayList<>();
        for(int i=0;i<panier.size();i++){
            lst_P.add(sp.ConsulterProduit((String) panier.get(i).get(0)));  
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
        
        v1.getChildren().addAll(espace1,espace2,nom_produit,espace3,prix_produit,espace4,date_mise);
        grid.add(v1, columnV1, rowV1);
      
     
        VBox v2=new VBox();
        
        Label esp1=new Label();
        esp1.setText("");
        
        Label nb_produits=new Label();
        nb_produits.setText("Nombre de "+p.getNom()+" est :"+panier.get(indice_prod_panier).get(1).toString());
        
        Label esp4=new Label();
        esp1.setText("");
        
        Label prix_total=new Label();
        prix_total.setText("Prix total est :"+sp.ConsulterProduit((String) panier.get(indice_prod_panier).get(0)).getPrix_vente()* Integer.parseInt(panier.get(indice_prod_panier).get(1).toString())+"DT"); 
        
        
        Label esp3=new Label();
        esp3.setText("");
        
        Button Diminuer_NBprod=new Button();
         Diminuer_NBprod.setText("Diminuer Nbre");
         DiminuerNB_Produits(Diminuer_NBprod,indice_prod_panier,nb_produits,prix_total,p.getNom());
        
        Button Supp_Panier=new Button();
        Supp_Panier.setText("Retirer du Panier");
        SupprimerProduit_Panier(Supp_Panier, indice_prod_panier);
        
        Label esp5=new Label();
        esp5.setText("");
        
        Button PasserCommande=new Button();
        PasserCommande.setText("Passer Commande");
        
        v2.getChildren().addAll(esp1,nb_produits,esp4,prix_total,esp3,Diminuer_NBprod,Supp_Panier,PasserCommande);
        
        
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
    
    public void DiminuerNB_Produits(Button btn,int indice_produit,Label lbl_NBproduits,Label lblPrix,String Nom_Produit){
        ServiceProduit sp=new ServiceProduit();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int nb_prod=(int) panier.get(indice_produit).get(1);
                List<Integer> choices = new ArrayList<>();
                for(int i=1;i<=nb_prod;i++){
                    choices.add(i);
                }

                ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, choices);
                dialog.setTitle("Choice Dialog");
                dialog.setHeaderText("Look, a Choice Dialog");
                dialog.setContentText("Choisir le nouveau nbre de produit:");

                // Traditional way to get the response value.
                Optional<Integer> result = dialog.showAndWait();
                if (result.isPresent()){
                    panier.get(indice_produit).set(1, result.get());
                    lbl_NBproduits.setText("Nombre de "+Nom_Produit+" est :"+panier.get(indice_produit).get(1).toString());
                    lblPrix.setText("Prix total est :"+sp.ConsulterProduit((String) panier.get(indice_produit).get(0)).getPrix_vente()* Integer.parseInt(panier.get(indice_produit).get(1).toString())+"DT");
        }

                // The Java 8 way to get the response value (with lambda expression).
               // result.ifPresent(letter -> System.out.println("Your choice: " + letter));
                 
            }
        });
       
    }
    
    //Supprimer un produit du panier
    public void SupprimerProduit_Panier(Button btn,int indice_produit){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                panier.remove(indice_produit);
                System.err.println(panier.size());
                pan_panier.getChildren().removeAll(scroll);
                pan_panier.getChildren().removeAll(s);
                g=new GridPane();
                s=new ScrollPane();
                Configurer_GridScroll(g,s);
                ServiceProduit sp=new ServiceProduit();
                List<Produit> lst_P=new ArrayList<>();
                for(int i=0;i<panier.size();i++){
                    lst_P.add(sp.ConsulterProduit((String) panier.get(i).get(0)));  
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
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLProduit.fxml")); 
            Parent root=loader.load();
            Scene s = pan_panier.getScene(); 
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
            Scene s = pan_panier.getScene(); 
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
            Scene s = pan_panier.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    
}
