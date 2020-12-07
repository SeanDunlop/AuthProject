package com.company;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.awt.*;

public class SelectScreen extends JFrame{

    //https://stackoverflow.com/questions/2856480/resizing-a-imageicon-in-a-jbutton
    int[] password;
    MainMenu menu;

    Util u = new Util();

    JButton[] arr_btn = new JButton[49];

    boolean[] arr_select = new boolean[49];

    Font fnt_btn = new Font("Tahoma", Font.BOLD, 1);

    public void forge(){
        for(boolean b : arr_select){
            b = false;
        }

        ImageIcon[] icons = u.icons();

        for(int x = 0; x < arr_btn.length; x++){
            arr_btn[x] = new JButton(String.valueOf(x));
            arr_btn[x].setIcon(new ImageIcon(icons[x].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH)));
            arr_btn[x].setBounds(10+(110*(x%7)), (int) (100 + (110 * (Math.floor(x / 7)))), 100, 100);
            arr_btn[x].setVisible(true);
            arr_btn[x].setHorizontalAlignment(0);
            arr_btn[x].setFont(fnt_btn);
            add(arr_btn[x]);

            arr_btn[x].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    int x = Integer.parseInt(((JButton)evt.getSource()).getText());
                    if(u.passEmpty(password) && !u.contains(password,x)){
                        //Turn the button on
                        password[u.firstEmpty(password)] = x;
                        u.sysout(password[0] + " " + password[1] + " " + password[2]);

                        //Do button make bigger

                    }else if(u.contains(password, x)){
                        //Turn the button off
                        password[u.find(password, x)] = -1;
                        u.sysout(password[0] + " " + password[1] + " " + password[2]);
                        
                        //Do button make smaller

                    }
                    u.sysout(x);
                }
            });
        }
    }

    

    public SelectScreen(int[] _password, MainMenu _menu){
        super("Selection Menu"); //sets title of window
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                //SEND VALUES BACK TO THING
                setVisible(false);
                menu.setVisible(true);
                dispose();
            }
        });
        setResizable(false);
        setSize(820,920); //size of window
        setLayout(null); //removes default layout
        setVisible(true); //makes frame visible

        password = _password;
        menu = _menu;

        forge();
    }
}
