/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.compiladores.semantico;


/**
 * @author biankatpas
 */
public class Instruction {

    private Integer pointer;
    private String code;
    private Item parameter;

    public Instruction(Integer pointer, String code, Item parameter) {
        this.pointer = pointer;
        this.code = code;
        this.parameter = parameter;
    }

    public Integer getPointer() {
        return pointer;
    }

    public void setPointer(int Integer) {
        this.pointer = pointer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Item getParameter() {
        return parameter;
    }

    public void setParameter(Item parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "{pointer=" + pointer + ", code='" + code + '\'' + ", parameter=" + parameter.toString() + '}';
    }
}
