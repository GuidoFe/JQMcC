/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc;
import java.util.*;
/**
 *
 * @author Guido_Fe
 */
class RigaIndexP {
    int index;
    byte[] P;
}

public class TabIndexP{
    private ArrayList <RigaIndexP> tab;
    
    public byte[] getP(int index){
        boolean trovato = false;
        for(int i = 0; i<tab.size() && !trovato; i++){
            RigaIndexP RI = tab.get(i);
            if(RI.index == index)
                return RI.P;
        }
        System.out.println("ERROR: Can't find P" + index);
        System.exit(-1);
        return null;
    }
}