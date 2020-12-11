package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AuthenticationScreen extends JFrame {
    //https://stackoverflow.com/questions/2856480/resizing-a-imageicon-in-a-jbutton
    int[] password;
    int[] attempt;
    int[] selected;
    LinkedList<Integer> dragSelected;
    boolean dragging;

    Challenge challenge;

    final int DIMENSION = 7;

    MainMenu menu;

    JLabel lbl_title = new JLabel("Select at least 2 target icons");

    Util u = new Util();

    JButton[] arr_btn = new JButton[49];
    JButton enter_btn = new JButton("OK");

    Font fnt_btn = new Font("Tahoma", Font.BOLD, 1);
    Font fnt_title = new Font("Tahoma", Font.BOLD, 30);

    ArrayList<Integer> indices;
    int[][] map = new int[7][7];

    LineBorder selectedBorder;
    LineBorder deselectedBorder;

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

        if(challenge.getCurrent() == 1)
        {
            setPassFirst(indices, password);
        }else{setPassLast(indices, password);}


        dragSelected = new LinkedList<>();


        int x;
        //JButton[][] buttonMap = new JButton[DIMENSION][DIMENSION];
        selectedBorder = new LineBorder(Color.GREEN, 4);
        deselectedBorder = new LineBorder(Color.GREEN, 0);

        this.addMouseListener(new dragSelectListener(this));

        for(int i = 0; i < arr_btn.length; i++){

            x = indices.get(i);
            arr_btn[x] = new JButton(String.valueOf(x));
            arr_btn[x].setIcon(new ImageIcon(icons[x].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH)));
            arr_btn[x].setBounds(10+(110*(i%DIMENSION)), (int) (100 + (110 * (Math.floor(i / DIMENSION)))), 100, 100);
            map[i%7][(int)Math.floor(i/7)] = x; // thats a mess

            //if(u.contains(password, x)){
            //    u.makeButtonSmaller(arr_btn[x]);
            //}
            arr_btn[x].setVisible(true);
            arr_btn[x].setHorizontalAlignment(0);
            arr_btn[x].setFont(fnt_btn);
            add(arr_btn[x]);

            //click to rectangle select code
            /*
            arr_btn[x].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    int x = Integer.parseInt(((JButton)evt.getSource()).getText());

                    if(u.passEmpty(attempt) && !u.contains(attempt,x)){
                        //Turn the button on
                        attempt[u.firstEmpty(attempt)] = x;
                        //Do button make smaller
                        u.makeButtonSmaller((JButton)evt.getSource());

                        //update selected area
                        if(u.firstEmpty(attempt) == -1)
                        {
                            selected = getValuesInRect(map, mapSearch(map, attempt[0]), mapSearch(map, attempt[1]));
                            for(int i : indices)
                            {
                                arr_btn[i].setBorder(deselectedBorder);
                            }
                            for(int i : selected)
                            {
                                arr_btn[i].setBorder(selectedBorder);
                            }
                            printMap(map);
                            for(int i = 0; i < selected.length; i++)u.sysline(selected[i] + ", ");

                            if(checkSuccess(password, selected, 2))
                            {
                                u.sysout("You did it!");
                            }
                        }

                    }else if(u.contains(attempt, x)){
                        //Turn the button off
                        attempt[u.find(attempt, x)] = -1;

                        //Do button make bigger
                        u.makeButtonBigger((JButton)evt.getSource());

                        //clear selected area
                        selected = new int[0];
                        for(int i : indices)
                        {
                            arr_btn[i].setBorder(deselectedBorder);
                        }
                    }
                }
            });

             */
            // listener for click
            arr_btn[x].addMouseListener(new dragSelectListener(this));

            //listener for drag

            int finalX = x;
            arr_btn[x].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(dragging)
                    {
                        //u.sysout("DRAG");
                        if(!dragSelected.contains(finalX))
                        {
                            dragSelected.add(finalX);
                        }
                        while(dragSelected.size() > 10)dragSelected.remove();

                        for(int i : indices)
                        {
                            arr_btn[i].setBorder(deselectedBorder);
                        }
                        for(int i : dragSelected)
                        {
                            arr_btn[i].setBorder(selectedBorder);
                        }
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    dragSelected.clear();
                    if(!dragSelected.contains(finalX))
                    {
                        dragSelected.add(finalX);
                    }
                    while(dragSelected.size() > 10)dragSelected.remove();

                    for(int i : indices)
                    {
                        arr_btn[i].setBorder(deselectedBorder);
                    }
                    for(int i : dragSelected)
                    {
                        arr_btn[i].setBorder(selectedBorder);
                    }
                }
            });
        }

        this.setSize(820,1000);
        enter_btn.setBounds(230,870,320,80);
        enter_btn.setVisible(true);
        enter_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(dragSelected.size() != 0)
                {
                    int[] selectedValues = new int[dragSelected.size()];
                    for(int i = 0; i<dragSelected.size();i++)
                    {
                        selectedValues[i] = dragSelected.get(i);
                    }
                    if(challenge.getCurrent() == 1)
                    {
                        if(checkSuccess(password, selectedValues, 2) && !challenge.over)
                        {
                            selectedValues = new int[0];
                            u.sysout("You Did It");
                            startNextChallenge(1);
                            return;
                        }
                        else if(!challenge.over)
                        {
                            u.sysout("You failed, but moving on");
                            startNextChallenge(0);
                            return;
                        }
                    }
                    else if(challenge.getCurrent() == 0 && !challenge.over)
                    {
                        u.sysout("That one didn't matter anyways!");
                        startNextChallenge(1);
                        return;
                    }
                }

            }});
        add(enter_btn);
    }

    public void unforge()
    {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    public void startNextChallenge(int success)
    {
        challenge.getNext(success);
        if(challenge.over && challenge.passed)
        {
            u.sysout("We passed!");
            MainMenu menu = new MainMenu(password, "Authentication Successful");
            setVisible(false);
            //dispose();
        }
        else if(challenge.over && !challenge.passed)
        {
            u.sysout("Failed!");
            MainMenu menu = new MainMenu(password, "Authentication Failed");
            setVisible(false);
            //dispose();
        }
        else if(!challenge.over)
            {
                unforge();
                forge();
            }
    }


    public boolean checkSuccess(int[] password, int[] selectedValues, int threshhold)
    {
        if(u.numberOfMatches(password, selectedValues) >= threshhold)
        {
            return true;
        }else return false;
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
        attempt = new int[]{-1,-1};
        menu = _menu;

        challenge = new Challenge(3, 2);

        forge();
    }

    public void setPassFirst(ArrayList<Integer> indices, int[] password)
    {
        if(u.firstEmpty(password) != -1)return;
        for(int i=1; i<password.length; i++)
        {
            int targetIndex = indices.indexOf(password[0]);
            int targetx = targetIndex%DIMENSION;
            int targety = (int)Math.floor(targetIndex/DIMENSION);

            int passIndex = indices.indexOf(password[i]);
            int passx = passIndex%DIMENSION;
            int passy = (int)Math.floor(passIndex/DIMENSION);
            if (passy < targety || (passy == targety && passx <= targetx))
            {
                    //swap them
                    indices.set(passIndex, password[0]);
                    indices.set(targetIndex,password[i]);
                    //u.sysout("swapped");
            }
        }
    }

    public void setPassLast(ArrayList<Integer> indices, int[] password)
    {
        if(u.firstEmpty(password) != -1)return;
        for(int i=1; i<password.length; i++)
        {
            int targetIndex = indices.indexOf(password[0]);
            int targetx = targetIndex%DIMENSION;
            int targety = (int)Math.floor(targetIndex/DIMENSION);

            int passIndex = indices.indexOf(password[i]);
            int passx = passIndex%DIMENSION;
            int passy = (int)Math.floor(passIndex/DIMENSION);
            if (passy > targety || (passy == targety && passx >= targetx))
            {
                //swap them
                indices.set(passIndex, password[0]);
                indices.set(targetIndex,password[i]);
                u.sysout("swapped");
            }
        }
    }

    public int[] getValuesInRect(int[][] map, int[]p1, int[]p2)
    {
        int x1 = p1[0];
        int x2 = p2[0];
        int y1 = p1[1];
        int y2 = p2[1];

        ArrayList<Integer> result = new ArrayList<>();

        for(int x = Math.min(x1,x2); x <= Math.max(x1,x2); x++)
        {
            for(int y = Math.min(y1,y2); y <= Math.max(y1,y2); y++)
            {
                result.add(map[x][y]);
            }
        }
        int[] finalResult = new int[result.size()];
        for(int i=0;i<result.size();i++)finalResult[i] = result.get(i);
        return finalResult;
    }

    public int[] mapSearch(int[][] map, int target)
    {
        int[] location = new int[]{-1,-1};
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j< map[i].length; j++)
            {
                if(map[i][j] == target)
                {
                    location[0] = i;
                    location[1] = j;
                }
            }
        }
        return location;
    }

    public int[] printMap(int[][] map)
    {
        System.out.print('\n');
        int[] location = new int[]{-1,-1};
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j< map[i].length; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.print('\n');
        }
        return location;
    }

}

