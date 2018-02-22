
package com.healthadvisor.service.impl;


import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Commande;
import com.heathadvisor.service.InterfaceCommande;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommande implements InterfaceCommande  {
    
    public MyDB db;

    public ServiceCommande() 
    {
        db = MyDB.getInstance();
    }

    @Override
    public void AjouterCommande(Commande cmd) {
         String req = "insert into commande(NUMERO_COMMANDE,DATE_PAYEMENT,MODE_PAYEMENT,ID_CLIENT) values("+cmd.getNum_commande()+","+cmd.getDate_commande()+",'"+cmd.getMode_payement()+"','"+cmd.getID_client()+"')"; 
        try
        {
            Statement stm = db.getConnexion().createStatement();
            stm.executeUpdate(req);
            System.out.println("ajout commande effectuer avec succes");
        }
        catch(SQLException add)
        {
            System.err.println("probleme d'ajout de commande "+add.getSQLState());
        }
    }

    @Override
    public void SupprimerCommande(int num_commande) {
        Commande c=ConsulterCommande(num_commande);
        if(c.getNum_commande() == 0){
            System.out.println("Commande n'existe pas");
        }
        else{
                try
               {
                PreparedStatement stm = db.getConnexion().prepareStatement("delete from commande where NUMERO_COMMANDE = ?"); 
                stm.setInt(1,num_commande);
                stm.executeUpdate();
                System.out.println("Suppression commande effectue avec succes");
                }
                catch(SQLException add)
                {
                System.out.println("Echec de suppression de commande"+add.getSQLState());
                }
            }
    }

    @Override
    public void UpdateCommande(Commande cmd) {
        try
        {
         PreparedStatement stm = db.getConnexion().prepareStatement("UPDATE commande SET DATE_PAYEMENT = "+ cmd.getDate_commande()+",SET MODE_PAYEMENT = '"+ cmd.getMode_payement()+"' WHERE NUMERO_COMMANDE = "+cmd.getNum_commande()+" ;"); 
         stm.executeUpdate();
            System.out.println("Update effectue avec succes");
        }
        catch(SQLException add)
        {
            System.out.println("Echec d' update "+add.getSQLState());
        }
    }

    @Override
    public List<Commande> ConsulterListe_Commandes() {
        List<Commande>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from commande";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          {
              Commande cmd=new Commande();
              cmd.setNum_commande(s.getInt(1));
              cmd.setDate_commande(s.getDate(2));
              cmd.setMode_payement(s.getString(3));
              cmd.setID_client(s.getString(4));
              tabE.add(cmd);
          }
          System.out.println("patientiez en cours d'affichage de la liste des commandes");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage de commande ");
      }
      //System.out.println(tabE.toString());
      return tabE;
    }

    @Override
    public Commande ConsulterCommande(int num_commande) {
        Commande c=new Commande();
      try
      {
          String sql = "select * from commande where NUMERO_COMMANDE = '"+ num_commande +"' ";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          {
              Commande cmd=new Commande();
              cmd.setNum_commande(s.getInt(1));
              cmd.setDate_commande(s.getDate(2));
              cmd.setMode_payement(s.getString(3));
              cmd.setID_client(s.getString(4));
              c=cmd;
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      //System.out.println(tabE.toString());
      return c;
    }

   
    

    
}
