/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLDrawerController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton doctorBtn;
    @FXML
    private JFXButton appointmentBtn;
    @FXML
    private JFXButton paymentBtn;
    @FXML
    private JFXButton exitBtn;
    @FXML
    private JFXButton exitBtn2;
    @FXML
    private JFXButton exitBtn21;
    @FXML
    private JFXButton exitBtn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exit(ActionEvent event) {
                Platform.exit();

    }
    
}
