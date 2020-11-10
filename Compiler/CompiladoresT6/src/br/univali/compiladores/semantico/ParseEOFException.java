/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.compiladores.semantico;

public class ParseEOFException extends ParseException {
    public ParseEOFException(final String sMessage) {
        super(sMessage);
    }
}