package qmcc.espansione;
/**
 *
 * @author Marcello
 */

import java.util.*;
public class RigaEsp {  //Struttura dati per ogni riga della tabella di espansione
    ArrayList<Integer> mini = new ArrayList (0);  //mintermini   
    byte [] code;  //implicante in forma binaria
    boolean signed;  //stato corrispondente all'avvenuta espansione di una coppia
    
    public RigaEsp(byte [] newCode) {  //costruttore per implicante espanso
        code = newCode;
        signed = false;
        
    }

    public RigaEsp (int n) { //costruttore che converte ogni mintermine da decimale a binario
        mini.add(0,n);
        code = convert(mini);
        signed = false;
    }

    private byte[] convert (ArrayList<Integer> m){ //convertitore da decimale a binario
        int i = m.get(0);
        int resto;
        String result = "";
        
        while (i>0) {
        resto=i%2;
         i/=2;
         result=resto+result;
        }
        char c;
        byte b;
        byte [] codice = new byte [result.length()];
        int n = 0;
        while (n < result.length()){
            c = result.charAt(n);
            b = (byte) (((byte)c) - 48);
            codice [n] = b;          
            n++;            
        }        
        return codice;
    }
    //Metodo non più usato. Conta gli uni di un implicante
    /*private int contaUno (byte [] code) {
        int c = 0;
        
        for (int i = 0; i < code.length; i++){
            if (code[i]==1)
                c++;
        }
        return c;
    }*/
    
    public void sign (){ 
        signed = true;
    }
    
    public void printRig () {
        System.out.println("Numero " + mini + "\nCodice Binario " + Arrays.toString(code));
    }
}


