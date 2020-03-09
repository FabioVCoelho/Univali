package questaoDois;

import java.util.ArrayList;
import java.util.List;

public class Ator {
    private String nome;
    private List<Atua> atuacoes = new ArrayList<Atua>();

    Ator(String nome) {
        this.nome = nome;
    }

    public List<Atua> getAtuacoes() {
        return atuacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
