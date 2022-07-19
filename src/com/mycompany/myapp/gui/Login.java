/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author user
 */

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.gui.Home2;

public class Login extends Form{
    private Label lb;
    Form current;

   public Login(Form previous){
       this.setLayout(BoxLayout.y());
   
               // Form f2=new Form("hello imed");
        lb=new Label("Authentification");
         TextField txt1 = new TextField("", "Email");
                  TextField txt2 = new TextField("", "Password",20,TextField.PASSWORD);
                   Button btn = new Button("Valider");
                   this.addAll(txt1,txt2,btn);
                   
                btn.addActionListener(e->{
            ConnectionRequest con=new ConnectionRequest();
      String url = Statics.BASE_URL+"loginUser";
        con.setPost(false);
        con.addArgument("email", txt1.getText());
        con.addArgument("password", txt2.getText());
        
        con.setUrl(url);
        con.addResponseListener(ee->{
        String response =new String(con.getResponseData());
            System.out.println("response"+response); 
            if(!response.equals("Bad credentials")){
          Dialog.show("Success","Connection accepted",new Command("OK"));
             new Home2().show();
            }
            else{
              Dialog.show("Bad credentials", "Bad credentials", new Command("OK"));}
               
          
                });
        NetworkManager.getInstance().addToQueueAndWait(con);
                });
        this.show();
               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                        }

  
}