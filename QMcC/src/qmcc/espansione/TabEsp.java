/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc.espansione;

import java.util.ArrayList;

/**
 *
 * @author Marcello
 */
public class TabEsp {
    ArrayList<RigaEsp> tab = new ArrayList<RigaEsp> (0);   
    
    public TabEsp (String args []) {
        for (int i = 0; i< args.length; i++) {
            RigaEsp n= new RigaEsp (Integer.parseInt(args[i]));
            tab.add(n);            
        }
    }
    
    public void printTab (){
        for (int i= 0; i<tab.size(); i++) {
            (tab.get(i)).printRig();
        }
    }
}
