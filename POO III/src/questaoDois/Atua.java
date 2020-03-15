package questaoDois;

public class Atua {
    private String papel;
    private Ator ator;
    private Filme filme;

    public Atua(String papel, Ator ator, Filme filme) {
        this.papel = papel;
        this.ator = ator;
        this.filme = filme;
    }

    public String getPapel() {
        return papel;
    }

    public String getAtor() {
        return ator.getNome();
    }

    public String getFilme() {
        return filme.getTitulo();
    }
}
