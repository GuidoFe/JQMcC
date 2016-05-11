/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc.copertura;
import java.util.*;
import qmcc.espansione.TabEsp;
/**
 *
 * @author Guido_Fe
 */
public class TabCopertura {
    private ArrayList<RigaCop> tab;
    private ArrayList<Integer> listAllMinT; //lista di tutti i mintermini presenti. Byte > 2 solo per distinguere gli implicanti tra loro
    private ArrayList<byte[]> finalP;
//    private ArrayList<String> nomiLett;
    
    public TabCopertura(TabEsp esp){
        tab = new ArrayList<RigaCop>();
        listAllMinT = new ArrayList<Integer>();
        finalP = new ArrayList<byte[]>();
        for(int i = 0; i < esp.getLength(); i++){
            tab.add(new RigaCop(i, esp.getCode(i), esp.getMini(i)));
            for(int j = 0; j < esp.getMini(i).size(); j++){
                if(!listAllMinT.contains(esp.getMini(i).get(j)))
                    listAllMinT.add(esp.getMini(i).get(j));
            }
        }
        listAllMinT.sort(null);
    }
    
    public ArrayList<Integer> getListAllMinT(){
        return listAllMinT;
    }
    
    public void addFinalP(byte[] word){
        finalP.add(word);
    }
    
    public ArrayList<byte[]> getFinalP(){
        return finalP;
    }
    
    public RigaCop getRow(int i){
        return tab.get(i);
    }
    
    public boolean isEmpty(){
        return tab.isEmpty();
    }
    
    public boolean isEss(RigaCop P){
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
    
    public boolean isMDom(int m){
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
    
    public boolean isPDominated(int index){
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
    
    public void delP(int pos){
        tab.remove(pos);
    }
    
    public void delM(int m){  //cancella i mintermini anche da listAllMinT
        for(int i = 0; i < tab.size(); i++)
            tab.get(i).delM(m);
        listAllMinT.remove(new Integer(m));
    }
    
    public void delMofP(int pos){
        while(!tab.get(pos).isEmpty()){
            delM(tab.get(pos).getM(0));
        }
    }
    
    public void delVoidP(){
        for(int i = 0; i < tab.size(); i++)
            if(tab.get(i).isEmpty()){
                delP(i);
                i--;
            }
    }
    
    
    public int nPforM(int m){
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
                //System.out.println("Essential- P: " + Arrays.toString(tab.get(i).getP()));
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
            if(isPDominated(i)){
                //System.out.println("RowDominated - P: " + Arrays.toString(tab.get(i).getP())); //DEBUG
                delP(i);
                done = true;
            }
        }
        return done;
    }
    
    public boolean stepColDom(){
        boolean done = false;
        for(int i = 0; i < listAllMinT.size() && !false; i++){
            if(isMDom(listAllMinT.get(i))){
                //System.out.println("ColDom - M: " + listAllMinT.get(i)); //DEBUG
                delM(listAllMinT.get(i));
                delVoidP();
                done = true;
            }
        }
        return done;
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
    public static ArrayList<byte[]> copri(TabEsp in){ //DA FINIRE
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
            }while(!isBlocked && !tab.isEmpty());
            if(isBlocked && !tab.isEmpty()){
                //System.out.println("Blocked "); DEBUG
                tab.delMofP(0);
                tab.addFinalP(tab.getRow(0).getP());
                tab.delP(0);
                redo = true;
                tab.delVoidP();
            }
            else redo = false;
        }while(redo);
        return tab.getFinalP();
    }
}
