/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.analysesymptome;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 *
 * @author Firassov
 */
public class Tag extends Label{

		public Tag(Object arg0) {
			super(arg0.toString());
			initTag();
		}

		private final void initTag(){
			Path path = new Path();
			/**
			 * you will need to increase the 5 if you want the close button to be big
			 */
			path.getElements().addAll(
					new MoveTo(0,0),new LineTo(10, 10),
					new MoveTo(10,0), new LineTo(0,10));
			path.setStyle("-fx-stroke: white;");
			/*path.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent paramT) {
					Node n = Tag.this.getParent();
					if(n instanceof Pane){//of course it is
						((Pane)n).getChildren().remove(Tag.this);
					}
				}
			});
                        this.setOnMouseClicked(new EventHandler<MouseEvent>(){
                            @Override
				public void handle(MouseEvent paramT) {
					Node n = Tag.this.getParent();
					if(n instanceof Pane){//of course it is
						((Pane)n).getChildren().remove(Tag.this);
					}
				}
                        });*/
			setPadding(new Insets(3,5,3,5));
			setGraphic(path);
			setContentDisplay(ContentDisplay.RIGHT);
			setGraphicTextGap(8);
			graphicProperty().addListener(new ChangeListener<Node>() {
				@Override
				public void changed(ObservableValue<? extends Node> paramObservableValue,
						Node paramT1, Node paramT2) {
					if(paramT2 != path){
						setGraphic(path);
					}
				}
			});

			setStyle("-fx-background-color: silver; -fx-border-radius: 3; -fx-border-color: black;");
		}

	}
