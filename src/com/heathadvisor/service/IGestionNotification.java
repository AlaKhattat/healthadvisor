/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Notification;
import java.util.List;

/**
 *
 * @author khattout
 */
public interface IGestionNotification {
    public Notification AjouterNotification(Notification notif);
    public Notification ModifierNotification(Notification notif);
    public List<Notification> ListNotification(String cin);
    public List<Notification> marquerCommeLus(int id,String cin);
    public List<Notification> marquerTousCommeLus(int id,String cin); 

   
    
}
