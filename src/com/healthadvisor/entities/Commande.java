
package com.healthadvisor.entities;

import java.util.Date;



public class Commande {

  private int num_commande;
  private Date date_commande;
  private String mode_payement;
  private String ID_client;

    public Commande() {
    }

    public Commande(int num_commande, Date date_commande, String mode_payement, String ID_client) {
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.mode_payement = mode_payement;
        this.ID_client = ID_client;
    }

    public int getNum_commande() {
        return num_commande;
    }

    public void setNum_commande(int num_commande) {
        this.num_commande = num_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public String getMode_payement() {
        return mode_payement;
    }

    public void setMode_payement(String mode_payement) {
        this.mode_payement = mode_payement;
    }

    public String getID_client() {
        return ID_client;
    }

    public void setID_client(String ID_client) {
        this.ID_client = ID_client;
    }

    @Override
    public String toString() {
        return "Commande{" + "num_commande=" + num_commande + ", date_commande=" + date_commande + ", mode_payement=" + mode_payement + ", ID_client=" + ID_client + '}';
    }
  
  
  
    
}


