/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package com.healthadvisor.javafx.boutique;


import com.healthadvisor.entities.Produit;
import com.healthadvisor.service.impl.ServiceProduit;
import com.jfoenix.controls.JFXTextArea;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class FXMLAfficherProduitController implements Initializable {

    @FXML
    private AnchorPane pan;
    @FXML
    private AnchorPane filtre;
    @FXML
    private AnchorPane lst_produits;
    @FXML
    private Button btn_filtre;
    @FXML
    private TextField txt_promMIN;
    @FXML
    private TextField txt_promMAX;
    @FXML
    private TextField txt_prixMIN;
    @FXML
    private TextField txt_prixMAX;
    
    @FXML
    private DatePicker date_min;
    @FXML
    private DatePicker date_max;
    @FXML
    private ComboBox<String> ch_type;
    @FXML
    private CheckBox check_IMG;
    
    private ScrollPane scroll =new ScrollPane();
    private GridPane   grid=new GridPane();
    GridPane g;
    ScrollPane s;
    public static ArrayList<ArrayList> panier=new ArrayList<>();
    @FXML
    private Button btn_passer;
    @FXML
    private Button btnInterface_Ajout;
    @FXML
    private Button btnInterface_Publie;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    //remplir le combobox
    ch_type.getItems().addAll("Sante","Bien etre");
    
    ServiceProduit sp=new ServiceProduit();
    List<Produit> lst_P=sp.ConsulterListe_Produits();
    Configurer_GridScroll(grid,scroll);
    for(int i=0;i<lst_P.size();i++)
    {
        CreerProduit(grid, lst_P.get(i), 0, i, 1, i, 2, i, 3, i);
    }
    lst_produits.getChildren().addAll(scroll);
    lst_produits.getChildren().addAll(filtre);
    pan.getChildren().add(filtre);
   
   
    }    

    //creere instanciation d'un produit
    public void CreerProduit(GridPane grid ,Produit p,int columnIMG,int rowIMG,int columnV1,int rowV1,int columnDESC,int rowDESC,int columnV2,int rowV2){
        
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
      
        JFXTextArea description =new JFXTextArea() ;
        description.setText(p.getDescription());
        description.setPrefWidth(500);
        //description.setStyle("-fx-vertical-align: middle; ");
        description.setEditable(false);
        description.setDisable(false);
        //description.setStyle("-fx-border: 2px solid; -fx-border-color: red;");    
        grid.add(description,columnDESC, rowDESC);
        
        VBox v2=new VBox();
        
        Label esp1=new Label();
        esp1.setText("");
        
        Label esp2=new Label();
        esp2.setText("");
        
        Label promotion=new Label();
        if(p.getPromotion() == 0){
            promotion.setText("Aucune Promotion");
        }
        else{
        promotion.setText(p.getPromotion()+"%"); 
        }
        Label esp3=new Label();
        esp3.setText("");
        
        Button Ajt_Panier=new Button();
         Ajt_Panier.setText("Ajouter Panier");
         Ajouter_Panier(Ajt_Panier,p);
        
        Label esp4=new Label();
        esp4.setText("");
        
        v2.getChildren().addAll(esp1,esp2,promotion,esp3,Ajt_Panier,esp4);
        grid.add(v2, columnV2, rowV2);
        
    }

    @FXML
    private void Recherche_Avancee(ActionEvent event) {
            ServiceProduit sp=new ServiceProduit();
            List<Produit> lst_prom=sp.ListeProduits_Promotion(Float.parseFloat(txt_promMIN.getText()),Float.parseFloat(txt_promMAX.getText()));
            List<Produit> lst_prix=sp.ListProduits_Prix(Float.parseFloat(txt_prixMIN.getText()),Float.parseFloat(txt_prixMAX.getText()));
            lst_prix.retainAll(lst_prom);
            List<Produit> lst_date=sp.ListProduits_Date(java.sql.Date.valueOf(this.date_min.getValue()),java.sql.Date.valueOf(this.date_max.getValue()) );
            lst_date.retainAll(lst_prix);
            List<Produit> lst_img=sp.ListProduits_Image(check_IMG.isSelected());
            lst_img.retainAll(lst_date);
            List<Produit> lst_type=sp.ListProduits_Categorie(ch_type.getValue());
            lst_type.retainAll(lst_img);
            
            lst_produits.getChildren().removeAll(scroll);
            lst_produits.getChildren().removeAll(s);
          
            g=new GridPane();
            s=new ScrollPane();
    
          
           Configurer_GridScroll(g,s);
          for(int i=0;i<lst_type.size();i++)
            {
                CreerProduit(g, lst_type.get(i), 0, i, 1, i, 2, i, 3, i);
            }
       
       
       lst_produits.getChildren().addAll(s);
       
   
    }

  //fonction pour configurer la structure du gridpane et scroll pane
    public  void Configurer_GridScroll(GridPane grid,ScrollPane scroll){
           //configuration grid
           grid.setHgap(10); 
           grid.setVgap(10);
           grid.setMaxHeight(pan.getHeight());
           grid.setMaxWidth(pan.getPrefWidth());   
           
           //configuration scroll
           scroll.setMaxHeight(lst_produits.getPrefHeight());
           scroll.setMaxWidth(pan.getPrefWidth());
           scroll.setContent(grid);
           scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
           scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }

   //fonction pour ajouter des produits au panier
    public void Ajouter_Panier(Button btn,Produit p){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(p.getQuantite()== 1){
                    
                   if(MAJ_Nbr_Produits(p) != -1){
                       
                       panier.get(MAJ_Nbr_Produits(p)).set(1, Integer.parseInt(panier.get(MAJ_Nbr_Produits(p)).get(1).toString()));
                   }
                else{
                   ArrayList<Object> nb_pdt=new ArrayList<>();
                   nb_pdt.add(0,p.getReference());
                   nb_pdt.add(1, 1);
                   panier.add(nb_pdt);
                    }
                }
                else{
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Nombre de produits");
                dialog.setHeaderText(null);
                dialog.setContentText("SVP Entrez le nombre de produit :");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                   if(MAJ_Nbr_Produits(p) != -1){
                       
                       panier.get(MAJ_Nbr_Produits(p)).set(1, Integer.parseInt(panier.get(MAJ_Nbr_Produits(p)).get(1).toString())+  Integer.parseInt(result.get()));
                   }
                else{
                   ArrayList<Object> nb_pdt=new ArrayList<>();
                   nb_pdt.add(0,p.getReference());
                   nb_pdt.add(1, Integer.parseInt(result.get()));
                   panier.add(nb_pdt);
                                       }
            
                // The Java 8 way to get the response value (with lambda expression).
               // result.ifPresent(name -> System.out.println("Your name: " + name));
                
                }
                }
            }
        });
    }

    @FXML
    private void Passer(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLAfficherPanier.fxml")); 
            Parent root=loader.load();
            Scene s = pan.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //fonction pour ne pas ajouter le meme produit dans le panier s'il existe et incrémenter le nombre 
    public int MAJ_Nbr_Produits(Produit p){
        int  pos=-1;
        for(int i=0;i<panier.size();i++){
           
                if(panier.get(i).get(0).toString().equalsIgnoreCase(p.getReference())){
                    pos=i;
                }
            
        }
        return pos;
    }

    @FXML
    private void InterfaceAjouter(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLProduit.fxml")); 
            Parent root=loader.load();
            Scene s = pan.getScene(); 
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
            Scene s = pan.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    
    
}
*/