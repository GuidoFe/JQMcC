package qmcc.espansione;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Marcello
 */

public class TabEsp {
    protected ArrayList<RigaEsp> tab = new ArrayList ();   
    
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
    
    public byte[] newCode (RigaEsp e1, RigaEsp e2){
        byte [] newCode = new byte [e1.code.length];
        
        for (int i = 0; i<e2.code.length; i++){
            if (e1.code[i] != e2.code [i])
                newCode[i] = 2;
            else newCode[i] = e1.code[i];
        }
        return newCode;
    }
    
    public boolean indiffCheck (RigaEsp e1, RigaEsp e2){
        boolean check = true;
        
        for (int i = 0; i<e2.code.length; i++){
            if (e1.code[i] != e2.code[i])
                if((e1.code[i] == 2) || (e2.code[i] ==2) )
                    check = false;
        }
        return check;
    }
    
    public boolean check (RigaEsp e1, RigaEsp e2){
        int d=0;
        for (int i = 0; i<e1.code.length; i++)
            if(e1.code[i]!=e2.code[i])
                d++;
        return d==1;
    }
    
    public int getLength () {
        return tab.size();
    }
    
    public ArrayList<Integer> getMini (int i) {
        return tab.get(i).mini;
    }
    
    public byte [] getCode (int i){
        return tab.get(i).code;
    } 
    
    public static TabEsp expand(String args []){
        TabEsp result = new TabEsp();
        TabEsp tabAtt = new TabEsp (args);
        TabEsp tabNext = new TabEsp();
        RigaEsp aux;
        boolean trovato = true;
        boolean ricerca = false;
        
        tabAtt.alignZero();
        int i, c, n;     
        
        while (trovato == true) {
            
            trovato = false;
            
            for (i = 0; i < tabAtt.tab.size(); i++ )
                for (c = 0; c < tabAtt.tab.size(); c++ ) {
                    if(tabAtt.check(tabAtt.tab.get(i),tabAtt.tab.get(c))){  
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
        return result;
    }
}
