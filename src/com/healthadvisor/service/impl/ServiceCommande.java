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
        
        java.util.Date date_commande = cmd.getDate_commande();
        java.sql.Date sqlDate_commande = new java.sql.Date(date_commande.getTime());
        
        try
        {   Statement stm = db.getConnexion().createStatement();
            String req = "insert into commande(REFERENCE_COMMANDE,DATE_PAYEMENT,MODE_PAYEMENT,ID_CLIENT) "+" values (?,?,?,?)"; 
            PreparedStatement preparedStmt = db.getConnexion().prepareStatement(req);
            preparedStmt.setString(1, cmd.getReference_commande());
            preparedStmt.setDate(2, sqlDate_commande);
            preparedStmt.setString(3, cmd.getMode_payement());
            preparedStmt.setString(4, cmd.getID_client());
            preparedStmt.executeUpdate();
            System.out.println("ajout commande effectuer avec succes");
        }
        catch(SQLException add)
        {
            System.err.println("probleme d'ajout de commande "+add.getSQLState());
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
              cmd.setReference_commande(s.getString(2));
              cmd.setDate_commande(s.getDate(3));
              cmd.setMode_payement(s.getString(4));
              cmd.setID_client(s.getString(5));
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
    public void SupprimerCommande(String ref_commande) {
        Commande c=ConsulterCommande(ref_commande);
        if(c.getNum_commande() == 0){
            System.out.println("Commande n'existe pas");
        }
        else{
                try
               {
                PreparedStatement stm = db.getConnexion().prepareStatement("delete from commande where REFERENCE_COMMANDE = ?"); 
                stm.setString(1,ref_commande);
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
    public Commande ConsulterCommande(String ref_commande) {
         Commande c=new Commande();
      try
      {
          String sql = "select * from commande where REFERENCE_COMMANDE = '"+ ref_commande +"' ";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          {
              Commande cmd=new Commande();
              cmd.setNum_commande(s.getInt(1));
              cmd.setReference_commande(s.getString(2));
              cmd.setDate_commande(s.getDate(3));
              cmd.setMode_payement(s.getString(4));
              cmd.setID_client(s.getString(5));
              c=cmd;
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return c;
    }

   
    

    
}
