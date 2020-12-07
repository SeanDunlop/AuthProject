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

    public void forge(){

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
        setSize(445,400); //size of window
        setLayout(null); //removes default layout
        setVisible(true); //makes frame visible

        password = _password;
        menu = _menu;

        forge();
    }
}
