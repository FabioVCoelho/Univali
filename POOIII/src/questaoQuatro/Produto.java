package questaoQuatro;

public class Produto {
    protected String nome;
    protected Integer valor;

    public Produto(String nome, Integer valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}
