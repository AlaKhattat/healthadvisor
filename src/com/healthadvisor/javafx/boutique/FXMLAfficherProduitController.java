package com.healthadvisor.javafx.boutique;


import com.healthadvisor.entities.Produit;
import com.healthadvisor.service.impl.ServiceProduit;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;


public class FXMLAfficherProduitController implements Initializable {

    @FXML
    private AnchorPane pan;

    @FXML
    private AnchorPane filtre;

    @FXML
    private DatePicker date_min;

    @FXML
    private DatePicker date_max;

    @FXML
    private Button btn_passer;

    @FXML
    private Button btnInterface_Ajout;

    @FXML
    private Button btnInterface_Publie;

    @FXML
    private JFXTextField txt_promMIN;

    @FXML
    private JFXTextField txt_promMAX;

    @FXML
    private JFXTextField txt_prixMIN;

    @FXML
    private JFXTextField txt_prixMAX;

    @FXML
    private JFXComboBox<String> ch_type;

    @FXML
    private JFXCheckBox check_IMG;

    @FXML
    private JFXButton btn_filtre;

    @FXML
    private AnchorPane lst_produits;

    @FXML
    private Label lbl_panier;

    @FXML
    private Label lblpanier_NB;
    //////////////////////////////////////////////////////////////////////////
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
    
    //////////////////////////////////////////////////////////////////////////
    private ScrollPane scroll =new ScrollPane();
    private GridPane   grid=new GridPane();
    GridPane g;
    ScrollPane s;
    private boolean prom_minC=true,prom_maxC=true,date_minC=true,date_maxC=true,prix_minC=true,prix_maxC=true;
    public static List<ArrayList> panier=new ArrayList<>();
    //cette liste pour pouvoir manipulé les quantités des produits dans le panier
    private List<Produit> lst_P ;
    ServiceProduit service_P=new ServiceProduit();
    //********************************héthi lézém nzidha 3and 5atout
    public static int nb_produits_panier=panier.size();
   // public static float Prix_Final=0;
    @FXML
    private BorderPane pan_notif;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
   Controle_Date(date_min);
   Controle_Date(date_max);
   btnInterface_Ajout.setStyle("");
    
        
        
    //remplir le combobox
    ch_type.getItems().addAll("Sante","Bien etre");
    
    //set the product number into image
    lblpanier_NB.setText(""+nb_produits_panier);
    
    
     lst_P=service_P.ConsulterListe_Produits();
     //***************************************suppression des produits expirer
     
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
        
        JFXButton Ajt_Panier=new JFXButton();
         Ajt_Panier.setText("Ajouter Panier");
         Ajouter_Panier(Ajt_Panier,p);
        
        Label esp4=new Label();
        esp4.setText("");
        
        JFXButton Signaler_Prod=new JFXButton();
        Signaler_Prod.setText("Signaler");
        SignalerProduit(Signaler_Prod, p);
        
