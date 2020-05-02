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
        return new ArrayList<>(atuacoes);
    }

    public void addAtuacao(String papel, Filme filme) {
        atuacoes.add(new Atua(papel, this, filme));
    }

    public String getNome() {
        return nome;
    }

}
