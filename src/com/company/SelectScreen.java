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

    public void forge(){
        ImageIcon[] icons = u.icons();

        for(int x = 0; x < arr_btn.length; x++){
            arr_btn[x] = new JButton();
            arr_btn[x].setIcon(new ImageIcon(u.icons()[x].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH)));
            arr_btn[x].setBounds(10+(110*(x%7)), (int) (100 + (110 * (Math.floor(x / 7)))), 100, 100);
            arr_btn[x].setVisible(true);
            add(arr_btn[x]);
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
