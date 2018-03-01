/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.bienetre;

import com.healthadvisor.entities.Aliment;
import com.healthadvisor.entities.InfoSante;
import com.healthadvisor.entities.Notification;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.ProgrammeRegime;
import com.healthadvisor.entities.Regime;
import com.healthadvisor.entities.Sport;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.enumeration.StatutNotificationEnum;
import com.healthadvisor.enumeration.Type_Aliment;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionInfoSante;
import com.healthadvisor.service.impl.GestionRegime;
import com.healthadvisor.service.impl.GestionUserRegime;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.nutritionix.client.NutritionixClientBuilder;
import com.nutritionix.client.NutritionixClientImpl;
import com.nutritionix.dto.Item;
import com.nutritionix.dto.SearchItem;
import com.nutritionix.dto.SearchResults;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javax.naming.directory.SearchResult;
import org.apache.log4j.BasicConfigurator;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class FXMLImcController implements Initializable {

    private AnchorPane pourcentage;
    @FXML
    private VBox box;
    @FXML
    private ImageView calculIMC;
    public  Timer time;
    @FXML
    private Label interpretIMC;
    @FXML
    private Label imcRegime;
    @FXML
    private JFXTextField poidRegime;
    @FXML
    private Pane allergies;
    @FXML
    private JFXCheckBox box_Allergi;
    @FXML
    private JFXTextField allergiesSupp;
    @FXML
    private JFXListView<String> listeAllergieSup;
    @FXML
    private JFXListView<String> listMaladies;
    private String buffer;
    private String bufferMaladies;
    @FXML
    private JFXTextField PoidsAPerdre;
    @FXML
    private JFXComboBox<String> regimeEffectue;
    @FXML
    private JFXComboBox<String> dureeRegime;
    @FXML
    private Label imcIdealRegime;
    @FXML
    private JFXTabPane tabRegime;
    @FXML
    private Tab suivreRegime11;
    @FXML
    private JFXRadioButton siSport;
    @FXML
    private ToggleGroup sport;
    @FXML
    private JFXRadioButton sansSport;
    @FXML
    private JFXTextField maladies;
    private Patient patient;
    @FXML
    private JFXComboBox<String> regimePropose;
    @FXML
    private TextArea infoRegime;
    @FXML
    private JFXListView<String> listeAliments;
    @FXML
    private VBox lesSports;
    @FXML
    private Tab suivreRegime1;
    @FXML
    private JFXListView<String> regimeDujour;
    @FXML
    private JFXComboBox<String> sportUser;
    @FXML
    private Tab suivreRegime121;
 
    @FXML
    private StackPane pane;
    
    private WebView view;
    @FXML
    private Text heureRegimeJeune;
    @FXML
    private JFXButton cancelJeune;
    @FXML
    private JFXButton startJeune;
    @FXML
    private StackPane pane1;
    @FXML
    private JFXTextField rechercheManger;
    private int heure;
    private int minute;
    @FXML
    private Tab suivreRegime1211;
    private JFXListView<String> ptitDej;
    private JFXListView<String> dejeuner;
    private JFXListView<String> gouter;
    private JFXListView<String> diner;  
    @FXML
    private StackPane pane11;
    @FXML
    private JFXListView<String> ptitdej_micro;
    @FXML
    private JFXListView<String> dej_micro;
    @FXML
    private JFXListView<String> gouter_micro;
    @FXML
    private JFXListView<String> diner_micro;
    @FXML
    private Tab suivreRegime122;
    @FXML
    private JFXListView<String> programmeProteine;
    @FXML
    private Tab suivreRegime1221;
    @FXML
    private JFXListView<String> programmeCalorie;
    @FXML
    private JFXComboBox<?> sportUser1;
    @FXML
    private ContextMenu resultatChearch;
    @FXML
    private JFXListView<String> calorieC;
    @FXML
    private MenuItem itemMenu;
    @FXML
    private Label totalCalorie;
    @FXML
    private JFXTextArea calorieMin;
    @FXML
    private JFXTabPane bienetre;
    @FXML
    private JFXTextField rechercheManger1;
    @FXML
    private ContextMenu resultatChearch1;
    @FXML
    private MenuItem itemMenu1;
    @FXML
    private JFXListView<?> calorieC1;
    @FXML
    private Label totalCalorie1;
    @FXML
    private JFXTextArea calorieMin1;
    @FXML
    private Tab sansIMC;
    @FXML
    private VBox error;
    
    public Patient getPatient() {
        return patient;
    }

      @FXML
    private Tab suivreRegime12;
    @FXML
    private Tab calorieCounter;
 public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
    public VBox getBox() {
        return box;
    }

    public void setBox(VBox box) {
        this.box = box;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
               
                FillProgressIndicator indicator = new FillProgressIndicator();
                this.box.getChildren().add(indicator);
                patient= new Patient(FXMLLoginController.pseudo,"", "","");
                System.out.println("patient:"+patient);
                GestionInfoSante g = new GestionInfoSante();
                System.out.println("patient est la :"+patient);
                InfoSante info =g.afficherInfoSante(patient.getLogin()); 
                if(info!=null)
                {
                      FillProgressIndicator fp= (FillProgressIndicator)box.getChildren().get(0);
                      Timer time1 = new Timer(true);
                      time1.schedule(new TimerTask() {
                          @Override
                          public void run() {
                              System.out.println("okkay");
                              animationCercle(fp,info.calculIMC(info));     

                             }
                      }, 3600);
                }
               
                KiloValidator kilo = new KiloValidator();
                kilo.setMessage("veullez  saisir un poid correct");
                this.poidRegime.getValidators().add(kilo);                
                this.imcIdealRegime.setText(Double.toString(info.calculPoidIdeal(info))+" Kg");
                ArrayList<String> list = new ArrayList<>();
                list.add("2 Semaine");list.add("1 mois");list.add("2 mois");list.add("3 mois");list.add("4 mois");list.add("5 mois");list.add("6 mois");
                list.add("1 ans");list.add("le temps que sa prendra");
                this.dureeRegime.getItems().addAll(list);
                ArrayList<String> typeregime = new ArrayList<>();
                typeregime.add("le jeune");typeregime.add("la micronutrition ou chronoregime");typeregime.add("le regime dissocié");
                typeregime.add("le regime hyperprotéiné");typeregime.add("le regime hypocalorique ou hypoglucidique");
                this.regimeEffectue.getItems().addAll(typeregime);            
                this.view = new WebView();                
                this.pane.getChildren().add(view);
                heure = 0;
                minute = 0;
                this.ptitDej = new JFXListView<>();
                this.dejeuner = new JFXListView<>();
                this.diner = new JFXListView<>();
                this.gouter = new JFXListView<>();
                this.regimeDujour = new JFXListView<>();
                this.calorieMin.setDisable(true);
               // this.tabRegime.setStyle("-fx-tab-max-height:0");
    } 
    public void imc()
    {
        System.out.println("okay clicked");
    }
    public AnchorPane getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(AnchorPane pourcentage) {
        this.pourcentage = pourcentage;
    }

    @FXML
    private void calculIMC(MouseEvent event) 
    {
       
        FXMLAjoutIMCViewController cont = new FXMLAjoutIMCViewController();
        System.out.println("PATIENT"+patient.getLogin());
        if(patient.getLogin().equals(" "))
        {
            patient = new Patient(FXMLLoginController.pseudo,"","","");
            System.out.println(" patient:"+patient.getLogin());
        }
        FXMLAjoutIMCViewController.setPatient(patient);
        FillProgressIndicator fp= (FillProgressIndicator)box.getChildren().get(0);
        
         GestionInfoSante ginfo = new GestionInfoSante();
        try {
            cont.startIMCAJOUT();
            Timer time2 = new Timer();
                time2.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() 
                {
                  if(FXMLAjoutIMCViewController.IMC > 0)
                  {
                    
                      Platform.runLater(() -> {
                          animationCercle(fp,FXMLAjoutIMCViewController.IMC);
                          
                          System.out.println("okk imc:"+FXMLAjoutIMCViewController.IMC);
                         
                          time2.cancel();
                          time2.purge();
                          FXMLAjoutIMCViewController.IMC =0;
                          tabRegime.getSelectionModel().select(0);
                      });
                                     
                  }
                }
            }, 3600, 200);
               
       } catch (Exception ex) {
            Logger.getLogger(FXMLImcController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    public void animationCercle(FillProgressIndicator progress,Double IMC)
    {
        Timer time2 = new Timer(true);
        GestionInfoSante ginfo = new GestionInfoSante();
          if(patient.getLogin()==null)
          {
            patient = new Patient(FXMLLoginController.pseudo,"","","");
          }
         InfoSante info = ginfo.afficherInfoSante(patient.getLogin()); 
        time2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                 if(progress.getProgress() < IMC)
                 {
                     Platform.runLater(() -> {
                         String s= IMC.toString();
                         String p=s.substring(s.indexOf('.')+1,s.length());//recuperation de la partie decimal
                         System.out.println("ch"+p);
                         String ch ="1";
                         ch = ch.concat("."+p);
                         double decimal=Double.parseDouble(ch);
                         double occ=IMC-decimal;                      
                         occ=IMC-occ;
                         occ+=progress.getProgress();
                        
                         System.out.println("oklm: "+occ);
                         progress.setProgress(Math.round(occ*100.0)/100.0);
                         
                     });
                    
                 } 
                 else
                 {
                     Platform.runLater(() -> {
                         interpretIMC.setTextFill(Color.CORAL);
                         interpretIMC.setText(info.interpreterIMC(FXMLAjoutIMCViewController.IMC));
                         imcRegime.setText(Double.toString(progress.getProgress()));
                         poidRegime.setText(Double.toString(info.getPoids()));
                         imcIdealRegime.setText(Double.toString(info.calculPoidIdeal(info))+" Kg");
                         PoidsAPerdre.setText(Double.toString(Math.abs(info.getPoids()-info.calculPoidIdeal(info))));
                         time2.cancel();
                         time2.purge();
                     });
                 }
            }
        }, 1,100);
    }
    @FXML
    public void validerPoids()
    {
        if (this.poidRegime.validate()) {
            Color c = Color.web("#008000"); //green
            poidRegime.validate();
            poidRegime.focusColorProperty().setValue(c);
            poidRegime.getValidators().get(0).setMessage("");
        } else {
            Color c = Color.web("#FF0000"); //red
            poidRegime.validate();
            poidRegime.focusColorProperty().setValue(c);
            poidRegime.getValidators().get(0).setMessage("veullez  saisir un poid correct");
        }
    }
    @FXML
    public void ajouterAllergies()
    {
       String ch = allergiesSupp.getText();
       if(listeAllergieSup.getItems().contains(buffer))
       {
            listeAllergieSup.getItems().remove(buffer);
            if((!ch.equals("")) && (listeAllergieSup.getItems().contains(ch)==false))
           {
            listeAllergieSup.getItems().add(ch);   
            allergiesSupp.clear();
           }   
       }
       else
       {
          if((!ch.equals("")) && (listeAllergieSup.getItems().contains(ch)==false))
         {
            listeAllergieSup.getItems().add(ch);   
            allergiesSupp.clear();
         }   
       }
      
       
       
    }
    @FXML
    public void modifierAllergies()
    {
        buffer = listeAllergieSup.getSelectionModel().getSelectedItem();
        allergiesSupp.setText(buffer);
        
    }
    @FXML
    public void supprimerAllergies()
    {
       String ch = allergiesSupp.getText();
        if(ch.equals(buffer))
        {
           if((!ch.equals("")) && (listeAllergieSup.getItems().contains(ch)==true))
           {
            listeAllergieSup.getItems().remove(ch);
            allergiesSupp.clear();
            buffer="";
           }   
            
        }
        else
        {
           if((!ch.equals("")) && (listeAllergieSup.getItems().contains(ch)==true))
           {
            listeAllergieSup.getItems().remove(ch);   
            allergiesSupp.clear();           
           }   
        }
    }
    //--------------------------------------------------gestion maladie-------------
    @FXML
    public void ajouterMaladies()
    {
        String ch = maladies.getText();
       if(listMaladies.getItems().contains(bufferMaladies))
       {
            listMaladies.getItems().remove(bufferMaladies);
            if((!ch.equals("")) && (listMaladies.getItems().contains(ch)==false))
           {
            listMaladies.getItems().add(ch);   
            maladies.clear();
           }   
       }
       else
       {
          if((!ch.equals("")) && (listMaladies.getItems().contains(ch)==false))
         {
            listMaladies.getItems().add(ch);   
            maladies.clear();
         }   
       }

    }
    @FXML
    public void modifierMaladies()
    {
       bufferMaladies = listMaladies.getSelectionModel().getSelectedItem();
       maladies.setText(bufferMaladies);  
        
    }
    @FXML
    public void supprimerMaladies()
    {
      String ch = maladies.getText();
        if(ch.equals(bufferMaladies))
        {
           if((!ch.equals("")) && (listMaladies.getItems().contains(ch)==true))
           {
            listMaladies.getItems().remove(ch);
            maladies.clear();
            bufferMaladies="";
           }   
            
        }
        else
        {
           if((!ch.equals("")) && (listeAllergieSup.getItems().contains(ch)==true))
           {
            listMaladies.getItems().remove(ch);   
            maladies.clear();           
           }   
        }   
    }
    @FXML
    public void etape2suivieRegime()
    {
        GestionRegime gregime = new GestionRegime();
        List<Regime> reg = new ArrayList<>();
        reg = gregime.afficherRegime();
        System.out.println(reg);
        for(int i = 0 ; i < reg.size(); i++)
        {
            System.out.println("nom reg:"+reg.get(i));
            if(regimePropose.getItems().contains(reg.get(i).getId_regime())==false)
            {
               regimePropose.getItems().add(reg.get(i).getId_regime());   
            }
         
        }
      
        tabRegime.getSelectionModel().select(1);                     
    }
    @FXML
    public void afficherInfoRegime()
    {
        List<String>allergiesElement = new ArrayList<>();
        List<String>maladiesL = new ArrayList<>();
        Regime regime = new Regime();
        regime.setId_regime(this.regimePropose.getValue());
        GestionRegime greg = new GestionRegime();
        regime = greg.rechercherRegime(regime);
      
        this.infoRegime.setText(regime.splitDescription(regime.getDescription()));
        List<Regime> regimes = greg.afficherRegime();
        List<String> aller = new ArrayList<>();
        for(int i = 0; i < this.allergies.getChildren().size(); i++)
        {
            JFXCheckBox check =(JFXCheckBox) this.allergies.getChildren().get(i);
            if(check.isSelected())
            {
                System.out.println("gfg"+check.getText());
                aller.add(check.getText());
            }
           
        }
       
       
        
        allergiesElement.addAll(this.listeAllergieSup.getItems());
        
        maladiesL.addAll(this.listMaladies.getItems());
        System.out.println("index:"+regimes.indexOf(regime));
        aller.addAll(allergiesElement);         
        System.out.println(proposerRegime(aller,maladiesL, regimes));       
        regime = regimes.get(regimes.indexOf(regime));
        for (Aliment aliment : regime.getAliments()) 
        {
            if(this.listeAliments.getItems().contains(aliment.getNom_aliment())==false)
            {
                this.listeAliments.getItems().add(aliment.getNom_aliment());
            }
        }
        regimeSport();
    }
  
    public void chargerSport()
    {
        Regime regime = new Regime();
         if(this.regimePropose.getValue()!=null)
         {
          regime.setId_regime(this.regimePropose.getValue());
          System.out.println("prop"+this.regimePropose.getValue());
          GestionRegime gregime = new GestionRegime();
          List<Regime> regimes = gregime.afficherRegime();
          regime = regimes.get(regimes.indexOf(regime));
          System.out.println("rr:" +regime);
          this.lesSports.getChildren().clear();
          for(Sport i : regime.getSports())
          {
            JFXCheckBox ch1 = new JFXCheckBox(i.getNom_sport()); 
            this.lesSports.getChildren().add(ch1);
          }
         }    
    }
    
    @FXML
    public void regimeSport()
    {
      if(this.siSport.isSelected()==true)
      {
          this.lesSports.setDisable(false);
          this.chargerSport();
      }
      else
      {
         this.lesSports.setDisable(true);       
      } 
    }
    @FXML
    public  void validerRegime()
    {
        System.out.println("jb:"+this.dureeRegime.getValue());
        Regime regime = new Regime();
        GestionRegime gestion = new GestionRegime();
        if(this.dureeRegime.getValue().equals("le temps que sa prendra")==true)
        {
            regime.setDuree(-1);
        }
        else
        {
            if(this.dureeRegime.getValue().equals("2 Semaine")==true)
            {
                try
                {
                    int duree = Integer.parseInt(this.dureeRegime.getValue().substring(0,1));
                    regime.setDuree(duree);
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("exection:"+ex.getMessage());
                    System.out.println(this.dureeRegime.getValue().substring(0,1));
                }                                                       
            }
            else if(this.dureeRegime!=null)
            {
                try
                {
                     int duree = Integer.parseInt(this.dureeRegime.getValue().substring(0,1));
                     regime.setDuree(duree*4);
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("exection:"+ex.getMessage());
                    System.out.println(this.dureeRegime.getValue().substring(0,1));
                }                                
            }
        }
        regime.setId_regime(this.regimePropose.getValue());
       if(this.siSport.isSelected())
       {
         List<Sport>listSport = new ArrayList<>();
         for(int i = 0; i < this.lesSports.getChildren().size(); i++)
         {
            JFXCheckBox sport =(JFXCheckBox) this.lesSports.getChildren().get(i);
            Sport sp = new Sport();
            if(i==0)
            {
                sp.setNom_sport(sport.getText());
            }
            else
            {
                 sp.setNom_sport("|"+sport.getText());
            }
            listSport.add(sp);
         }
        regime.setSports(listSport);
        Date date = new Date();
        regime.setDate_debut(date);
        gestion.suivreRegime(regime, patient);
        Notifications.create().title("BIENVENUE DANS LE PROGRAMME REGIME").show();
        regimeDuJour();
       }
    }
    public List<Regime> proposerRegime(List<String>AllergiesAliments,List<String>Maladies,List<Regime>regimes)
    {
        //toutes personne avec une maladies superieur a 2 ne sont pas traité uniquement le cas d'un diabetique est traité
        //tout les aliments dont la personne est allergique est filtré et sont enlevé de sa liste
        ProgrammeRegime prog = new ProgrammeRegime();
        List<Regime> tabRegime = new ArrayList<>();
        List<String> ltypeAliment = new ArrayList<>();
        for(Type_Aliment type: Type_Aliment.values())
        {
            ltypeAliment.add(type.toString());
        }
        for(int i = 0; i < regimes.size(); i++)
        {
           Regime r = regimes.get(i);
            Map<Type_Aliment,List<Aliment>>aliments = prog.grouperParAliment(regimes.get(i));
            for(String allergie: AllergiesAliments)
            {
               if(ltypeAliment.contains(allergie))
               {
                if(aliments.containsKey(Type_Aliment.valueOf(allergie)))
                {
                    aliments.remove(Type_Aliment.valueOf(allergie));
                }
               }
            }
           List<Aliment>aliment = new ArrayList<>();
           for(List<Aliment> j:aliments.values())
           {
               aliment.addAll(j);
           }
          r.setAliments(aliment);
          tabRegime.add(r);
        }
        return tabRegime;
    }
    @FXML
    public void regimeDuJour()
    {
        bienetre.getSelectionModel().select(1);   
        GestionRegime greg = new GestionRegime();
        GestionUserRegime guser = new GestionUserRegime();
        GestionInfoSante g = new GestionInfoSante();
        List<Regime> regimes = greg.afficherRegime();
        if(patient.getLogin()==null)
        {
            patient.setLogin(FXMLLoginController.pseudo);
        }        
        ProgrammeRegime prog = guser.rechercherUserRegime(patient);
        Regime regime = new Regime();
        regime.setId_regime(prog.getNomRegime());
        InfoSante info =g.afficherInfoSante(patient.getLogin());
        System.out.println("info:"+info);
      if(info.getAge()>0  && info.getTaille()>0d) 
      {
       if(regimes!=null)
       {
        if(regimes.contains(regime))
        {
         regime = regimes.get(regimes.indexOf(regime));
        }
        System.out.println("prog est:"+prog);
        switch(prog.getNomRegime())
        {
            case "regime dissocie":
                                   tabRegime.getSelectionModel().select(2);
                                   regimeDissocie(regime, prog, guser);
                                   break;
            case "micronutrition":
                                   tabRegime.getSelectionModel().select(3);
                                   regimeMicronutrition(regime, prog, guser);
                                   break;
            case "regime jeune":
                                   tabRegime.getSelectionModel().select(4);
                                   regimeJeune();
                                   break;
            case "hyper proteine":
                                   tabRegime.getSelectionModel().select(5);
                                   regimeHyperProteine(regime,prog,guser);
                                   break;
            
            case "regime calorique":
                                   tabRegime.getSelectionModel().select(6);
                                   hypoCalorique(regime,prog,guser);
                                   break;
            default:
                                   tabRegime.getSelectionModel().select(0);                                                          
                                   break;
             
        }
      }
      }
      else 
      {
         error.getChildren().clear();
          tabRegime.getSelectionModel().select(8);
          Label label = new Label("Pour suivre le Regime Vous devez tout dabord commencer par calculer votre IMC");
          JFXButton button  = new JFXButton("calculerIMC");
         button.setOnMouseClicked((MouseEvent e) -> {
             calculIMC(e);
          });
         error.getChildren().add(label); error.getChildren().add(button);
                 
      }
       if(prog.getSport()!=null)
       {
        for(String sport: prog.getSport())
        {
           if(sportUser.getItems().contains(sport)==false)
           {
               sportUser.getItems().add(sport);
           }
        }
       }
    }
    
    public void nePlusSuivreRegime()
    {
       
       GestionUserRegime guser = new GestionUserRegime();
       ProgrammeRegime prog = guser.rechercherUserRegime(patient);
     
       if(prog!=null)
       {
           guser.supprimerProgrammeRegime(patient, prog);
          bienetre.getSelectionModel().select(0);
          this.listeAllergieSup.getItems().clear();
          this.listMaladies.getItems().clear();
          this.dureeRegime.setValue("");
          this.regimeEffectue.setValue("");
          this.bienetre.getSelectionModel().select(0);
       }
     
    }
    @FXML
    public void chargerVideo()
    {
        //Exercices Biceps |Exercices des Abdominaux
        HashMap<String,String>tabVideo = new HashMap<>();
        tabVideo.put("Exercices Biceps".trim(), "exerciceBiceps.html");
        tabVideo.put(" Exercices des Abdominaux".trim(),"exerciceAbdominaux.html");
        String nomSport = this.sportUser.getValue().trim();
        System.out.println(tabVideo);
        for(Map.Entry i : tabVideo.entrySet())
        {
               String sansSpace = i.getKey().toString().trim();
                if(sansSpace.equals(nomSport))
                {
                    this.pane.getChildren().clear();
                    this.view.getEngine().load(getClass().getResource(tabVideo.get(sansSpace)).toExternalForm());
                    this.pane.getChildren().add(view);  
                }           
        }                
    }
  public void regimeDissocie(Regime regime,ProgrammeRegime prog,GestionUserRegime guser)
  {    
        Date dateAujdh = new Date();
        DateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
        if(prog.getAlimentJour().isEmpty() || prog.getDateJour().toString().compareTo(fo.format(dateAujdh))!=0) //s'il sagit du premier jour  du suivuie de regime
        {
            Map<Integer,List<Aliment>> proposition = prog.regimeDissocie(regime);
            System.out.println("prop:"+proposition);
            List<Aliment> aliments = new ArrayList<>();

            Aliment a = new Aliment();
            for(int n: proposition.keySet())
            {
                aliments = proposition.get(n);
            }
          
            System.out.println("aliment:"+aliments);
            for(Aliment i: aliments)
            {
               if(regimeDujour.getItems().contains(i.getNom_aliment())==false)
               {
                regimeDujour.getItems().add(i.getNom_aliment());
               }
            }
            prog.setAlimentJour(a.totalNomAliment(aliments));
            prog.setDateJour(new Date());
            
            guser.modifierProgrammeRegime(patient, prog);
        }
        else
        {
            regimeDujour.getItems().clear();
             for(String i: prog.getAlimentJour())
            {        
                regimeDujour.getItems().add(i);            
            }
        }
   }
  public void regimeMicronutrition(Regime regime,ProgrammeRegime prog,GestionUserRegime guser)
  {
      
           System.out.println("regime:"+regime);
           Map<String,List<Aliment>>map = prog.regimeMicronutrition(regime);
           System.out.println("map:"+map);
           for(Map.Entry<String,List<Aliment>> i: map.entrySet())
           {
               System.out.println("key:"+i.getKey());
               if(i.getKey().equals("petit dejeuner"))
               {
                  for(Aliment aliment: map.get("petit dejeuner"))
                  {
                      System.out.println("ali:"+aliment.getNom_aliment());                      
                   if(ptitdej_micro.getItems().contains(aliment.getNom_aliment())==false)
                   {
                    ptitdej_micro.getItems().add(aliment.getNom_aliment());
                       System.out.println("PTIT DEJ");
                   }
                  }   
               }
               else if(i.getKey().equals("dejeuner"))
               {
                  for(Aliment aliment: map.get("dejeuner"))
                  {
                        System.out.println("alidej"+aliment.getNom_aliment());
                   if(dej_micro.getItems().contains(aliment.getNom_aliment())==false)
                   {
                      dej_micro.getItems().add(aliment.getNom_aliment());
                       System.out.println("DEJ");
                   }
                  }   
               }
               else if(i.getKey().equals("gouter"))
               {
                   for(Aliment aliment: map.get("gouter"))
                   {
                         System.out.println("aligouter:"+aliment.getNom_aliment());
                   if(gouter_micro.getItems().contains(aliment.getNom_aliment())==false)
                   {
                      gouter_micro.getItems().add(aliment.getNom_aliment());
                      System.out.println("Gouter");
                   }
                  }   
               }
               else if(i.getKey().equals("diner"))
               {
                  for(Aliment aliment: map.get("diner"))
                  {
                        System.out.println("aliDiner:"+aliment.getNom_aliment());
                   if(diner_micro.getItems().contains(aliment.getNom_aliment())==false)
                   {
                      diner_micro.getItems().add(aliment.getNom_aliment());
                   }
                  }   
               }
           }
       
  }
  public void regimeHyperProteine(Regime regime,ProgrammeRegime prog,GestionUserRegime guser)
  {
           System.out.println("regime:"+regime);
           Map<String,List<Aliment>>map = prog.regimeHyperProteine(regime);
           System.out.println("map:"+map);
           for(Map.Entry<String,List<Aliment>> i: map.entrySet())
           {
               System.out.println("key:"+i.getKey());             
               for(Aliment aliment: map.get("proteine"))
               {
                 System.out.println("ali:"+aliment.getNom_aliment());                      
                 if(programmeProteine.getItems().contains(aliment.getNom_aliment())==false)
                 {
                    programmeProteine.getItems().add(aliment.getNom_aliment());
                 }
               }              
           }
  }
  public void hypoCalorique(Regime regime,ProgrammeRegime prog,GestionUserRegime guser)
  {     
           System.out.println("regime:"+regime);
           Map<String,List<Aliment>>map = prog.regimeHypoCalorique(regime);
           System.out.println("map:"+map);
           for(Map.Entry<String,List<Aliment>> i: map.entrySet())
           {
               System.out.println("key:"+i.getKey());             
               for(Aliment aliment: map.get("calorie"))
               {
                 System.out.println("ali:"+aliment.getNom_aliment());                      
                 if(programmeCalorie.getItems().contains(aliment.getNom_aliment())==false)
                 {
                    programmeCalorie.getItems().add(aliment.getNom_aliment());
                 }
               }              
           }
  }
  public void precRegime2()
  {
      tabRegime.getSelectionModel().select(0);
  }
  public void regimeJeune()
  {
      
  }
    @FXML
   public void startCompteur()
   {
       Timer timer = new Timer(true);
   
       timer.scheduleAtFixedRate(new TimerTask() {
           @Override
           public void run() 
           {
               Platform.runLater(()->{                                 
                                     
                                       String chheure="",chminute="";
                                        if(heure < 10)
                                        {
                                                chheure="0"+Integer.toString(heure);
                                        }
                                        else
                                        {
                                                chheure=Integer.toString(heure);
                                        }
                                        if(minute < 10)
                                        {
                                           chminute="0"+Integer.toString(minute); 
                                        }
                                        else
                                        {
                                           chminute=Integer.toString(minute);
                                        }
                                        
                                       if(heure+1 < 16)
                                       {                                    
                                          if(minute+1==60)
                                          {
                                            minute=0;
                                            heure+=1;                                          
                                            if(heure < 10)
                                            {
                                                chheure="0"+Integer.toString(heure);
                                            }
                                            else
                                            {
                                                chheure=Integer.toString(heure);
                                            }                                       
                                          }
                                          else
                                          {
                                            minute+=1;
                                            if(heure==0)
                                            {
                                                chheure="00";
                                            }
                                            if(minute < 10)
                                            {
                                               chminute="0"+Integer.toString(minute); 
                                            }
                                            else
                                            {
                                                chminute=Integer.toString(minute);
                                            }
                                          }
                                           heureRegimeJeune.setText(chheure+"h"+":"+chminute+"mn"); 
                  
                                        }
                                       else if(heure==17)//annuler compteur
                                       {
                                          heureRegimeJeune.setText("00h:00mn"); 
                                          heure = 0;
                                          minute = 0;
                                          timer.cancel();
                                          timer.purge();
                                       }
                                       else //terminer compteur
                                       {
                                          
                                         minute=0;
                                          heure=0;
                                           Notifications.create().title("Jeune terminier").text("Votre heure de jeuner est terminier il est temps de "
                                                   + "reprendre des forces").showConfirm();
                                           heureRegimeJeune.setText("00h:00mn");
                                          timer.cancel();
                                          timer.purge();                                  
                                       }
               });
              
           }
       }, 25, 50);
   }
    @FXML
   public void annullerCompteur()
   {
       heure = 17;
   }
    @FXML
   public void chercherAliment()
   {
      
      if(rechercheManger.getText().length()==1)
      {
          rechercheManger.setPromptText("plus que deux caractere a taper pour commencer la recherche");
      }
      else if(rechercheManger.getText().length()==2)
      {
          rechercheManger.setPromptText("plus qu'un caractere a taper pour commencer la recherche");   
      }
      else if(rechercheManger.getText().length()>=3)
      {
         BasicConfigurator.configure();
       
       NutritionixClientBuilder client = new NutritionixClientBuilder();
       NutritionixClientImpl impl = new NutritionixClientImpl("073b261f","b9bfaaaff31fc9b51481c649d2958a6f","https://api.nutritionix.com/v1_1",2);
       client.build();
       SearchResults result=  impl.search(rechercheManger.getText(),"",1000,0,"*","item_name,brand_name,item_id,nf_calories");  
       resultatChearch.getItems().clear();
     if(result.getTotal_hits() > 0)
     {
       List<MenuItem>items=new ArrayList<>();
       for(SearchItem i : result.getHits())
       {
           MenuItem item = new MenuItem();
           SearchItem.Fields f = i.getFields();
           System.out.println("id: "+f.getBrandName()+" "+f.getItemName()+" calo:"+f.getAdditionalProperties().get("nf_calories").toString());
           item.setText(f.getItemName()+" calorie:"+f.getAdditionalProperties().get("nf_calories"));
           items.add(item);
           item.setOnAction(new EventHandler<ActionEvent>() {
               @Override
               public void handle(ActionEvent event) {
                   calorieC.getItems().add(item.getText());
                   rechercheManger.clear();
               }
           });
           
       }
       resultatChearch.getItems().addAll(items);
       resultatChearch.show(rechercheManger,Side.BOTTOM,0,0);
       impl.shutdown();
     }
    }  
      
   }
    @FXML
   public void ajouterListe()
   {
      /* System.out.println("pass"+itemMenu.getText());
       calorieC.getItems().add(itemMenu.getText());*/
        //calorie;
   }
    @FXML
   public void calculerCalorie()
   {
       double total = 0d;
       Regime r = new Regime();
       for(int i = 0; i < calorieC.getItems().size(); i++)
       {
           total+=r.recuperCalorie(calorieC.getItems().get(i));
       }
       totalCalorie.setText(total+"cal ");
       System.out.println("total est:"+total);
   }
    @FXML
   public void afficherCalculCalorie()
   {
       bienetre.getSelectionModel().select(1);
        tabRegime.getSelectionModel().select(7);
         GestionInfoSante g = new GestionInfoSante();
         if(patient.getLogin()==null)
         {
            patient.setLogin(FXMLLoginController.pseudo);         
         }
       
         InfoSante info =g.afficherInfoSante(patient.getLogin());
         if(info.calculCalorieMin(info) > 0)
         {
             calorieMin.setDisable(false);
               String ch="Vous avez besoins au moins de "+Double.toString(info.calculCalorieMin(info))+" cal\n"+
                      "pour faire fonctionner correctement votre organisme"+"\n"+
                      "si vous consommé moins vos organes vitaux ne fonctionne pas bien";
         calorieMin.setText(ch);
        
         }
          
                 
   }
}
