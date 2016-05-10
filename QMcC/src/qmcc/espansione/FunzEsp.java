/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc.espansione;

import java.util.Arrays;

/**
 *
 * @author Marcello
 */
public class FunzEsp {
    public static void expand(String args []){
        TabEsp result = new TabEsp();
        TabEsp tabAtt = new TabEsp (args);
        TabEsp tabNext = new TabEsp();
        RigaEsp aux;
        boolean trovato = true;
        boolean ricerca = false;
        
        tabAtt.alignZero();
        //tabAtt.printTab();
        int i, c, n;     
        
        while (trovato == true) {
            
            trovato = false;
            
            for (i = 0; i < tabAtt.tab.size(); i++ )
                for (c = 0; c < tabAtt.tab.size(); c++ ) {
                    if (((tabAtt.diff(tabAtt.tab.get(i),tabAtt.tab.get(c))== 1)||(tabAtt.diff(tabAtt.tab.get(i),tabAtt.tab.get(c))== -1))&& tabAtt.indiffCheck(tabAtt.tab.get(i),tabAtt.tab.get(c))) {
                        tabAtt.tab.get(i).sign();
                        trovato = true;
                        aux= new RigaEsp (tabAtt.newCode(tabAtt.tab.get(i),tabAtt.tab.get(c)));
                        aux.mini.addAll(tabAtt.tab.get(i).mini);
                        aux.mini.addAll(tabAtt.tab.get(c).mini);
                        for (n=0; n<tabNext.tab.size();n++){
                            if(tabNext.isEqual(tabNext.tab.get(n), aux))
                                ricerca= true;   
                        }
                        if (!ricerca)    
                                tabNext.tab.add(aux);
                        ricerca = false; 
                    }
                }
            for (i = 0; i < tabAtt.tab.size(); i++ )
                if(!tabAtt.tab.get(i).signed)
                    result.tab.add(tabAtt.tab.get(i));
            tabAtt.tab.clear();
            tabAtt.tab.addAll(tabNext.tab);
            tabNext.tab.clear();
        }
        result.tab.addAll(tabNext.tab);
        System.out.println("\n\nTAVOLA ESPANSA\n\n");
        result.printTab();
        //return result;
    }
    
}
