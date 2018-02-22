/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.bienetre;

import com.healthadvisor.entities.Aliment;
import com.healthadvisor.entities.InfoSante;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Regime;
import com.healthadvisor.entities.Sport;
import com.healthadvisor.enumeration.Type_Aliment;
import com.healthadvisor.service.impl.GestionInfoSante;
import com.healthadvisor.service.impl.GestionRegime;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JComboBox;

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
    private VBox allergies;
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
    private Tab suivreRegime1;
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
    private Tab suivreRegime12;
    @FXML
    private StackPane pane;

    public Patient getPatient() {
        return patient;
    }

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
                patient= new Patient("user1","password1", "cin1");
                GestionInfoSante g = new GestionInfoSante();
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
                /*WebEngine engin = new WebEngine(getClass().getResource("/styles/videoYoutube.html").toExternalForm());*/               
                WebView vew = new WebView();                
                WebEngine engin = vew.getEngine();
                engin.load(getClass().getResource("videoYoutube.html").toExternalForm());
                this.pane.getChildren().add(vew);             
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
       // proposerRegime(this.allergies.getChildren(), Maladies, regimes)
        this.allergies.getChildren().forEach((Node ch) -> {
            HBox hbox = (HBox)ch;
            hbox.getChildren().forEach((b)->{
                JFXCheckBox check = (JFXCheckBox)b;
                allergiesElement.add(check.getText());
               allergiesElement.addAll(this.listeAllergieSup.getItems());
            });
        });
        maladiesL.addAll(this.listMaladies.getItems());
        System.out.println("index:"+regimes.indexOf(regime));
       // System.out.println(proposerRegime(allergiesElement, maladiesL, regimes));
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
            sp.setNom_sport(sport.getText());
            listSport.add(sp);
         }
        regime.setSports(listSport);
        Date date = new Date();
        regime.setDate_debut(date);
        gestion.suivreRegime(regime, patient);
       }
    }
    public List<Regime> proposerRegime(List<String>AllergiesAliments,List<String>Maladies,List<Regime>regimes)
    {
        //toutes personne avec une maladies superieur a 2 ne sont pas traité uniquement le cas d'un diabetique est traité
        //tout les aliments dont la personne est allergique est filtré et sont enlevé de sa liste 
        System.out.println("aller:"+AllergiesAliments+"reg:"+regimes);
        List<Regime>regime = new ArrayList<>();
        Boolean bo = false;
       for(int i = 0; i < regimes.size(); i++)
       {
           Regime r = regimes.get(i);
            System.out.println("gh");
            List<Aliment> tab = r.getAliments();
            System.out.println("size"+tab.size());
            for(int j = 0; j < tab.size(); j++)
            {
                List<Type_Aliment> tabTypeAliment = tab.get(j).getType_aliment();
              for(int l = 0; l < AllergiesAliments.size(); l++)
               {
                for(int k = 0; k < tabTypeAliment.size(); k++)
                {
                   
                        if(AllergiesAliments.get(l).equals(tabTypeAliment.get(k).toString())==true)
                        {
                            System.out.println("sa :"+AllergiesAliments.get(l)+"=="+tabTypeAliment.get(k).toString());               
                            System.out.println("tt:"+r.getAliments().remove(k));  
                            tabTypeAliment.remove(k);
                            bo = true;
                            System.out.println("TOTO");                                
                        }
                }
                                                                                                     
                }
            }
            if(bo == true)
            {
                regime.add(r);
            }
            
               
       }
        
    
        return regime;
    }
    
    
}
