
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Evenement_Participants;
import com.heathadvisor.service.IGestionEvenementParticipants;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GestionEvenementParticipants implements IGestionEvenementParticipants{
    
    public MyDB db;

    public GestionEvenementParticipants() {
        
        db=MyDB.getInstance();
        
    }
    
    public void ajouterEvenementParticipants(Evenement_Participants p){
         
        try {
            PreparedStatement stm=db.getConnexion().prepareStatement("insert into evennement_participants(id_event, id_user) values("+p.getIdEvent()+",'"+p.getpLog()+"')");
            //stm.setInt(1,p.getIdEvent());
            //stm.setString(2, p.getpLog());
            stm.executeUpdate();
            System.out.println("Ajout effectué ! ");
        } catch (SQLException err) {
            System.out.println("Erreur ajout : "+err.getSQLState()+" msg:"+err.getMessage());
        }
    }
    
    public void supprimerEvenementParticipants(Evenement_Participants p) {
        
        try
        {
         PreparedStatement stm = db.getConnexion().prepareStatement("delete from evennement_participants where id_event= ? and id_user=?"); 
         stm.setInt(1,p.getIdEvent());
         stm.setString(2, p.getpLog());
         stm.executeUpdate();
            System.out.println("Suppression effectue ! ");
        }
        catch(SQLException err)
        {
            System.out.println("Erreur suppression : "+err.getSQLState());
        }
        
    }
    
    public List<Evenement_Participants> afficherEvenementParticipants()
    {
        List<Evenement_Participants> listEP = new ArrayList<>();

      try
      {
          String sql = "select * from evennement_participants";
          Statement stm = db.getConnexion().createStatement();
          ResultSet res = stm.executeQuery(sql);
          while(res.next())
          {
              Evenement_Participants p=new Evenement_Participants();
              p.setIdEvent(res.getInt(1));
              p.setpLog(res.getString(2));
              listEP.add(p);
          }
          System.out.println("Affichage réussi ! ");
      }
      catch(SQLException err)
      {
          System.out.println("Erreur affichage : "+err.getSQLState());
      }
      return listEP;
    }
    
    public Evenement_Participants rechercheEvenementParticipants(int id, String s){
        
        Evenement_Participants ep = new Evenement_Participants();
        for (Evenement_Participants i : afficherEvenementParticipants()) {
            if (i.getIdEvent()==id || i.getpLog().equals(s)) {
                ep = i;
            }
        }
        return ep;
        
        
    }
    
    
    
}