/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc.espansione;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Marcello
 */

public class TabEsp {
    ArrayList<RigaEsp> tab = new ArrayList ();   
    
    public TabEsp (String args []) {
        for (String arg : args) {
            RigaEsp n = new RigaEsp(Integer.parseInt(arg));
            tab.add(n);            
        }
    }
    public TabEsp (){
        
    }
    
    public void printTab (){
        for (int i= 0; i<tab.size(); i++) {
            (tab.get(i)).printRig();
        }
    }
    
    public void alignZero () {
        int max = 0;
        int c;
        for (int i = 0; i<tab.size(); i++){
            c = tab.get(i).code.length;
            if (c>max)
                max=c;
        }
        byte [] codAux;
        int diff;
        int diffAux;
        int n;
        for (int i = 0; i<tab.size(); i++){
            codAux = tab.get(i).code;
            c = tab.get(i).code.length;
            diff = max - c;
            tab.get(i).code= new byte [max];
            n=0;
            while (c>0) {
               tab.get(i).code[diff]=codAux[n];
               c--;
               n++;
               diff ++;
            }            
        }
    }
    
    public boolean isEqual (RigaEsp e1, RigaEsp e2){
        return Arrays.equals(e1.code, e2.code);        
    }
    
    public int diff (RigaEsp e1, RigaEsp e2){
        int index = 0;
        
        for (int i = 0; i<e2.code.length; i++){
            if (e2.code[i] != e2.code [i])
                index = i;
        }
        return index;
    }
}
