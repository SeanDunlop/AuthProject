package com.company;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;

public class MainMenu extends JFrame{
    JLabel lbl_title = new JLabel("Authentication Demo");
    JButton btn_set = new JButton("Set Password");
    JButton btn_go = new JButton("Test Authentication");
    JButton btn_help = new JButton("Help");
    
    JLabel lbl_credit = new JLabel("Icons made by Freepik from www.flaticon.com");

    // label used for the output of the last page the user was on
    JLabel lbl_result = new JLabel("Please create a password");

    Util u = new Util();

    
    int[] password = new int[]{-1,-1,-1};

    Font fnt_title = new Font("Tahoma", Font.BOLD, 30);

    // function to put the GUI together
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

        lbl_result.setBounds(10,270,420,30);
        lbl_result.setHorizontalAlignment(0);
        lbl_result.setVisible(true);
        add(lbl_result);

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

        btn_help.setBounds(110,130,200,40);
        btn_help.setVisible(true);
        btn_help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpScreen help = new HelpScreen(MainMenu.this);
                setVisible(false);
            }
        });
        add(btn_help);

        btn_go.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AuthenticationScreen set = new AuthenticationScreen(password, getSelf());
                setVisible(false);
            }
        });

    }

    public MainMenu getSelf(){
        return this;
    }

    public MainMenu(){
        this(new int[]{-1,-1,-1}, "Please create a password");
    }

    public MainMenu(int[] _password, String result)
    {
        super("Authentication Menu"); //sets title of window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //program ends on closing of window
        setResizable(false);
        setSize(445,400); //size of window
        setLayout(null); //removes default layout
        setVisible(true); //makes frame visible
        password  = _password;
        lbl_result.setText(result);
        forge();

        u.sysout("hey");
    }
}
