package com.company;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.util.*;
import java.awt.*;

public class MainMenu extends JFrame{
    JLabel lbl_title = new JLabel("This is the Title");
    JButton btn_set = new JButton("Button");
    JButton btn_go = new JButton("Other Button");
    
    JLabel lbl_credit = new JLabel("Icons made by Freepik from www.flaticon.com");

    Util u = new Util();

    
    int[] password = new int[]{-1,-1,-1};

    Font fnt_title = new Font("Tahoma", Font.BOLD, 30);

    public void forge(){
        lbl_title.setBounds(10,10,420,65);
        lbl_title.setFont(fnt_title);
        lbl_title.setHorizontalAlignment(0);
        lbl_title.setVisible(true);
        add(lbl_title);

        lbl_credit.setBounds(10,300,420,30);
        lbl_credit.setHorizontalAlignment(0);
        lbl_credit.setVisible(true);
        add(lbl_credit);

        btn_set.setBounds(10,85,200,40);
        btn_set.setVisible(true);
        add(btn_set);
        
        btn_set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectScreen set = new SelectScreen(password, getSelf());
                setVisible(false);
            }
        });

        btn_go.setBounds(220,85,200,40);
        btn_go.setVisible(true);
        add(btn_go);

        btn_go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AuthenticationScreen set = new AuthenticationScreen(password, getSelf());
                setVisible(false);
            }
        });

        btn_go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btn_goClicked(evt);
            }
        });
    }

    public MainMenu getSelf(){
        return this;
    }

    public MainMenu(){
        super("Authentication Menu"); //sets title of window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //program ends on closing of window
        setResizable(false);
        setSize(445,400); //size of window
        setLayout(null); //removes default layout
        setVisible(true); //makes frame visible


        forge();

        u.sysout("hey");
    }
}
