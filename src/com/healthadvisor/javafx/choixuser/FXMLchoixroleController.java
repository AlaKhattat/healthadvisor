/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.choixuser;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLchoixroleController implements Initializable {

    @FXML
    private Circle patient;
    @FXML
    private Circle medecin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void patientHover(MouseEvent event) {
        DropShadow ds=new DropShadow();
        ds.setWidth(21.0);
        ds.setHeight(21.0);
        ds.setColor(Color.WHITE);
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        patient.setStroke(Color.WHITE);
        patient.setEffect(ds);
    }

    @FXML
    private void medecinHover(MouseEvent event) {
         DropShadow ds=new DropShadow();
        ds.setWidth(21.0);
        ds.setHeight(21.0);
        ds.setColor(Color.WHITE);
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        medecin.setStroke(Color.WHITE);
        medecin.setEffect(ds);
    }

    @FXML
    private void patientExit(MouseEvent event) {
         DropShadow ds=new DropShadow();
        ds.setWidth(21.0);
        ds.setHeight(21.0);
        ds.setColor(Color.BLACK);
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        patient.setStroke(Color.BLACK);
        patient.setEffect(ds);
    }

    @FXML
    private void medecinExit(MouseEvent event) {
         DropShadow ds=new DropShadow();
        ds.setWidth(21.0);
        ds.setHeight(21.0);
        ds.setColor(Color.BLACK);
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        medecin.setStroke(Color.BLACK);
        medecin.setEffect(ds);
    }
    
}
