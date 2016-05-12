/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/**
 *
 * @author Guido_Fe
 */
public class Main {

    /**
     * @param args 
     */
    public static void main(String[] args) {
       String [] mini;
       boolean setIn = false;
       boolean setOut = false;
       boolean setLet = false;
       String fileOut = new String();
       ArrayList<byte[]> result = new ArrayList();
//        boolean stopRead = false;
//        for(int i = 0; i < args.length && !stopRead; i++){
//            if("-ti".equals(args[i]))
//        }
        for(int i = 0; i < args.length && i<5; i++){
            if ("-ti".equals(args[i])) {
                mini = InOut.readMini(args[i+1]);
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
        result = QMcC.qmcc_simplify(mini);       
        if(result.get(0).length>26){
            setLet = false;
        } 
        if(setOut){
            try{
                QMcC.writeImp(result, fileOut, setLet);
            }
            catch(IOException er){
                System.out.println("I/O Exception");
            }
        }
        else {
            QMcC.printImp(result, setLet);
        }
    }
}
