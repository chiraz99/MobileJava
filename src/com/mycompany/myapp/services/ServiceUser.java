/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceUser {

    public ArrayList<User> users;
    
    public static ServiceUser instance;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean addUser(User t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "create";
    
       req.setUrl(url);
       req.setPost(false);
   req.addArgument("role", t.getRole());
       req.addArgument("first_name", t.getFirst_name());
       req.addArgument("last_name", t.getLast_name());
       req.addArgument("email", t.getEmail());
       req.addArgument("password", t.getPassword());
       req.addArgument("initial", t.getPassword());
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<User> parseTasks(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                User t = new User();
    t.setUser_id(((int)Float.parseFloat(obj.get("user_id").toString())));
                t.setRole(obj.get("role").toString());
                t.setFirst_name(obj.get("first_name").toString());
                t.setLast_name(obj.get("last_name").toString());
                t.setEmail(obj.get("email").toString());
                t.setPassword(obj.get("password").toString());
                t.setInitial(obj.get("initial").toString());

                
                if (obj.get("first_name")==null)
              t.setFirst_name("null");
                else
                    t.setFirst_name(obj.get("first_name").toString());
                users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
    }
    
    public ArrayList<User> getAllUsers(){
        req = new ConnectionRequest();
       
        String url = Statics.BASE_URL+"listUser";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
      
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
}
