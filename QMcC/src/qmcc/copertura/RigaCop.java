package qmcc.copertura;
import java.util.*;
/**
 *
 * @author Guido_Fe
 */
public class RigaCop {
    private final byte[] implicante; //implicante
    private ArrayList<Integer> listMinT; //mintermini coperti dall'implicante
    
    public RigaCop(int nImplicante, byte[] implicante, ArrayList<Integer> minT){
        this.implicante = implicante;
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
    
    public int size(){
        return listMinT.size();
    }
    public byte[] getP(){
        return implicante;
    }
}
