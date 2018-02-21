/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.affichermedecin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLAfficherMedecinController implements Initializable {
  private Pagination pagination;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    pagination = new Pagination(28, 0);
    pagination.setStyle("-fx-border-color:red;");
    pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));

    AnchorPane anchor = new AnchorPane();
    AnchorPane.setTopAnchor(pagination, 10.0);
    AnchorPane.setRightAnchor(pagination, 10.0);
    AnchorPane.setBottomAnchor(pagination, 10.0);
    AnchorPane.setLeftAnchor(pagination, 10.0);
    anchor.getChildren().addAll(pagination);      
        
    }    
    public int itemsPerPage() {
    return 8;
  }

  public VBox createPage(int pageIndex) {
    VBox box = new VBox(5);
    int page = pageIndex * itemsPerPage();
    for (int i = page; i < page + itemsPerPage(); i++) {
      VBox element = new VBox();
      Hyperlink link = new Hyperlink("Item " + (i + 1));
      link.setVisited(true);
      Label text = new Label("Search results\nfor " + link.getText());
      element.getChildren().addAll(link, text);
      box.getChildren().add(element);
    }
    return box;
  }
    
    
    
}
