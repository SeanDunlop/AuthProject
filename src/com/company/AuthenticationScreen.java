package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.Collections;
import java.util.ArrayList;
public class AuthenticationScreen extends JFrame {
    //https://stackoverflow.com/questions/2856480/resizing-a-imageicon-in-a-jbutton
    int[] password;
    int[] attempt;

    MainMenu menu;

    JLabel lbl_title = new JLabel("Please Select 3 Icons");

    Util u = new Util();

    JButton[] arr_btn = new JButton[49];

    Font fnt_btn = new Font("Tahoma", Font.BOLD, 1);
    Font fnt_title = new Font("Tahoma", Font.BOLD, 30);

    ArrayList<Integer> indices;

    public void forge(){
        ImageIcon[] icons = u.icons();

        lbl_title.setHorizontalAlignment(0);
        lbl_title.setFont(fnt_title);
        lbl_title.setBounds(0,30,820,30);
        lbl_title.setVisible(true);
        add(lbl_title);

        indices = new ArrayList<>();
        for(int i = 0; i < arr_btn.length; i++)
        {
            indices.add(i);
        }
        Collections.shuffle(indices);
        int x;
        for(int i = 0; i < arr_btn.length; i++){

            x = indices.get(i);
            arr_btn[x] = new JButton(String.valueOf(x));
            arr_btn[x].setIcon(new ImageIcon(icons[x].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH)));
            arr_btn[x].setBounds(10+(110*(i%7)), (int) (100 + (110 * (Math.floor(i / 7)))), 100, 100);
            //if(u.contains(password, x)){
            //    u.makeButtonSmaller(arr_btn[x]);
            //}
            arr_btn[x].setVisible(true);
            arr_btn[x].setHorizontalAlignment(0);
            arr_btn[x].setFont(fnt_btn);
            add(arr_btn[x]);

            arr_btn[x].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    int x = Integer.parseInt(((JButton)evt.getSource()).getText());

                    if(u.passEmpty(attempt) && !u.contains(attempt,x)){
                        //Turn the button on
                        attempt[u.firstEmpty(attempt)] = x;
                        u.sysout(attempt[0] + " " + attempt[1] + " " + attempt[2]);

                        //Do button make smaller
                        u.makeButtonSmaller((JButton)evt.getSource());

                    }else if(u.contains(attempt, x)){
                        //Turn the button off
                        attempt[u.find(attempt, x)] = -1;
                        u.sysout(attempt[0] + " " + attempt[1] + " " + attempt[2]);

                        //Do button make bigger
                        u.makeButtonBigger((JButton)evt.getSource());
                    }
                    u.sysout(x);
                    if(u.compareArr(password, attempt))
                    {
                        u.sysout("You did it!");
                    }
                }
            });
        }
    }



    public AuthenticationScreen(int[] _password, MainMenu _menu){
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
        attempt = new int[]{-1,-1,-1};
        menu = _menu;

        forge();
    }

}
