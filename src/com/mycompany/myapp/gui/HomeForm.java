/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
   private Resources theme;
   public HomeForm(Form previous){
      theme = UIManager.initFirstTheme("/theme");
        current=this; //Back 
        setTitle("Welcome");
   
        setLayout(BoxLayout.yCenter());
        this.getStyle().setBgColor(0X3BB925);
        
         final Label apple = new Label(theme.getImage("logo.jpg"));
           Container c=new Container();
         c.add(apple);
         c.setLayout(BoxLayout.y());
       Label lb= new Label("Choose an option");
        Button btnSignUp = new Button("Sign Up");
        //Button btnListClients = new Button("List Clients");
        Button btnLogin=new Button("Login");
        
        btnSignUp.addActionListener(e-> new SignUpForm(current).show());
      //  btnListClients.addActionListener(e-> new ListClientsForm(current).show());
        btnLogin.addActionListener(e-> new Login(current).show());
      //  add(c);
        addAll(c,btnSignUp,btnLogin);}
   
    public HomeForm() {
          theme = UIManager.initFirstTheme("/theme");
        current=this; //Back 
        setTitle("Welcome");
   
        setLayout(BoxLayout.yCenter());
        this.getStyle().setBgColor(0X3BB925);
        
         final Label apple = new Label(theme.getImage("logo.jpg"));
           Container c=new Container();
         c.add(apple);
         c.setLayout(BoxLayout.y());
       Label lb= new Label("Choose an option");
        Button btnSignUp = new Button("Sign Up");
        //Button btnListClients = new Button("List Clients");
        Button btnLogin=new Button("Login");
        
        btnSignUp.addActionListener(e-> new SignUpForm(current).show());
      //  btnListClients.addActionListener(e-> new ListClientsForm(current).show());
        btnLogin.addActionListener(e-> new Login(current).show());
      //  add(c);
        addAll(c,btnSignUp,btnLogin);
        
        
    }
    
    
}
