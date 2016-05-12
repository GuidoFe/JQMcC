/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.*;

/**
 *
 * @author Guido_Fe
 */
public class Main {

    /**
     * @param args 
     */
    public static void main(String[] args) {
       String [] mini = null;
       boolean setIn = false;
       boolean setOut = false;
       boolean setLet = false;
       String fileOut = new String();
       ArrayList<byte[]> result = new ArrayList();
//        boolean stopRead = false;
//        for(int i = 0; i < args.length && !stopRead; i++){
//            if("-ti".equals(args[i]))
//        }
        if(args.length >= 0 && "help".equals(args[0])){
            System.out.print("Arguments:  [-ti fileInput.txt] [-to fileOutput.txt] [-l] [Minterms]"
            + "\nEs. 'java -jar QMcC.jar -to output.txt 2 4 5 7'\n"
            + "With -l it will use alphabet symbols instead of (1, 0, -), if"
            + "there aren't too many inputs."
            + "\nAuthors: Marcello Simonati, Guido Ferri.\n");
            System.exit(-2);
        }
        for(int i = 0; i < args.length && i<5; i++){
            if ("-ti".equals(args[i])) {
                try{
                    mini = InOut.readMini(args[i+1]);
                }
                catch(IOException ex){
                    System.out.println("ERRORE NEL FILE DI INPUT");
                    System.exit(-1);
                }
                setIn = true;
            }
            else if("-to".equals(args[i])){
                setOut = true;
                fileOut = args[i+1];
            }
            else if("-l".equals(args[i])){
               setLet = true;
            }
        }
        if (!setIn) {
            int p = (((setIn) ? 1 : 0) + ((setOut) ? 1 : 0))*2 + ((setLet) ? 1 : 0);
            mini = Arrays.copyOfRange(args, p, args.length);
            
        }
        try {
            result = QMcC.qmcc_simplify(mini);
        }
        catch (NumberFormatException ex){
            System.out.println("ERRORE, hai inserito caratteri non riconosciuti come numeri");
            System.exit(-2);
        }
        Collections.sort(result, new Comparator<byte[]>(){
            public int compare(byte[] a, byte[] b){
                for(int i = 0; i < a.length && i < b.length; i++){
                    if(a[i] != b[i]){
                        if(a[i] == 1 || b[i] == 2)
                            return -1;
                        else return 1;
                    }
                }
                if(a.length == b.length)
                    return 0;
                else if(a.length < b.length)
                    return -1;
                else return 1;
            }
        });
        if(result.get(0).length>26){
            setLet = false;
        } 
        if(setOut){
            try{
                InOut.writeImp(result, fileOut, setLet);
            }
            catch(IOException er){
                System.out.println("Error: I/O Exception");
                System.exit(-2);
            }
        }
        else {
            InOut.printImp(result, setLet);
        }
    }
}