class Challenge
{
    public int challengeCount;
    public int current;
    public int[] challenges;
    public int[] results;
    boolean passed;
    boolean over;

    public Challenge(int count, int realCount)
    {
        challengeCount = count;
        challenges = new int[challengeCount];
        results = new int[challengeCount];
        ArrayList<Integer> generated = new ArrayList<>();
        passed = false;
        over = false;

        for(int i=0;i<challengeCount;i++)
        {
            if(i<realCount)
            {
                generated.add(1);
            }else generated.add(0);
        }
        Collections.shuffle(generated);
        for(int i = 0; i < generated.size(); i++)
        {
            challenges[i] = generated.get(i);
        }
        current = 0;
    }

    public int getNext(int result)
    {
        results[current] = result;
        if(current < challenges.length-1)
        {
            current ++;
            return challenges[current];
        }
        else
            {
                boolean temp = true;
                for(int val : results)
                {
                    if(val ==0 ) temp = false;
                }
                passed = temp;
                over = true;
            }
        return challenges[current];
    }

    public int getCurrent()
    {
        return challenges[current];
    }

}

class dragSelectListener implements MouseListener
{
    private AuthenticationScreen screen;
    public dragSelectListener(AuthenticationScreen _screen){screen = _screen;}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        screen.dragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        screen.dragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}