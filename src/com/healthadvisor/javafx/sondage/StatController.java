/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.service.impl.GestionReponsesPossibles;
import com.healthadvisor.service.impl.GestionStatistiques;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class StatController implements Initializable {

    @FXML
    private AnchorPane paneID;
    @FXML
    private Button retourBtn;
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        //Stage stage = new Stage();
        //Scene scene = new Scene();
        /*stage.setTitle("Résultat sondage");
        stage.setWidth(500);
        stage.setHeight(500);*/
        
        
        
        GestionStatistiques gs = new GestionStatistiques();
        int nbrTotalReponses = gs.countReponsesSondage(SondageController.sondage.getId());
        //System.out.println(nbrTotalReponses);
        GestionReponsesPossibles grp=new GestionReponsesPossibles();
        ArrayList<Integer> lrp=new ArrayList<>();
        for(ReponsesPossibles rp :grp.ListReponsesPossibles(SondageController.sondage.getId())){
            lrp.add(rp.getId_reponse());
            
        }
        
        
            
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("★", gs.countStat(SondageController.sondage.getId(), lrp.get(0))),
                new PieChart.Data("★★", gs.countStat(SondageController.sondage.getId(), lrp.get(1))),
                new PieChart.Data("★★★", gs.countStat(SondageController.sondage.getId(), lrp.get(2))),
                new PieChart.Data("★★★★", gs.countStat(SondageController.sondage.getId(), lrp.get(3))),
                new PieChart.Data("★★★★★", gs.countStat(SondageController.sondage.getId(), lrp.get(4))));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle(SondageController.sondage.getNom()+"\n"+"Nombre total des réponses : "+nbrTotalReponses);
        
        
        
        /**/
        Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 roboto;");
        

        for (PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                
                caption.setTranslateX(e.getSceneX()-240);
                caption.setTranslateY(e.getSceneY()-110);
                caption.setText(String.format("%.1f",(data.getPieValue())/nbrTotalReponses*100) + "%");
             }
        });
}
                        
        
        
        
        /**/
        
        
        
        
        chart.setPrefWidth(600);
        chart.setPrefHeight(484);
        paneID.getChildren().add(chart);
        paneID.getChildren().add(caption);
        
        
        //scene.getRoot().getChildren().add(chart);
        /*stage.setScene(scene);
        stage.show();*/
    }

    @FXML
    private void RetourBtnAction(ActionEvent event) throws IOException {
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("SondageAdmin.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
                        
       
    }
}
        
        
 
    

