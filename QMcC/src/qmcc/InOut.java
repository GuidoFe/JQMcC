/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcello
 */
package qmcc;

import java.io.*;
import java.util.*;

public class InOut {
    public static String [] readMini(String filename) throws IOException{
        ArrayList <String> mini = new ArrayList (0);
        String [] miniArr = new String [0];
        FileReader fr = new FileReader(filename);
        BufferedReader input = new BufferedReader(fr);        
        String miniT = input.readLine();
        while (miniT != null){
            miniT = miniT.trim();
            mini.add(miniT);
            miniT = input.readLine();
        }
        input.close();
        miniArr = mini.toArray(miniArr);
        return miniArr;
    }
    
    public static void printImp(ArrayList<byte[]> list, boolean formatted){
        if(!formatted || list.get(0).length >26){
            if(formatted)
                System.out.println("Note: number of literals too large to use alphabet representation.");
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list.get(i).length; j++){
                    if(list.get(i)[j] == 2)
                        System.out.print("-");
                    else System.out.print(list.get(i)[j]);
                }
                System.out.println();
            }
        }
        else
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list.get(i).length; j++){
                    if(list.get(i)[j] != 2){
                        char c =(char)(j + 97);
                        System.out.print(c);
                        if(list.get(i)[j] == 0)
                            System.out.print("'");
                    }
                }
                if(i != list.size() -1)
                    System.out.print(" + ");
            }            
    }
    
    public static void writeImp(ArrayList<byte[]> list, String path, boolean formatted) throws IOException{
        FileWriter fw = new FileWriter(path);
        PrintWriter pw = new PrintWriter(fw);
        if(!formatted){
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list.get(i).length; j++){
                    if(list.get(i)[j] == 2)
                        pw.print("-");
                    else pw.print(list.get(i)[j]);
                }
                pw.println();
            }
        }
        else
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list.get(i).length; j++){
                    if(list.get(i)[j] != 2){
                        char c =(char)(j + 97);
                        pw.print(c);
                        if(list.get(i)[j] == 0)
                            pw.print("'");
                    }
                }
                if(i != list.size() -1)
                    pw.print(" + ");
            }
        pw.close();
    }    
    
}
