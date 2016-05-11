/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc;
import qmcc.copertura.TabCopertura;
import qmcc.espansione.TabEsp;
import java.util.*;
/**
 *
 * @author Guido_Fe
 * @author Marcello
 */
public class QMcC {
    public static ArrayList<byte[]> qmcc_simplify(String[] args){
        TabEsp tab = new TabEsp();
        tab = TabEsp.expand(args);
        ArrayList<byte[]> result = new ArrayList<byte[]>();
        result = TabCopertura.copri(tab);
        return result;
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
}