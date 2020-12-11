package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AuthenticationScreen extends JFrame {


    int[] password; // the user's password
    int[] attempt; //  The current attempt's selected indices


    LinkedList<Integer> dragSelected; // The queue of icons selected by dragging - uses FIFO with capacity of 10
    boolean dragging; // if the user is currently dragging the mouse

    Challenge challenge; // the current challenge object

    final int DIMENSION = 7; // The width/height of the display - Only change if we have a different image set

    MainMenu menu; // the menu object we came from

    JLabel lbl_title = new JLabel("Select at least 2 target icons"); // title label

    Util u = new Util(); // utility class for some array functions as well as the image loader

    JButton[] arr_btn = new JButton[49]; // the array of buttons to select
    JButton enter_btn = new JButton("OK"); // the ok button

    Font fnt_btn = new Font("Tahoma", Font.BOLD, 1);
    Font fnt_title = new Font("Tahoma", Font.BOLD, 30);

    ArrayList<Integer> indices; // all of the button indices go here

    LineBorder selectedBorder; // the borders we use for indicating which icons are selected
    LineBorder deselectedBorder;

    public void forge(){

        ImageIcon[] icons = u.icons();

        // set up the title label
        lbl_title.setHorizontalAlignment(0);
        lbl_title.setFont(fnt_title);
        lbl_title.setBounds(0,30,820,30);
        lbl_title.setVisible(true);
        add(lbl_title);

        // put all of the indices into a list and shuffle it so we have a random layout of the icons
        indices = new ArrayList<>();
        for(int i = 0; i < arr_btn.length; i++)
        {
            indices.add(i);
        }
        Collections.shuffle(indices);

        // if challenge is real, put first target image in earliest position, otherwise make sure it isn't first
        if(challenge.getCurrent() == 1)
        {
            setPassFirst(indices, password);
        }else{setPassLast(indices, password);}


        dragSelected = new LinkedList<>(); // our queue of selected images

        int x;
        selectedBorder = new LineBorder(Color.GREEN, 4);
        deselectedBorder = new LineBorder(Color.GREEN, 0);

        this.addMouseListener(new dragSelectListener(this));

        // set up the panel of icons as buttons
        for(int i = 0; i < arr_btn.length; i++){

            x = indices.get(i);
            arr_btn[x] = new JButton(String.valueOf(x));
            arr_btn[x].setIcon(new ImageIcon(icons[x].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH)));
            arr_btn[x].setBounds(10+(110*(i%DIMENSION)), (int) (100 + (110 * (Math.floor(i / DIMENSION)))), 100, 100);

            arr_btn[x].setVisible(true);
            arr_btn[x].setHorizontalAlignment(0);
            arr_btn[x].setFont(fnt_btn);
            add(arr_btn[x]);

            // listener for click
            arr_btn[x].addMouseListener(new dragSelectListener(this));

            //listener for drag
            int finalX = x;
            arr_btn[x].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(dragging)
                    {
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
                        if(checkSuccess(password, selectedValues, 2) && !challenge.over) // go to next challenge and record this as a pass
                        {
                            selectedValues = new int[0];
                            u.sysout("You Did It");
                            startNextChallenge(1);
                            return;
                        }
                        else if(!challenge.over) // keep going even if the user fails the check to not reveal anything
                        {
                            u.sysout("You failed, but moving on");
                            startNextChallenge(0);
                            return;
                        }
                    }
                    else if(challenge.getCurrent() == 0 && !challenge.over) // ignore the selected icons and count it as a pass anyways
                    {
                        u.sysout("That one didn't matter anyways!");
                        startNextChallenge(1);
                        return;
                    }
                }

            }});
        add(enter_btn);
    }

    public void unforge() // clear the screen to re-create the panel
    {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    public void startNextChallenge(int success) // go to the next challenge in the challenge set
    {
        challenge.getNext(success);
        if(challenge.over && challenge.passed) // return to main menu with success message
        {
            u.sysout("We passed!");
            MainMenu menu = new MainMenu(password, "Authentication Successful");
            setVisible(false);
            //dispose();
        }
        else if(challenge.over && !challenge.passed) // return to main menu with failed message
        {
            u.sysout("Failed!");
            MainMenu menu = new MainMenu(password, "Authentication Failed");
            setVisible(false);
            //dispose();
        }
        else if(!challenge.over) // challenge isn't over so we get the panel ready again
            {
                unforge();
                forge();
            }
    }


    public boolean checkSuccess(int[] password, int[] selectedValues, int threshold) // threshold is how many of the target values must be selected
    {
        if(u.numberOfMatches(password, selectedValues) >= threshold)
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

    public void setPassFirst(ArrayList<Integer> indices, int[] password) // swap the locations of target images to make sure target 1 is in first position
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
            }
        }
    }

    public void setPassLast(ArrayList<Integer> indices, int[] password) // swap the locations of target images to make sure target 1 is not in first position
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
            }
        }
    }

    public int[] getValuesInRect(int[][] map, int[]p1, int[]p2) // used for rectangle selection, not called in this version
    {
        int x1 = p1[0];
        int x2 = p2[0];
        int y1 = p1[1];
        int y2 = p2[1];

        ArrayList<Integer> result = new ArrayList<>();

        // go through the selected area and grab all of the icon indices
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

    public int[] mapSearch(int[][] map, int target) // search a 2d array for a value - used for rectangle selection
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

    public int[] printMap(int[][] map) // debugging function for the rectangle selection
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

// challenge class represents a set of stages where the user must select some subset of their target images
// fake stages allow user to input anything which makes guessing the target icons from OTS attacks more difficult
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
        // shuffle the challenges so the locations of real/fake challenges are random
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
                // only pass if all results are 1 (success)
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
// custom mouseListener for adding to all of the buttons
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