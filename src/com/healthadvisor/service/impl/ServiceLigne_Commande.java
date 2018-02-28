
package com.healthadvisor.service.impl;


import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Ligne_Commande;
import com.healthadvisor.entities.Produit;
import com.heathadvisor.service.InterfaceLigne_Commande;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ServiceLigne_Commande implements InterfaceLigne_Commande{
    
     public MyDB db;

    public ServiceLigne_Commande() 
    {
        db = MyDB.getInstance();
    }

    @Override
    public void AjouterLigne_Commande(Ligne_Commande lc) {
        
        try
        {   Statement stm =db.getConnexion().createStatement();
            String sql="Insert into Ligne_Commande (ID_PRODUIT,ID_COMMANDE,PRIX,QUANTITE) "+" values (?,?,?,?)";
                PreparedStatement preparedStmt = db.getConnexion().prepareStatement(sql);
                preparedStmt.setInt(1, lc.getId_produit());
                preparedStmt.setInt(2, lc.getId_commande());
                preparedStmt.setFloat(3, lc.getPrix_commande());
                preparedStmt.setInt(4, lc.getQuantite());
                preparedStmt.executeUpdate();
                System.err.println(preparedStmt.toString());
            System.out.println("ajout ligne commande effectuer avec succes");
        }
        catch(SQLException add)
        {
            System.err.println("probleme d'ajout de ligne commande"+add.getSQLState());
        }
    }

    @Override
    public void SupprimerLigne_Commande(String id_produit, int id_commande) {
       
                try
               {
                PreparedStatement stm = db.getConnexion().prepareStatement("delete from ligne_commande where id_commande = ? AND id_produit= ?"); 
                stm.setInt(1,id_commande);
                stm.setString(2, id_produit);
                stm.executeUpdate();
                System.out.println("Suppression effectue avec succes");
                }
                catch(SQLException add)
                {
                System.out.println("Echec de suppression"+add.getSQLState());
                }
            
    }

    @Override
    public void UpdateLigne_Commande(Ligne_Commande lc) {
        try
        {
         PreparedStatement stm = db.getConnexion().prepareStatement("UPDATE ligne_commande SET prix = "+ lc.getPrix_commande()+",quantite = '"+lc.getQuantite()+"' where id_commande = '"+lc.getId_commande()+"' AND id_produit = '"+lc.getId_produit()+"' ;"); 
         stm.executeUpdate();
            System.out.println("Update ligne commande effectue avec succes");
        }
        catch(SQLException add)
        {
            System.out.println("Echec d' update "+add.getSQLState());
        }
    }

    @Override
    public Ligne_Commande ConsulterLigne_Commande(String id_produit, int id_commande) {
         List<Ligne_Commande>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from ligne_commande where id_commande = "+id_commande+" AND id_produit = "+id_produit+" ";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          {
              Ligne_Commande lc=new Ligne_Commande();
              lc.setId_commande(s.getInt(2));
              lc.setId_produit(s.getInt(1));
              lc.setPrix_commande(s.getFloat(3));
              lc.setQuantite(s.getInt(4));
              tabE.add(lc);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      //System.out.println(tabE.toString());
      return tabE.get(0);
    }

    @Override
    public List<Ligne_Commande> Liste_LigneCommandes_IDPRoduit(String id_produit) {
         List<Ligne_Commande>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from ligne_commande where id_produit = "+id_produit+" ";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          {
              Ligne_Commande lc=new Ligne_Commande();
              lc.setId_commande(s.getInt(2));
              lc.setId_produit(s.getInt(1));
              lc.setPrix_commande(s.getFloat(3));
              lc.setQuantite(s.getInt(4));
              tabE.add(lc);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      //System.out.println(tabE.toString());
      return tabE;
    }

    @Override
    public List<Ligne_Commande> Liste_LigneCommandes_IDCommande(int id_commande) {
        List<Ligne_Commande>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from ligne_commande where id_commande = "+id_commande+" ";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          {
              Ligne_Commande lc=new Ligne_Commande();
              lc.setId_commande(s.getInt(2));
              lc.setId_produit(s.getInt(1));
              lc.setPrix_commande(s.getFloat(3));
              lc.setQuantite(s.getInt(4));
              tabE.add(lc);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      //System.out.println(tabE.toString());
      return tabE;
    }

    @Override
    public List<ArrayList> ListeProduits_PlusVendus() {
        List<ArrayList>tabE = new ArrayList<>();
      try
      {
          String sql = "select produit.nom,produit.quantite,sum(ligne_commande.quantite) from produit,ligne_commande where ligne_commande.id_produit=produit.id_produit group by produit.id_produit order by produit.quantite DESC ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {  
              ArrayList<String> al=new ArrayList<>();
              al.add(s.getString(1));
              al.add(s.getString(2));
              al.add(s.getString(3));
              
              tabE.add(al);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println(add);
      }
      return tabE;
    }

    @Override
    public List<ArrayList> ChiffresAffaires() {
         List<ArrayList>tabE = new ArrayList<>();
      try
      {
          String sql = "select extract(MONTH from commande.date_payement)as 'mois',SUM(ligne_commande.PRIX) from commande,ligne_commande where ligne_commande.id_commande=commande.NUMERO_COMMANDE and (extract(Year from sysdate())-extract(YEAR from commande.date_payement))=0 group by extract(MONTH from commande.date_payement); ";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {  
              ArrayList<String> al=new ArrayList<>();
              al.add(s.getString("mois"));
              al.add(s.getString(2));
              
              tabE.add(al);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println(add);
      }
      return tabE;
    }
    
}
