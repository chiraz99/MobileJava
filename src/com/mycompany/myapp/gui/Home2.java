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
 * @author user
 */
public class Home2 extends Form {
    Form current;
   private Resources theme;
    public Home2() {
          theme = UIManager.initFirstTheme("/theme");
        current=this; //Back 
        setTitle("Home");
   
        setLayout(BoxLayout.yCenter());
        this.getStyle().setBgColor(0X3BB925);
        
         final Label apple = new Label(theme.getImage("logo.jpg"));
           Container c=new Container();
         c.add(apple);
         c.setLayout(BoxLayout.y());
       Label lb= new Label("Choose an option");
        
        
        Button btnListClients = new Button("Customer list");
                Button btnListVols = new Button("Flight list");
         Button btnListCar = new Button("Cars list");
         Button btnHotelClients = new Button("Hotels list");
        Button btnLogOut=new Button("Log out");
        
       
      btnListClients.addActionListener(e-> new ListClientsForm(current).show());
        btnLogOut.addActionListener(e->new HomeForm(current).show());
      add(c);
        addAll(btnListClients ,btnLogOut);
        
        
    }
    
}
