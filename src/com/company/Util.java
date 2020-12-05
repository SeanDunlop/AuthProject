package com.company;

import javax.swing.*;
import java.util.*;

public class Util {

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
