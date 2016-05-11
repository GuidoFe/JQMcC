/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmcc;

import java.util.ArrayList;

/**
 *
 * @author Guido_Fe
 */
public class Main {

    /**
     * @param args 
     */
    public static void main(String[] args) {
        ArrayList<byte[]> result = new ArrayList();
        result = QMcC.qmcc_simplify(args);
        QMcC.printImp(result, false);
    }
}
