package qmcc.espansione;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Marcello
 */

public class TabEsp {
    protected ArrayList<RigaEsp> tab = new ArrayList ();   
    
    public TabEsp (String args []) throws NumberFormatException { //costruttore della tabella degli implianti
        for (String arg : args) {
            RigaEsp n = new RigaEsp(Integer.parseInt(arg));
            tab.add(n);            
        }
    }
    public TabEsp (){  //costruttore per creare una tabella vuota
        
    }
    
    public void printTab (){
        for (int i= 0; i<tab.size(); i++) {
            (tab.get(i)).printRig();
        }
    }
    
    public void alignZero () {  //allinea il numero di input in base al mintermine maggiore
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
    
    public byte[] newCode (RigaEsp e1, RigaEsp e2){  //genera il codice espanso
        byte [] newCode = new byte [e1.code.length];
        
        for (int i = 0; i<e2.code.length; i++){
            if (e1.code[i] != e2.code [i])
                newCode[i] = 2;
            else newCode[i] = e1.code[i];
        }
        return newCode;
    }
    
    public boolean check (RigaEsp e1, RigaEsp e2){  //verifica che una coppia sia espandibile
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
    
    public static TabEsp expand(String args [])  throws NumberFormatException {  //funzione di espansione
        TabEsp result = new TabEsp();  //tabella con gli implicanti primi
        TabEsp tabAtt = new TabEsp (args);  //tabella in espansione
        TabEsp tabNext = new TabEsp();  //nuova tabella in costruzione
        RigaEsp aux;
        boolean trovato = true;
        boolean ricerca = false;
        
        tabAtt.alignZero();
        int i, c, n;     
        
        while (trovato == true) {  //ciclo di espansione, termina quando non riesce ad espandere nessuna coppia
            
            trovato = false;
            
            for (i = 0; i < tabAtt.tab.size(); i++ )
                for (c = 0; c < tabAtt.tab.size(); c++ ) {
                    if(tabAtt.check(tabAtt.tab.get(i),tabAtt.tab.get(c))){  //controllo delle coppie se sono espandibili
                        tabAtt.tab.get(i).sign();
                        trovato = true;
                        aux= new RigaEsp (tabAtt.newCode(tabAtt.tab.get(i),tabAtt.tab.get(c)));
                        aux.mini.addAll(tabAtt.tab.get(i).mini);
                        aux.mini.addAll(tabAtt.tab.get(c).mini);
                        for (n=0; n<tabNext.tab.size();n++){  //controllo delle coppie se non sono state già espanse
                            if(tabNext.isEqual(tabNext.tab.get(n), aux))
                                ricerca= true;   
                        }
                        if (!ricerca)    
                                tabNext.tab.add(aux);
                        ricerca = false; 
                    }
                }
            for (i = 0; i < tabAtt.tab.size(); i++ )
                if(!tabAtt.tab.get(i).signed)  //si aggiungono alla tabella risultato le righe non segnate
                    result.tab.add(tabAtt.tab.get(i));
            tabAtt.tab.clear();  
            tabAtt.tab.addAll(tabNext.tab);
            tabNext.tab.clear();
        }
        result.tab.addAll(tabNext.tab);
        return result;
    }
}
