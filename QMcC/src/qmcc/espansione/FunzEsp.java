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
public class FunzEsp {
    public static void expand(String args []){
        TabEsp result = new TabEsp();
        TabEsp tabAtt = new TabEsp (args);
        TabEsp tabNext = new TabEsp();
        
        tabAtt.alignZero();
        tabAtt.printTab();
        
        
        //return result;
    }
    
}
