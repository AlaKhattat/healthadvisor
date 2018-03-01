/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.modifier_rdv;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 *
 * @author Firassov
 */
public class AlertMessage {
    private GridPane alertMessageContainer;
    private Label alertMessageLabel;
    private Label alertHelpMessageLabel;
    private Label alertIcon;
    private Button closePopupButton;
    private Timeline timerStoppedTimeline;
    private Timeline showAlertTimeline;
    private Timeline hideAlertTimeline;
    private Timeline showPopupTimeline;
    private Timeline hidePopupTimeline;
    Boolean popupState = false;

    public AlertMessage() {
    }
    
    public void showPopup(String message, String helpText, int alertType,BorderPane gamePlayContainer) {
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

        gamePlayContainer.setBottom(alertMessageContainer);
        gamePlayContainer.setAlignment(alertMessageContainer, Pos.CENTER_LEFT);


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
        alertHelpMessageLabel.setWrapText(true);
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
        KeyFrame clear = new KeyFrame(Duration.millis(201), e -> gamePlayContainer.setBottom(null));

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

}
