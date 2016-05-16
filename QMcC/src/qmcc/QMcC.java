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
    public static ArrayList<byte[]> qmcc_simplify(String[] args)  throws NumberFormatException { //epansione + copertura
        TabEsp tab = new TabEsp();
        tab = TabEsp.expand(args);
        ArrayList<byte[]> result = new ArrayList<byte[]>();
        result = TabCopertura.copri(tab);
        return result;
    } 
    
}