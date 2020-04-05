package src;

import java.util.List;

public class Revista extends Material {
    protected String colecao;
    protected String editora;
    protected List<Artigo> artigos;

    public Revista(String codigo, String assunto, String titulo, String colecao, String editora, List<Artigo> artigo) throws RevistaSemArtigo {
        super(codigo, assunto, titulo);
        this.colecao = colecao;
        this.editora = editora;
        if (artigo.size() < 1) {
            throw new RevistaSemArtigo();
        }
        artigos = artigo;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public String getColecao() {
        return colecao;
    }

    public void setColecao(String colecao) {
        this.colecao = colecao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Revista{" +
                "colecao='" + colecao + '\'' +
                ", editora='" + editora + '\'' +
                ", artigos=" + artigos +
                ", codigo='" + codigo + '\'' +
                ", assunto='" + assunto + '\'' +
                ", titulo='" + titulo + '\'' +
                ", reservas=" + reservas +
                ", emprestimos=" + emprestimos +
                '}';
    }
}
