/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc.copertura;
import java.util.*;
/**
 *
 * @author Guido_Fe
 */
public class TabCopertura {
    private ArrayList<RigaCop> tab;
    private ArrayList<Integer> listAllMinT; //lista di tutti i mintermini presenti
    private ArrayList<byte[]> finalP;
    
    private boolean isEss(RigaCop P){
        boolean trovato = false;
        for(int i = 0; i<P.size() && !trovato; i++){
            boolean doppio = false;
            for(int j = 0; !doppio && j<tab.size(); j++){
                if(tab.get(j).getPIndex() != P.getPIndex() && tab.get(j).hasM(P.getM(i))){
                    doppio = true;
                }
            }
            trovato = !doppio;
        }
        return trovato;
    }
    
    private boolean isMDom(int m){
        boolean trovato = false;
        for(int i = 0; i < listAllMinT.size() && !trovato; i++){
            if(listAllMinT.get(i) != m && nPforM(m)>nPforM(listAllMinT.get(i))){
                boolean isCovered = true;
                for(int j = 0; j<tab.size() && isCovered; j++){
                    if(tab.get(j).hasM(listAllMinT.get(i)))
                        isCovered=tab.get(j).hasM(m);                    
                }
                if(isCovered) trovato = true;
            }
        }
        return trovato;
    }
    
    private boolean isPDominated(RigaCop P){
        boolean trovato = false;
        for(int i = 0; i < tab.size() && !trovato; i++){
            boolean uguali = true;
            if(tab.get(i).getPIndex() != P.getPIndex() && tab.get(i).size() > P.size()&& !trovato){
                for(int j = 0; j<P.size() && uguali; j++){
                    uguali = tab.get(i).hasM(P.getM(j));
                }
                if(uguali) trovato = true;
            }
        }
        return trovato;
    }
    
    private void delP(int pos){
        tab.remove(pos);
    }
    
    private void delM(int m){  //cancella i mintermini anche da listAllMinT
        for(int i = 0; i < tab.size(); i++)
            tab.get(i).delM(m);
        listAllMinT.remove(new Integer(m));
    }
    
    private void delMofP(int pos){
        while(tab.get(pos).size() != 0){
            int m = tab.get(pos).getM(0);
            for(int i = 0; i<tab.size(); i++)
                tab.get(i).delM(m);
        }
    }
    
    private void delVoidP(){
        for(int i = 0; i < tab.size(); i++)
            if(tab.get(i).isEmpty()){
                delP(i);
                i--;
            }
    }
    
    
    private int nPforM(int m){
        int tot = 0;
        for(int i = 0; i<tab.size(); i++)
            if(tab.get(i).hasM(m))
                tot++;
        return tot;
    }
    
    public boolean stepEss(){
        boolean done = false;       //Indica se è stato semplificato qualcosa
        for(int i = 0; i<tab.size() && !done; i++){
            if(isEss(tab.get(i))){
                delMofP(i);
                finalP.add(tab.get(i).getP());
                delP(i);
                done = true;
                delVoidP();
            }
            
        }
        return done;
    }
    
    public boolean stepRowDom(){
        boolean done = false;
        for(int i = 0; i < tab.size() && !done; i++){
            if(isPDominated(tab.get(i))){
                delP(i);
                done = true;
            }
        }
        return done;
    }
    
    public boolean stepColDom(){
        boolean done = false;
        for(int i = 0; i < listAllMinT.size(); i++){
            if(isMDom(listAllMinT.get(i))){
                delM(listAllMinT.get(i));
                listAllMinT.remove(i);
                delVoidP();
                done = true;
            }
        }
        return done;
    }
    
    public ArrayList<byte[]> copri(){
        boolean redo = false;
        do{
            boolean isBlocked = true;
            boolean s1, s2, s3;
            do{
                s1 = stepEss();
                if(s1) while(stepEss());
                s2 = stepRowDom();
                if(s2) while(stepRowDom());
                s3 = stepColDom();
                if(s3) while(stepRowDom());
                if(!(s1 || s2 || s3))
                    isBlocked = true;
            }while(!isBlocked && !listAllMinT.isEmpty());
            if(isBlocked){
                delMofP(0);
                finalP.add(tab.get(0).getP());
                delP(0);
                redo = true;
                delVoidP();
            }
            else redo = false;
        }while(redo);
        return finalP;
    }
}
