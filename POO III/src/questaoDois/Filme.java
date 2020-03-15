package questaoDois;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Filme {
    private String titulo;
    private Time duracao;
    private List<Atua> atuacoes = new ArrayList<Atua>();

    public Filme(String titulo, Time duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public List<Atua> getElenco() {
        return new ArrayList<>(atuacoes);
    }

    public void adicionaAtuacao(String papel, Ator ator) {
        atuacoes.add(new Atua(papel, ator, this));
    }

    public String getTitulo() {
        return titulo;
    }

    public Time getDuracao() {
        return duracao;
    }

}
