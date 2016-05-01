/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc.copertura;
import java.util.*;
/**
 *
 * @author Guido_Fe
 */
class RigaIndexP {
    private int index;
    private byte[] P;
    public int getIndex(){
        return index;
    }
    public byte[] getP(){
        return P;
    }
}

public class TabIndexP{
    private ArrayList <RigaIndexP> tab;
    
    public byte[] getP(int index){
        boolean trovato = false;
        for(int i = 0; i<tab.size() && !trovato; i++){
            RigaIndexP RI = tab.get(i);
            if(RI.getIndex() == index)
                return RI.getP();
        }
        System.out.println("ERROR: Can't find P" + index);
        System.exit(-1);
        return null;
    }
    
    public void delP(int pIndex){
        boolean trovato = false;
        for(int i = 0; i<tab.size() && !trovato; i++)
            if(tab.get(i).getIndex() == pIndex){
                tab.remove(i);
                trovato = true;
            }
    }
}