/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Sondage;
import com.healthadvisor.service.impl.GestionQuestion;
import com.healthadvisor.service.impl.GestionSondage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Tarek
 */


public class SondageController implements Initializable {

    @FXML
    private TableView<Sondage> tableID;
    @FXML
    private TableColumn<Sondage, String> columnID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        GestionSondage gs = new GestionSondage();
        ObservableList<Sondage> lists = FXCollections.observableArrayList(gs.ListSondage());
           /* for (Sondage s : gs.ListSondage() ){
                lists.add(s);
            }*/
        columnID.setCellValueFactory(new PropertyValueFactory<Sondage,String>("nom"));
        tableID.setItems(lists);
    }    
    
}
