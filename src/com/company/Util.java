package com.company;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Util {

    public ImageIcon[] icons(){
        ImageIcon[] icons = new ImageIcon[49];

        icons[0] = new ImageIcon("001-knight.png");
        icons[1] = new ImageIcon("002-wizard.png");
        icons[2] = new ImageIcon("003-dwarf.png");
        icons[3] = new ImageIcon("004-elf.png");
        icons[4] = new ImageIcon("005-witch.png");
        icons[5] = new ImageIcon("006-ogre.png");
        icons[6] = new ImageIcon("007-giant.png");
        icons[7] = new ImageIcon("008-gnome.png");
        icons[8] = new ImageIcon("009-hood.png");
        icons[9] = new ImageIcon("010-wolf.png");
        icons[10] = new ImageIcon("011-queen.png");
        icons[11] = new ImageIcon("012-king.png");
        icons[12] = new ImageIcon("013-princess.png");
        icons[13] = new ImageIcon("014-prince.png");
        icons[14] = new ImageIcon("015-frogprince.png");
        icons[15] = new ImageIcon("016-fairy.png");
        icons[16] = new ImageIcon("017-robinhood.png");
        icons[17] = new ImageIcon("018-pirate.png");
        icons[18] = new ImageIcon("019-goblin.png");
        icons[19] = new ImageIcon("020-elf.png");
        icons[20] = new ImageIcon("021-pig.png");
        icons[21] = new ImageIcon("022-tinman.png");
        icons[22] = new ImageIcon("023-scarecrow.png");
        icons[23] = new ImageIcon("024-cowardlylion.png");
        icons[24] = new ImageIcon("025-pinocchio.png");
        icons[25] = new ImageIcon("026-puss.png");
        icons[26] = new ImageIcon("027-hatter.png");
        icons[27] = new ImageIcon("028-cyclops.png");
        icons[28] = new ImageIcon("029-whiterabbit.png");
        icons[29] = new ImageIcon("030-mermaid.png");
        icons[30] = new ImageIcon("031-genie.png");
        icons[31] = new ImageIcon("032-vampire.png");
        icons[32] = new ImageIcon("033-unicorn.png");
        icons[33] = new ImageIcon("034-dragon.png");
        icons[34] = new ImageIcon("035-phoenix.png");
        icons[35] = new ImageIcon("036-poison.png");
        icons[36] = new ImageIcon("037-poison.png");
        icons[37] = new ImageIcon("038-cauldron.png");
        icons[38] = new ImageIcon("039-poisonous.png");
        icons[39] = new ImageIcon("040-magicmirror.png");
        icons[40] = new ImageIcon("041-excalibur.png");
        icons[41] = new ImageIcon("043-magiclamp.png");
        icons[42] = new ImageIcon("044-crystalball.png");
        icons[43] = new ImageIcon("045-ring.png");
        icons[44] = new ImageIcon("046-broom.png");
        icons[45] = new ImageIcon("047-wand.png");
        icons[46] = new ImageIcon("048-tower.png");
        icons[47] = new ImageIcon("049-castle.png");
        icons[48] = new ImageIcon("050-fairytale.png");

        /*
        for(int x = 0; x < 49; x++){
            icons[x] = new ImageIcon(icons[x].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
        }
        */
        return icons;
    }

    public boolean passEmpty(int[] i){
        boolean flag = false;
        for(int _i : i){
            if(_i == -1){
                flag = true;
            }
        }
        return flag;
    }

    public boolean contains(int[] _i, int x){
        for(int i : _i){
            if(i == x){
                return true;
            }
        }
        return false;
    }

    public int firstEmpty(int[] _i){
        for(int x = 0; x < _i.length; x++){
            if(_i[x] == -1){
                return x;
            }
        }
        return -1;
    }

    public int find(int[] i, int x){
        for(int n = 0; n < i.length; n++){
            if(x == i[n]){
                return n;
            }
        }
        return -1;
    }

    public void sysout(Object... o){
        for(Object _o : o){
            if(_o.getClass().isPrimitive() || _o.getClass().equals(String.class)){
                System.out.println(_o);
            }else{
                System.out.println(_o.toString());
            }
        }
    }

    public void makeButtonSmaller(JButton b){
        Rectangle r = b.getBounds();
        r.grow(-10,-10);
        b.setBounds(r);
        ImageIcon i = new ImageIcon(icons()[Integer.parseInt(b.getText())].getImage().getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH));
        b.setIcon(i);
    }

    public void makeButtonBigger(JButton b){
        Rectangle r = b.getBounds();
        r.grow(10,10);
        b.setBounds(r);
        ImageIcon i = new ImageIcon(icons()[Integer.parseInt(b.getText())].getImage().getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH));
        b.setIcon(i);
    }
}
