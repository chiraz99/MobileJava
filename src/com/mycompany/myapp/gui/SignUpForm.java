/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author bhk
 */
public class SignUpForm extends Form{

    public SignUpForm(Form previous) {
        setTitle("SignUp");
        setLayout(BoxLayout.yCenter());
        Label lbl=new Label();
        TextField tFName = new TextField("","First Name");
        TextField tLName= new TextField("", "Last Name");
        TextField tEmail= new TextField("", "Email");
        TextField tPassword= new TextField("", "Password",20,TextField.PASSWORD);
        Button btnValider = new Button("Sign Up");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tFName.getText().length()==0)||(tLName.getText().length()==0)||(tEmail.getText().length()==0)||(tPassword.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        User t = new User(tFName.getText().toString(), tLName.getText().toString(),tEmail.getText().toString(),tPassword.getText().toString());
                        if( ServiceUser.getInstance().addUser(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                                        new Home2().show();

                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR","problem");
                    }
                    
                }
                
                
            }
        });
        
        addAll(tFName,tLName,tEmail,tPassword,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
