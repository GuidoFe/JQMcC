/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc.espansione;
/**
 *
 * @author Marcello
 */

import java.util.*;
public class RigaEsp {
    ArrayList<Integer> mini = new ArrayList (0);
    byte [] code;
    boolean signed;
    int nUni;

    public RigaEsp (int n) {
        mini.add(0,n);
        code = convert(mini);
        signed = false;
        nUni = contaUno(code);
    }

    private byte[] convert (ArrayList<Integer> m){
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
    
    private int contaUno (byte [] code) {
        int c = 0;
        
        for (int i = 0; i < code.length; i++){
            if (code[i]==1)
                c++;
        }
        return c;
    }
    
    public void sign (){
        signed = true;
    }
    
    public void printRig () {
        System.out.println("Numero " + mini + "\nCodice Binario " + Arrays.toString(code) + "\nNumero Uno " + nUni + "\n");
    }
}


