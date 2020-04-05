package src;

public class Livro extends Material {
    protected String editora;
    protected String edicao;
    protected String isbn;
    protected String autor;

    public Livro(String codigo, String assunto, String titulo, String editora, String edicao, String isbn, String autor) {
        super(codigo, assunto, titulo);
        this.editora = editora;
        this.edicao = edicao;
        this.isbn = isbn;
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "editora='" + editora + '\'' +
                ", edicao='" + edicao + '\'' +
                ", isbn='" + isbn + '\'' +
                ", autor='" + autor + '\'' +
                ", codigo='" + codigo + '\'' +
                ", assunto='" + assunto + '\'' +
                ", titulo='" + titulo + '\'' +
                ", reservas=" + reservas +
                ", emprestimos=" + emprestimos +
                '}';
    }
}
