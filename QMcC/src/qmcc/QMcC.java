/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc;
import qmcc.copertura.TabCopertura;
import qmcc.espansione.TabEsp;
import java.util.*;
import java.io.*;
/**
 *
 * @author Guido_Fe
 * @author Marcello
 */
public class QMcC {
    public static ArrayList<byte[]> qmcc_simplify(String[] args)  throws NumberFormatException {
        TabEsp tab = new TabEsp();
        tab = TabEsp.expand(args);
        ArrayList<byte[]> result = new ArrayList<byte[]>();
        result = TabCopertura.copri(tab);
        return result;
    } 
    
}