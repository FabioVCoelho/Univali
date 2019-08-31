/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.topicosespecias;

/**
 *
 * @author Fabio
 * CREATE TABLE USUARIO (Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,CODIGO_SEGURANCA INT, DATA_VALIDADE VARCHAR(255),
 * LOGIN VARCHAR(255),NUMERO_CARTAO VARCHAR(255), SENHA VARCHAR(255),PRIMARY KEY (Id));
 */
public class User {
    private String login;
    private int codigoSeguranca;
    private String dataValidade;
    private String numeroCartao;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setCodigoSeguranca(int codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getLogin() {
        return login;
    }

    public int getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
