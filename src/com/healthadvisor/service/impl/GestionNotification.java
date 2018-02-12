/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.entities.Notification;
import com.heathadvisor.service.IGestionNotification;
import java.util.List;

/**
 *
 * @author khattout
 */
public class GestionNotification implements IGestionNotification{

    @Override
    public Notification AjouterNotification(Notification notif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Notification ModifierNotification(Notification notif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notification> ListNotification(String cin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notification> marquerCommeLus(int id, String cin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Notification> marquerTousCommeLus(int id, String cin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
