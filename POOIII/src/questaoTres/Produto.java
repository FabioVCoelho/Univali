package questaoTres;

public class Produto {

    private float preco;
    private int tamanho;

    public Produto(float preco, int tamanho) {
        this.preco = preco;
        this.tamanho = tamanho;
    }

    public float getPreco() {
        return preco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void vendido() {
        System.out.println("Produto vendido");
    }
}
