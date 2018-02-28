
package com.healthadvisor.service.impl;


import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Produit;
import com.heathadvisor.service.InterfaceProduit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceProduit implements InterfaceProduit{

    
    public MyDB db;

    public ServiceProduit() 
    {
        db = MyDB.getInstance();
    }
    
    @Override
    public void AjouterProduit(Produit p) {
       
        java.util.Date date_mise = p.getDate_mise();
        java.sql.Date sqlDate_mise = new java.sql.Date(date_mise.getTime());
        try
        {
                Statement stm =db.getConnexion().createStatement();
                String sql="Insert into produit (REFERENCE,NOM,PRIX_VENTE,URL_IMAGE,DESCRIPTION,TYPE,DATE_MISE,PROMOTION,ID_USER,QUANTITE,SIGNALISATION_ETAT) "+" values (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStmt = db.getConnexion().prepareStatement(sql);
                preparedStmt.setString(1, p.getReference());
                preparedStmt.setString(2, p.getNom());
                preparedStmt.setFloat(3, p.getPrix_vente());
                preparedStmt.setString(4, p.getUrl_image());
                preparedStmt.setString(5,p.getDescription());
                preparedStmt.setString(6, p.getType());
                preparedStmt.setDate(7, sqlDate_mise);
                preparedStmt.setFloat(8, p.getPromotion());
                preparedStmt.setString(9, p.getId_user());
                preparedStmt.setInt(10, p.getQuantite());
                preparedStmt.setInt(11, p.getSignaler());
                preparedStmt.executeUpdate();
                System.out.println("ajout produit effectuer avec succes");
        }
        catch(SQLException add)
        {
            System.err.println("probleme d'ajout de produit "+add.getSQLState());
        }
    }

    @Override
    public void SupprimerProduit(String ref) {
        Produit p= ConsulterProduit(ref);
        System.out.println(p.toString());
        if(p.getReference() == null){
            System.out.println("Produit n'existe pas");
        }
        else{
                try
               {
                PreparedStatement stm = db.getConnexion().prepareStatement("delete from produit where REFERENCE = ?"); 
                stm.setString(1,ref);
                stm.executeUpdate();
                System.out.println("Suppression effectue avec succes");
                }
                catch(SQLException add)
                {
                System.out.println("Echec de suppression"+add.getSQLState());
                }
            }
    }

    @Override
    public void UpdateProduit(Produit p) {
        java.util.Date date_mise = p.getDate_mise();
        java.sql.Date sqlDate_mise = new java.sql.Date(date_mise.getTime());
        try
        {

                PreparedStatement preparedStmt =db.getConnexion().prepareStatement("UPDATE `produit` SET `NOM` = ?, `PRIX_VENTE` = ?,`URL_IMAGE` = ?,`DESCRIPTION` = ?,`TYPE` = ?,`PROMOTION`= ?,`QUANTITE`=?,`SIGNALISATION_ETAT`=? ,`DATE_MISE`=? WHERE `REFERENCE` = ? ;");
                preparedStmt.setString(1, p.getNom());
                preparedStmt.setFloat(2, p.getPrix_vente());
                preparedStmt.setString(3, p.getUrl_image());
                preparedStmt.setString(4,p.getDescription());
                preparedStmt.setString(5, p.getType());
                preparedStmt.setFloat(6, p.getPromotion());
                preparedStmt.setInt(7, p.getQuantite());
                preparedStmt.setInt(8, p.getSignaler());
                preparedStmt.setDate(9, sqlDate_mise);
                preparedStmt.setString(10, p.getReference());
                System.out.println(preparedStmt.toString());
        preparedStmt.executeUpdate();
            System.out.println("Update produit effectue avec succes");
        }
        catch(SQLException add)
        {
            System.out.println("Echec d' update de produit  "+add.getSQLState());
        }
    }

    @Override
    public Produit ConsulterProduit(String ref) {
         Produit p= new Produit();
      try
      {
          String sql = "select * from produit where REFERENCE = '"+ ref +"' ";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          { 
              Produit pdt=new Produit();
              pdt.setId_produit(s.getInt(1));
              pdt.setReference(s.getString(2));
              pdt.setNom(s.getString(3));
              pdt.setPrix_vente(s.getFloat(4));
              pdt.setUrl_image(s.getString(5));
              pdt.setDescription(s.getString(6));
              pdt.setType(s.getString(7));
              pdt.setDate_mise(s.getDate(8));
              pdt.setPromotion(s.getFloat(9));
              pdt.setId_user(s.getString(10));
              pdt.setQuantite(s.getInt(11));
              pdt.setSignaler(s.getInt(12));
              p=pdt;
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
        System.err.println(p.toString());
      return p;
    }

    @Override
    public List<Produit> ConsulterListe_Produits() {
          List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE;
    }        

    @Override
    public List<Produit> ListeProduits_Promotion(float prom_min, float prom_max) {
         List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit where promotion >="+ prom_min+" AND promotion <="+ prom_max+" ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE;
    }

    @Override
    public List<Produit> ListProduits_Date(Date date_min, Date date_max) {
         List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit where date_mise >='"+ date_min+ "' AND date_mise <= '"+ date_max+ "' ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE;
    }

    @Override
    public List<Produit> ListProduits_Prix(float prix_min, float prix_max) {
        List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit where prix_vente >="+ prix_min+" AND prix_vente <="+ prix_max +" ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE;
    }

    @Override
    public List<Produit> ListProduits_Categorie(String type) {
        List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit where type = '"+type+"' ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE;
    }

    @Override
    public List<Produit> ListProduits_Image(boolean img) {
        List<Produit>tabE = new ArrayList<>();
if(img){
      try
      {
          String sql = "select * from produit where url_image != '' ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
}
else{
    try
      {
          String sql = "select * from produit where url_image ='' ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
}

      return tabE;
    }

    @Override
    public List<Produit> ListProduits_User(String id_user) {
         List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit where id_user = '"+id_user+"' ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE;
    }

    @Override
    public List<Produit> ListProduits_Signaler(int signaler) {
         List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit where signalisation_etat = '"+signaler+"' ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE;
    }

    @Override
    public Produit ConsulterProduit_Reference(String ref) {
        List<Produit>tabE = new ArrayList<>();
      try
      {
          String sql = "select * from produit where reference = '"+ref+"' ;";
          Statement stm = db.getConnexion().createStatement();
          ResultSet s = stm.executeQuery(sql);
          
          while(s.next())
          {
              Produit p=new Produit();
              p.setId_produit(s.getInt(1));
              p.setReference(s.getString(2));
              p.setNom(s.getString(3));
              p.setPrix_vente(s.getFloat(4));
              p.setUrl_image(s.getString(5));
              p.setDescription(s.getString(6));
              p.setType(s.getString(7));
              p.setDate_mise(s.getDate(8));
              p.setPromotion(s.getFloat(9));
              p.setId_user(s.getString(10));
              p.setQuantite(s.getInt(11));
              p.setSignaler(s.getInt(12));
              tabE.add(p);
          }
          System.out.println("patientiez en cours d'affichage");
      }
      catch(SQLException add)
      {
          System.out.println("probleme d'affichage ");
      }
      return tabE.get(0);
    }
    
    
    
    
}
