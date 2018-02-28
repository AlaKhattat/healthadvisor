package com.healthadvisor.javafx.boutique;

import com.healthadvisor.entities.Produit;
import com.healthadvisor.service.impl.ServiceCommande;
import com.healthadvisor.service.impl.ServiceLigne_Commande;
import com.healthadvisor.service.impl.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class FXMLStatistiques_AdminController implements Initializable {

    @FXML
    private AnchorPane pan_stats;
    @FXML
    private Button btn_gerer;
    @FXML
    private AnchorPane pan_pie;
    @FXML
    private AnchorPane pan_bar;
    @FXML
    private AnchorPane pan_area;

    ServiceProduit servP=new ServiceProduit();
    ServiceCommande servC=new ServiceCommande();
    ServiceLigne_Commande servLC=new ServiceLigne_Commande();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
       
         
        
          StatsProduits_Signales();
          StatsProduitsVendus_Type();
          Stats_ChiffresAffaires();
         
          //area chart
       /*  final NumberAxis xAxis = new NumberAxis(1, 31, 1);
        final NumberAxis yAxis = new NumberAxis();
        final AreaChart<Number,Number> ac = 
            new AreaChart<Number,Number>(xAxis,yAxis);
        ac.setTitle("Temperature Monitoring (in Degrees C)");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("April");
        seriesApril.getData().add(new XYChart.Data(1, 4));
        seriesApril.getData().add(new XYChart.Data(3, 10));
        seriesApril.getData().add(new XYChart.Data(6, 15));
        seriesApril.getData().add(new XYChart.Data(9, 8));
        seriesApril.getData().add(new XYChart.Data(12, 5));
        seriesApril.getData().add(new XYChart.Data(15, 18));
        seriesApril.getData().add(new XYChart.Data(18, 15));
        seriesApril.getData().add(new XYChart.Data(21, 13));
        seriesApril.getData().add(new XYChart.Data(24, 19));
        seriesApril.getData().add(new XYChart.Data(27, 21));
        seriesApril.getData().add(new XYChart.Data(30, 21));
        
        XYChart.Series seriesMay = new XYChart.Series();
        seriesMay.setName("May");
        seriesMay.getData().add(new XYChart.Data(1, 20));
        seriesMay.getData().add(new XYChart.Data(3, 15));
        seriesMay.getData().add(new XYChart.Data(6, 13));
        seriesMay.getData().add(new XYChart.Data(9, 12));
        seriesMay.getData().add(new XYChart.Data(12, 14));
        seriesMay.getData().add(new XYChart.Data(15, 18));
        seriesMay.getData().add(new XYChart.Data(18, 25));
        seriesMay.getData().add(new XYChart.Data(21, 25));
        seriesMay.getData().add(new XYChart.Data(24, 23));
        seriesMay.getData().add(new XYChart.Data(27, 26));
        seriesMay.getData().add(new XYChart.Data(31, 26));
        
        ac.getData().addAll(seriesApril, seriesMay);
        pan_stats.getChildren().add(ac);*/
    }    
    
    public void StatsProduits_Signales(){
        List<Produit> lst=servP.ListProduits_Signaler(0);
        List<Produit> lst1=servP.ListProduits_Signaler(1);
          
          ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(new PieChart.Data("produits Non Signaler",lst.size()),new PieChart.Data("produits signaler",lst1.size()));
          final PieChart chart = new PieChart(pieChartData);
          chart.setTitle("Produits");
          
          //chart.setLabelLineLength(10);
         // chart.setLegendSide(Side.LEFT);
     
          for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
            new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                Label caption = new Label("");
                caption.setTextFill(Color.BLACK);
                caption.setStyle("-fx-font: 24 arial;");
                System.err.println("hahahaha");
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue())+"%");
             }
        });
          }
          chart.setPrefWidth(pan_pie.getPrefWidth());
        chart.setPrefHeight(pan_pie.getPrefHeight());
        pan_pie.getChildren().add(chart);
    }
    
    //Bar chart des qte prdts vendus selon type
    public void StatsProduitsVendus_Type(){
         List<ArrayList> res=servLC.ListeProduits_PlusVendus();
    
 
        //stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
         NumberAxis yAxis = new NumberAxis(0,Integer.parseInt(res.get(0).get(1).toString()),Integer.parseInt(res.get(0).get(1).toString())/10);
        
        
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Produits les plus vendus");
        xAxis.setLabel("Produit");       
        yAxis.setLabel("Quantite");
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Quantite vendus");
        
         
         for(int i=0;i<res.size();i++){
             series1.getData().add(new XYChart.Data(res.get(i).get(0).toString(),Integer.parseInt(res.get(i).get(2).toString())));
             
         }
        
     
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Quantite non vendus");
        
        for(int j=0;j<res.size();j++){
             series2.getData().add(new XYChart.Data(res.get(j).get(0).toString(),Integer.parseInt(res.get(j).get(1).toString())-Integer.parseInt(res.get(j).get(2).toString())));
        }
        
        
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1,series2);
        bc.setPrefWidth(pan_bar.getPrefWidth());
        bc.setPrefHeight(pan_bar.getPrefHeight());
        pan_bar.getChildren().add(bc);
    }
    
    public void Stats_ChiffresAffaires(){
        Map<Integer,Float> lst=RecupererChiffresAffaires_Annee();
         
        
          //area chart
         NumberAxis xAxis = new NumberAxis(1, 12, 1);
         NumberAxis yAxis = new NumberAxis(0,max_chiffres(),max_chiffres()/10);
         AreaChart<Number,Number> ac = new AreaChart<Number,Number>(xAxis,yAxis);
        ac.setTitle("Variation du chiffre d'affaires pendant tout l'année");
 
        XYChart.Series seriesApril= new XYChart.Series();
        seriesApril.setName("April");
        for (Map.Entry<Integer,Float> i :lst.entrySet()) {
            seriesApril.getData().add(new XYChart.Data(i.getKey(), i.getValue()));
            
        }
        
        
        
        
       
        
        ac.getData().addAll(seriesApril);
        ac.setPrefWidth(pan_area.getPrefWidth());
        ac.setPrefHeight(pan_area.getPrefHeight());
        pan_area.getChildren().add(ac);
    }
    
    
    //chiffres d'affaires max
    public float max_chiffres(){
        float max=0;
        for(int i=0;i<servLC.ChiffresAffaires().size();i++){
              if(Float.parseFloat(servLC.ChiffresAffaires().get(i).get(1).toString())>max){
                  max=Float.parseFloat(servLC.ChiffresAffaires().get(i).get(1).toString());
              }
                      
                  }
        return max;
    }
    //fonction qui retourne les chiffres d'affaire de 1 a 12
    public Map<Integer,Float> RecupererChiffresAffaires_Annee()
    {   
        System.err.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        List<ArrayList> list=servLC.ChiffresAffaires();
        Map<Integer,Float> res=new TreeMap<>();
        for(int i=0;i<12;i++){
            for(int j=0;j<list.size();j++){
                if(Integer.parseInt(list.get(j).get(0).toString())==i){
                    float var=Float.parseFloat(list.get(j).get(1).toString());
                    System.err.println("je suis laaaaaaaaaaaaa  "+var);
                    if(var>0){
                        System.err.println("barhoum");
                        res.put(i, Float.parseFloat(list.get(j).get(1).toString()));
                    }
                    else{
                        System.err.println("lkouli");
                        res.put(i, 1.5f);
                    }
              
            }
                else{
                    System.err.println("lkouli");
                        res.put(i, 0f);
                }
            }
            
        }
        return res;
    }
    
    
    
    
    
    @FXML
    private void InterfaceGerer(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLGererProduit_Admin.fxml")); 
            Parent root=loader.load();
            Scene s = pan_stats.getScene(); 
            s.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAfficherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
