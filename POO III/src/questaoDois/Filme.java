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

    public List<Atua> getAtuacoes() {
        return atuacoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Time getDuracao() {
        return duracao;
    }

    public void setDuracao(Time duracao) {
        this.duracao = duracao;
    }
}
