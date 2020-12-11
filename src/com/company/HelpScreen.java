package com.company;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.awt.*;

public class HelpScreen extends JFrame {

    public JTextArea HelpLabel = new JTextArea("""
    This is an Over-The-Shoulder attack resistant authentication system. To create a password,
    select three icons from the "Set Password" page. Remember which icon was selected first.
    To authenticate, first find the uppermost selected icon from the grid. If two or more icons
     are in the same row, choose the leftmost icon. If this icon is the first one you selected,
     drag to select at least two of your chosen icons and click "OK" to continue. Otherwise, select
     any area and click "OK" to continue. After three tests, authentication will be complete.
            """);

    public MainMenu menu;

    public HelpScreen(MainMenu _menu)
    {
        super("Help"); //sets title of window
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menu = _menu;
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
        setSize(600,400); //size of window
        setLayout(null); //removes default layout
        setVisible(true); //makes frame visible
        HelpLabel.setBounds(25,25,550,400);
        HelpLabel.setVisible(true);
        HelpLabel.setEditable(false);
        add(HelpLabel);
    }
}
