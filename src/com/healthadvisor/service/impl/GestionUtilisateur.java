/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Utilisateur;
import com.heathadvisor.service.IGestionMedecin;
import com.heathadvisor.service.IGestionUtilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khattout
 */
public class GestionUtilisateur implements IGestionUtilisateur{
 MyDB database;

    public GestionUtilisateur() {
        database = MyDB.getInstance();
    }
    @Override
    public void AjouterUtilisateur(Utilisateur utilisateur) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Insert into utilisateur (cin,nom,prenom,email,date_naiss,sexe,pays,ville,num_tel) "+" values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            preparedStmt.setString(1,utilisateur.getCin());
            preparedStmt.setString(2,utilisateur.getNom());
            preparedStmt.setString(3,utilisateur.getPrenom());
            preparedStmt.setString(4,utilisateur.getEmail());
            
            java.util.Date utilStartDate = utilisateur.getDate_naiss();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            preparedStmt.setDate(5, sqlStartDate );
            
            preparedStmt.setString(6,utilisateur.getSexe());
            preparedStmt.setString(7,utilisateur.getPays());
            preparedStmt.setString(8,utilisateur.getVille());
            preparedStmt.setInt(9,utilisateur.getNum_tel());

              preparedStmt.executeUpdate();

            System.out.println("Insertion avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }       }

    @Override
    public void ModifierUtilisateur(Utilisateur utilisateur) {
        System.out.println("Modification Utilisateur...");
        try{
                Statement stm =database.getConnexion().createStatement();
           String sql="UPDATE utilisateur SET nom='"+utilisateur.getNom()+"', prenom='"+utilisateur.getPrenom()+"', email='"+utilisateur.getEmail()+"', date_naiss='"+utilisateur.getDate_naiss()+"', sexe='"+utilisateur.getSexe()+"', 'pays="+utilisateur.getPays()+"', ville='"+utilisateur.getVille()+"', num_tel='"+utilisateur.getNum_tel()+"' WHERE cin='"+utilisateur.getCin()+"'";
            stm.executeUpdate(sql);
           System.out.println("Utilisateur bien modifiÃ©");
           
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    }

    @Override
    public void SupprimerUtilisateurCin(String cin) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from utilisateur where cin='"+cin+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public List<Utilisateur> ListUtilisateurs() {
    ArrayList<Utilisateur> listutil= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM utilisateur" ;
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                System.out.println("recuperation des donnees");
                Utilisateur util= new Utilisateur(rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getDate("date_naiss"), rs.getString("sexe"), rs.getString("pays"), rs.getString("ville"), rs.getInt("num_tel"));
                listutil.add(util);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listutil;     }

    @Override
    public Utilisateur AfficherUtilisateurCin(String cin) {
    Utilisateur util =null;
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="select * from utilisateur where cin='"+cin+"'" ;
            ResultSet rs = stm.executeQuery(sql);
                        while(rs.next()){
                            util= new Utilisateur(rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getDate("date_naiss"), rs.getString("sexe"), rs.getString("pays"), rs.getString("ville"),rs.getInt("num_tel"));
                        }
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return util;    
    }
    
}
