/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.compiladores.semantico;

/**
 * @author biankatpas
 */
public class First {


    static public final RecoverySet header = new RecoverySet();
    static public final RecoverySet declaration = new RecoverySet();
    static public final RecoverySet body = new RecoverySet();
    static public final RecoverySet comentario = new RecoverySet();

    static {
        header.add(new Integer(langX.PROGRAM));
        declaration.add(new Integer(langX.DECLARE));
        body.add(new Integer(langX.EXECUTE));
        comentario.add(new Integer(langX.PURPOSE));
        comentario.add(new Integer(langX.EOF));
    }
}
