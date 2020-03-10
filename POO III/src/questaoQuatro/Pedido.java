package questaoQuatro;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Produto> produtos = new ArrayList<Produto>();

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