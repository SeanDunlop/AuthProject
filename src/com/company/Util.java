package com.company;

import javax.swing.*;
import java.util.*;

public class Util {

    public ImageIcon[] icons(){
        ImageIcon[] icons = new ImageIcon[49];

        icons[0] = new ImageIcon("001-knight");
        icons[1] = new ImageIcon("002-wizard");
        icons[2] = new ImageIcon("003-dwarf");
        icons[3] = new ImageIcon("004-elf");
        icons[4] = new ImageIcon("005-witch");
        icons[5] = new ImageIcon("006-ogre");
        icons[6] = new ImageIcon("007-giant");
        icons[7] = new ImageIcon("008-gnome");
        icons[8] = new ImageIcon("009-hood");
        icons[9] = new ImageIcon("010-wolf");
        icons[10] = new ImageIcon("011-queen");
        icons[11] = new ImageIcon("012-king");
        icons[12] = new ImageIcon("013-princess");
        icons[13] = new ImageIcon("014-prince");
        icons[15] = new ImageIcon("015-frogprince");
        icons[16] = new ImageIcon("016-fairy");
        icons[17] = new ImageIcon("017-robinhood");
        icons[18] = new ImageIcon("018-pirate");
        icons[19] = new ImageIcon("019-goblin");
        icons[20] = new ImageIcon("020-elf");
        icons[21] = new ImageIcon("021-pig");
        icons[22] = new ImageIcon("022-tinman");
        icons[23] = new ImageIcon("023-scarecrow");
        icons[24] = new ImageIcon("024-cowardlylion");
        icons[25] = new ImageIcon("025-pinocchio");
        icons[26] = new ImageIcon("026-puss");
        icons[27] = new ImageIcon("027-hatter");
        icons[28] = new ImageIcon("028-cyclops");
        icons[29] = new ImageIcon("029-whiterabbit");
        icons[30] = new ImageIcon("030-mermaid");
        icons[31] = new ImageIcon("031-genie");
        icons[32] = new ImageIcon("032-vampire");
        icons[33] = new ImageIcon("033-unicorn");
        icons[34] = new ImageIcon("034-dragon");
        icons[35] = new ImageIcon("035-phoenix");
        icons[36] = new ImageIcon("036-poison");
        icons[37] = new ImageIcon("037-poison");
        icons[38] = new ImageIcon("038-caludron");
        icons[39] = new ImageIcon("039-poisonous");
        icons[40] = new ImageIcon("040-magicmirror");
        icons[41] = new ImageIcon("041-excalibur");
        icons[42] = new ImageIcon("043-magiclamp");
        icons[43] = new ImageIcon("044-crystalball");
        icons[44] = new ImageIcon("045-ring");
        icons[45] = new ImageIcon("046-broom");
        icons[46] = new ImageIcon("047-wand");
        icons[47] = new ImageIcon("048-tower");
        icons[48] = new ImageIcon("049-castle");
        icons[49] = new ImageIcon("050-fairytale");
        
        return icons;
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

}
