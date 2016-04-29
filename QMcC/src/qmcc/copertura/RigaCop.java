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
public class RigaCop {
    private final int nImplicante; //indice dell'implicante P (es P1 = 1)
    private ArrayList<Integer> listMinT;
    
    public RigaCop(int nImplicante, ArrayList<Integer> minT){
        this.nImplicante = nImplicante;
        this.listMinT = minT;
    }
    public int getM(int index){
        return listMinT.get(index);
    }
    
    public void delM(int mintermine){
        listMinT.removeIf(e->(e==mintermine));
    }
    
    public boolean hasM(int mintermine){
        return listMinT.contains(mintermine);
    }
    
    public boolean isEmpty(){
        return listMinT.isEmpty();
    }
}
