/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc;
import qmcc.copertura.TabCopertura;
import java.util.*;
/**
 *
 * @author Guido_Fe
 */
public class QMcC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TabCopertura tab = new TabCopertura();
        tab.printTab();
        ArrayList<byte[]> result = tab.copri();
        for(int i = 0; i<result.size(); i++){
            System.out.print("[");
            for(int j = 0; j< result.get(i).length; j++){
                System.out.print(result.get(i)[j] + ", ");
            }
            System.out.print("], ");
        }
    }
    
}
