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
    private ArrayList<Integer> listAllMinT; //lista di tutti i mintermini presenti. Byte > 2 solo per distinguere gli implicanti tra loro
    private ArrayList<byte[]> finalP;
    
//    public TabCopertura(){  //PROVVISORIO
//        tab = new ArrayList<RigaCop>(){{
//        }};
//        listAllMinT = new ArrayList<Integer>(){{
//            add(0); add(1);/* add(6); add(7); add(8); add(11); add(13);*/
//        }};
//        finalP = new ArrayList();
//    }
    
    private boolean isEss(RigaCop P){
        boolean trovato = false;
        for(int i = 0; i<P.size() && !trovato; i++){
            boolean doppio = false;
            for(int j = 0; !doppio && j<tab.size(); j++){
                if(j!=i && tab.get(j).hasM(P.getM(i))){
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
    
    private boolean isPDominated(int index){
        boolean trovato = false;
        for(int i = 0; i < tab.size() && !trovato; i++){
            boolean uguali = true;
            if(i!=index && tab.get(i).size() > tab.get(index).size()){
                for(int j = 0; j<tab.get(index).size() && uguali; j++){
                    uguali = tab.get(i).hasM(tab.get(index).getM(j));
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
        while(!tab.get(pos).isEmpty()){
            delM(tab.get(pos).getM(0));
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
                System.out.println("Essential- P: " + Arrays.toString(tab.get(i).getP()));
                delMofP(i);
                finalP.add(tab.get(i).getP());
                delP(i);
                done = true;
                delVoidP();
                printTab(); //DEBUG
            }
            
        }
        return done;
    }
    
    public boolean stepRowDom(){
        boolean done = false;
        for(int i = 0; i < tab.size() && !done; i++){
            if(isPDominated(i)){
                System.out.println("RowDominated - P: " + Arrays.toString(tab.get(i).getP())); //DEBUG
                delP(i);
                done = true;
                printTab(); //DEBUG
            }
        }
        return done;
    }
    
    public boolean stepColDom(){
        boolean done = false;
        for(int i = 0; i < listAllMinT.size() && !false; i++){
            if(isMDom(listAllMinT.get(i))){
                System.out.println("ColDom - M: " + listAllMinT.get(i)); //DEBUG
                delM(listAllMinT.get(i));
                delVoidP();
                done = true;
                printTab();
            }
        }
        return done;
    }
    
    public ArrayList<byte[]> copri(){
        boolean redo = false;
        do{
            boolean isBlocked = false;
            boolean s1, s2, s3;
            do{
                s1 = stepEss();
                if(s1) while(stepEss());
                s2 = stepRowDom();
                if(s2) while(stepRowDom());
                s3 = stepColDom();
                if(s3) while(stepColDom());
                if(!(s1 || s2 || s3))
                    isBlocked = true;
            }while(!isBlocked && !listAllMinT.isEmpty());
            if(isBlocked && !tab.isEmpty()){
                System.out.println("Blocked ");
                delMofP(0);
                finalP.add(tab.get(0).getP());
                delP(0);
                redo = true;
                delVoidP();
                printTab();
            }
            else redo = false;
        }while(redo);
        return finalP;
    }
    
    public void printTab(){
        System.out.print("         \t");
        for(int i = 0; i<listAllMinT.size(); i++){
            System.out.print(listAllMinT.get(i)+"\t");
        }
        System.out.println();
        for(int i = 0; i<tab.size(); i++){
            System.out.print(Arrays.toString(tab.get(i).getP()) + "\t");
            for(int j = 0; j<listAllMinT.size(); j++){
                if(tab.get(i).hasM(listAllMinT.get(j)))
                    System.out.print("X\t");
                else System.out.print(".\t");
            }
            System.out.println();
        }
        for(int i = 0; i<=listAllMinT.size(); i++)
            System.out.print("________");
        System.out.println();
    }
}