        v2.getChildren().addAll(esp1,esp2,promotion,esp3,Ajt_Panier,esp4,Signaler_Prod);
        grid.add(v2, columnV2, rowV2);
        
    }

    @FXML
    private void Recherche_Avancee(ActionEvent event) {
            List<Produit> lst_prom=new ArrayList<>();
            List<Produit> lst_prix=new ArrayList<>();
            List<Produit> lst_date=new ArrayList<>();
            List<Produit> lst_img=new ArrayList<>();
            List<Produit> lst_type=new ArrayList<>();
          System.err.println(prom_maxC+" dfgd  "+prom_minC+"  gdfd "+prix_minC+"  fdgd"+prix_maxC);
            if(prom_maxC==false || prom_minC==false || prix_minC==false || prix_maxC==false ){
                showPopup("Erreur de saisie","veuillez corriger les erreurs et réessayer", 0);
            }
            else{
                System.err.println(prom_maxC+"   "+prom_minC+"   "+prix_minC+"  "+prix_maxC);
                if(txt_promMIN.getText().isEmpty()&&txt_promMAX.getText().isEmpty()){
                      lst_prom=service_P.ConsulterListe_Produits();
                      
                
                }
                else if(txt_promMIN.getText().isEmpty()){
                     lst_prom=service_P.ListeProduits_Promotion(0,Float.parseFloat(txt_promMAX.getText()));
                
                }
                else if(txt_promMAX.getText().isEmpty()){
                     lst_prom=service_P.ListeProduits_Promotion(Float.parseFloat(txt_promMIN.getText()));
                    
                }
                 
                else{
                     lst_prom=service_P.ListeProduits_Promotion(Float.parseFloat(txt_promMIN.getText()),Float.parseFloat(txt_promMAX.getText()));
                    
                }
                if (txt_prixMIN.getText().isEmpty() && txt_prixMAX.getText().isEmpty()) {
                    
                    lst_prix=service_P.ConsulterListe_Produits();
                    lst_prix.retainAll(lst_prom);
                    
                     
                }
                else if(txt_prixMIN.getText().isEmpty()){
                    lst_prix=service_P.ListProduits_Prix(0,Float.parseFloat(txt_prixMAX.getText()));
                    lst_prix.retainAll(lst_prom);
                    
                }
                else if(txt_prixMAX.getText().isEmpty()){
                    lst_prix=service_P.ListProduits_Prix(Float.parseFloat(txt_prixMIN.getText()));
                    lst_prix.retainAll(lst_prom);
                    
                }
                
                else {
                    lst_prix=service_P.ListProduits_Prix(Float.parseFloat(txt_prixMIN.getText()),Float.parseFloat(txt_prixMAX.getText()));
                    lst_prix.retainAll(lst_prom);
                    
                }
                
                
                if(date_min.getValue()==null && date_max.getValue()==null){
                   lst_date=service_P.ConsulterListe_Produits();
                   lst_date.retainAll(lst_prix);
                   
                   
               }
                else if(date_min.getValue()==null){
                   lst_date=service_P.ListProduits_DateMax(java.sql.Date.valueOf(this.date_max.getValue()) );
                   lst_date.retainAll(lst_prix);
                  
               }
               else if(date_max.getValue()==null){
                   lst_date=service_P.ListProduits_Date(java.sql.Date.valueOf(this.date_min.getValue()) );
                   lst_date.retainAll(lst_prix);
               }
                
               else{
                   lst_date=service_P.ListProduits_Date(java.sql.Date.valueOf(this.date_min.getValue()),java.sql.Date.valueOf(this.date_max.getValue()) );
                   lst_date.retainAll(lst_prix);
               }
                
                if(check_IMG.isSelected()==true){
                lst_img=service_P.ListProduits_Image(check_IMG.isSelected());
                lst_img.retainAll(lst_date);
                }
                else{
                    
                    lst_img=service_P.ConsulterListe_Produits();
                    lst_img.retainAll(lst_date);
                    
                }
            
            if(ch_type.getSelectionModel().isEmpty()){
                lst_type=service_P.ConsulterListe_Produits();
                lst_type.retainAll(lst_img);
               
            }
            else{
                 lst_type=service_P.ListProduits_Categorie(ch_type.getValue());
                 lst_type.retainAll(lst_img);
            }
           
            
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
   
    }

  //fonction pour configurer la structure du gridpane et scroll pane
    public  void Configurer_GridScroll(GridPane grid,ScrollPane scroll){
           //configuration grid
           grid.setHgap(10); 
           grid.setVgap(10);
           grid.setPrefHeight(431);
           grid.setPrefWidth(1000);   
           grid.setStyle("-fx-background-color:rgba(2, 84, 217, 0.67)");
           //configuration scroll
           scroll.setPrefHeight(431);
           scroll.setPrefWidth(1000);
           scroll.setContent(grid);
           scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
           scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }

   //fonction pour ajouter des produits au panier
    public void Ajouter_Panier(JFXButton btn,Produit p){
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
                   nb_produits_panier=nb_produits_panier+1;
                   lblpanier_NB.setText(""+nb_produits_panier);
                    }
                }
                else
                {
                 List<Integer> lst_qte=new ArrayList<>();
                 for(int i=0;i<p.getQuantite();i++){
                     lst_qte.add(i+1);
                 }
                ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1, lst_qte);
                dialog.setTitle("Choice Dialog");
                dialog.setHeaderText("Look, a Choice Dialog");
                dialog.setContentText("Choisissez nbre de produit:");

                Optional<Integer> result = dialog.showAndWait();
                if (result.isPresent()){
                   if(MAJ_Nbr_Produits(p) != -1)
                   {   int qte=Integer.parseInt(panier.get(MAJ_Nbr_Produits(p)).get(1).toString())+result.get();
                       if(qte>p.getQuantite()){
                           panier.get(MAJ_Nbr_Produits(p)).set(1, p.getQuantite());
                       }
                else{
                      panier.get(MAJ_Nbr_Produits(p)).set(1, Integer.parseInt(panier.get(MAJ_Nbr_Produits(p)).get(1).toString())+ result.get());
                   }
                   }
                else{
                   ArrayList<Object> nb_pdt=new ArrayList<>();
                   nb_pdt.add(0,p.getReference());
                   nb_pdt.add(1, result.get());
                   panier.add(nb_pdt);
                   nb_produits_panier=nb_produits_panier+1;
                   lblpanier_NB.setText(""+nb_produits_panier);
                   //Prix_Final=Prix_Final+(p.getPrix_vente()*result.get())-((p.getPrix_vente()*result.get() * p.getPromotion())/100);
                   
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
    
    //Signaler un produit Indisérable
    public void SignalerProduit(JFXButton btn,Produit p){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Produit pdt=service_P.ConsulterProduit(p.getReference());
                pdt.setSignaler(1);
                service_P.UpdateProduit(pdt);
            }
        }); 
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

    @FXML
    private void txt_promMIN_C(KeyEvent event) {
       if(ControleSaisie_Float(txt_promMIN, prom_minC, prom_maxC, txt_promMAX,1)==true){
        prom_minC=false;
       }
    }

    @FXML
    private void txt_promMAX_C(KeyEvent event) {
       if(ControleSaisie_Float(txt_promMIN, prom_minC, prom_maxC, txt_promMAX,1)==true){
           prom_maxC=false;
       }
    }

    @FXML
    private void txt_prixMIN_C(KeyEvent event) {
        if(ControleSaisie_Float(txt_prixMIN, prix_maxC, prix_minC, txt_prixMAX,0)==true){
         prix_minC=false;   
        }
        
    }

    @FXML
    private void txt_prixMAX_C(KeyEvent event) {
        if(ControleSaisie_Float(txt_prixMIN, prix_maxC, prix_minC, txt_prixMAX,0)==true){
         prix_maxC=false;   
        }
    }

    
    
    

     public boolean ControleSaisie_Float(JFXTextField txtmin,boolean verifmin,boolean verifmax,JFXTextField txtmax,int x){
       boolean res=false;
         String min=txtmin.getText();
        String max=txtmax.getText();
       try{
          
       float res_min=Float.parseFloat(min);
       float res_max=Float.parseFloat(max);
       if(res_min<0 || res_max <0){
           if(x==1){
               if(res_min>=100 || res_max>=100){
                   txtmin.setFocusColor(Color.RED);
                    txtmax.setFocusColor(Color.RED);
                    verifmin=false;
                    verifmax=false;
               }
           }
           txtmin.setFocusColor(Color.RED);
           txtmax.setFocusColor(Color.RED);
           verifmin=false;
           verifmax=false;
           System.err.println("hhhhhhhh");
       }
       else{
       if(res_max<=res_min){
           txtmin.setFocusColor(Color.RED);
           txtmax.setFocusColor(Color.RED);
           verifmin=false;
           verifmax=false;
           System.err.println("kkkkkkkkkkk");
       }
       else{
           txtmin.setFocusColor(Color.BLUE);
            txtmax.setFocusColor(Color.BLUE);
            verifmin=true;
            verifmax=true;
            
            
       }
           }
       }catch(NumberFormatException ex)  {
       txtmin.setFocusColor(Color.RED);
       txtmin.setFocusColor(Color.RED);
       verifmin=false;
       verifmax=false;
       res=true;
           System.err.println(verifmin+"   "+verifmax);
    }
       return res;
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

   public void Controle_Date(DatePicker date){
                 final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
     public DateCell call(final DatePicker datePicker) {
         return new DateCell() {
             @Override public void updateItem(LocalDate item, boolean empty) {
                 super.updateItem(item, empty);

                 if (item.isAfter(LocalDate.now())) {
                     setStyle("-fx-background-color: #ff4444;");
                     setDisable(true);
                 }
                
             }
         };
     }
 };

  date.setDayCellFactory(dayCellFactory);
    
   }
    
   
   //calculer différence entre 2 date
    public static long DifférenceDates(Date date_min,Date date_max){
             long diff=0,nbjours=0;
             diff = date_max.getTime()-date_min.getTime();
             nbjours=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
             
        return nbjours;
    }
   
}
