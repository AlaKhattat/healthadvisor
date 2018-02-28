/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javamail;

import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author khattout
 */
 public  class SendEmail {
      private String username;
      private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      
 
   
   
   public int sendMail(String from,String password,String to,String sub,String message){
        try
        {
            Properties props = System.getProperties();
              // -- Attaching to default Session, or we could start a new one --
              props.put("mail.transport.protocol", "smtp" );
              props.put("mail.smtp.starttls.enable","true" );
              props.put("mail.imap.ssl.enable", "true");
              props.put("mail.smtp.socketFactory.port", "587");    
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

              props.put("mail.smtp.host","smtp.gmail.com");
              props.put("mail.smtp.auth", "true" );
              this.username =from;
              this.password=password;
              Authenticator auth = new SMTPAuthenticator();
              Session session = Session.getInstance(props, auth);
              // -- Create a new message --
              Message msg = new MimeMessage(session);
              // -- Set the FROM and TO fields --
              msg.setFrom(new InternetAddress(from));
              msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              msg.setSubject(sub);
              msg.setText(message);
              // -- Set some other header information --
              msg.setHeader("MyMail", "Mr. XYZ" );
              msg.setSentDate(new Date());
              // -- Send the message --
              Transport.send(msg);
              System.out.println("Message sent to"+to+" OK." );
              return 0;
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
          System.out.println("Exception "+ex);
          return -1;
        }
  }

// Also include an inner class that is used for authentication purposes

private class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            //String username =  "alakhattat17@gmail.com";           // specify your email id here (sender's email id)
           // String password = "aqzsedrftgyh";                                      // specify your password here
            return new PasswordAuthentication(username, password);
        }
  }


            
    
}
