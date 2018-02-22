/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;


import com.healthadvisor.entities.Maladie;
import com.heathadvisor.service.IGestionMaladie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Firassov
 */
public class GestionMaladie implements IGestionMaladie{
    @Override
    public ArrayList<Maladie> Diagnostique(String token,int anne,String gender,String Symptomes){
        if(gender.equals("Homme")||gender.equals("Garçon")){
            gender="male";
        }
        else{
        gender="female";
    }
        String n;
        double p;
        ArrayList<String> l=new ArrayList<>();
        ArrayList<Maladie> maladies=new ArrayList<>();
        URL url;
         String ch="";
        try {
    url = new URL("https://healthservice.priaid.ch/diagnosis?symptoms=["+Symptomes+"]&gender="+gender+"&year_of_birth="+anne+"&token="+token+"&language=fr-fr&format=json");
        

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
        for (String line; (line = reader.readLine()) != null;) {
           ch=ch+line;
        }
    }   catch (IOException ex) {
            Logger.getLogger(Maladie.class.getName()).log(Level.SEVERE, null, ex);
        }
    } catch (MalformedURLException ex) {
            Logger.getLogger(Maladie.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Modifier JSON
        StringBuilder sb = new StringBuilder(ch);
        sb.insert(ch.length(),"}");
        sb.insert(0,"{\"Home\":");
        ch=sb.toString();
        System.out.println(ch);
        //parcourir JSON
        JSONObject obj = new JSONObject(ch);
        JSONArray arr=obj.getJSONArray("Home");
        for (int i = 0; i < arr.length(); i++)
        {
                        l.removeAll(l);
            n=(arr.getJSONObject(i).getJSONObject("Issue").getString("Name"));
            p=arr.getJSONObject(i).getJSONObject("Issue").getDouble("Accuracy");
            JSONArray a=arr.getJSONObject(i).getJSONArray("Specialisation");
            for (int j = 0; j < a.length(); j++)
                    {
                        l.add(a.getJSONObject(j).getString("Name"));
                    }
            Maladie m=new Maladie(n,p,l);
            maladies.add(m);
        }
        return maladies;
    }
}
