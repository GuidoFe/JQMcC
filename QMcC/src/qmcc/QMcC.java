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
    
    public ArrayList<byte[]> copri(TabEsp in){ //DA FINIRE
        TabCopertura tab = new TabCopertura(in);
        boolean redo = false;
        do{
            boolean isBlocked = false;
            boolean s1, s2, s3;
            do{
                s1 = tab.stepEss();
                if(s1) while(tab.stepEss());
                s2 = tab.stepRowDom();
                if(s2) while(tab.stepRowDom());
                s3 = tab.stepColDom();
                if(s3) while(tab.stepColDom());
                if(!(s1 || s2 || s3))
                    isBlocked = true;
            }while(!isBlocked && !tab.getListAllMinT().isEmpty());
            if(isBlocked && !tab.isEmpty()){
                System.out.println("Blocked ");
                tab.delMofP(0);
                tab.addFinalP(tab.getRow(0).getP());
                tab.delP(0);
                redo = true;
                tab.delVoidP();
                tab.printTab();
            }
            else redo = false;
        }while(redo);
        return tab.getFinalP();
    }
    
}