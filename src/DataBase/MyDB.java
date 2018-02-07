/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class MyDB 
{
  private String url;
  private String user;
  private String pwd;
  private Connection con;
  static MyDB instance;
    private MyDB()
    {
        this.url="jdbc:mysql://localhost/";
        this.user="root";
        this.pwd="";
        

    }
    public Connection getConnexion()
    {
      
        try
        {
              this.con = DriverManager.getConnection(url,user,pwd);
              System.out.println("Connexion etablis avec succes");
        }
       catch(SQLException e)
       {
           System.out.println("pas de connexion:"+e.getSQLState()+e.getMessage());
       }
        return  this.con;
    }
    public static MyDB getInstance()
    {
        if(instance !=null)
        {
             return instance;
        }
        instance = new MyDB();
        return instance;
    }
  
    
}
