package src;

public class Artigo {
    protected String autor;
    protected String tituloArtigo;

    public Artigo(String autor, String tituloArtigo) {
        this.autor = autor;
        this.tituloArtigo = tituloArtigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTituloArtigo() {
        return tituloArtigo;
    }

    public void setTituloArtigo(String tituloArtigo) {
        this.tituloArtigo = tituloArtigo;
    }

    @Override
    public String toString() {
        return "Artigo{" +
                "autor='" + autor + '\'' +
                ", tituloArtigo='" + tituloArtigo + '\'' +
                '}';
    }
}
