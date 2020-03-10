package questaoTres;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private float preco;
    private int tamanho;
    private List<Venda> vendas = new ArrayList<Venda>();

    public List<Venda> getVendas() {
        return vendas;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void vendido() {

    }
}
