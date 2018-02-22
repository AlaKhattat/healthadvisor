/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.bienetre;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.DefaultProperty;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author asus
 */
@DefaultProperty(value = "icon")
public class KiloValidator extends ValidatorBase
{

    @Override
    protected void eval() {
         if(srcControl.get() instanceof TextInputControl)
      {
         evalTextInputField();
      }
         
    }
      private void evalTextInputField() {
        TextInputControl textField = (TextInputControl) srcControl.get();
        try {
            Double valeur =  Double.parseDouble(textField.getText());
            if((valeur < 0) ||( valeur >=1 && valeur <= 2) || (valeur > 560))
            {
                hasErrors.set(true);
            }
            else
            {
               hasErrors.set(false);  
            }
            
        } catch (Exception e) {
            hasErrors.set(true);
        }
    }
    
}
