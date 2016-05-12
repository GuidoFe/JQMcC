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
            mini.add(miniT);
            miniT = input.readLine();
        }
        input.close();
        miniArr = mini.toArray(miniArr);
        return miniArr;
    }
    
}
