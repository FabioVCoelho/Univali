package questaoQuatro;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    protected Compra compra;
    protected List<Produto> produtos = new ArrayList<Produto>();

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        if (this.compra == null)
            this.compra = compra;
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }
}