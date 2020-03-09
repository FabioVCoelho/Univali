package questaoTres;

public class Venda {
    Comprador comprador;
    Vendedor vendedor;
    Produto produto;

    public Venda(Comprador comprador, Vendedor vendedor, Produto produto) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.produto = produto;
    }

    public void concretizaVenda() {
    }

    public void cancelaVenda() {

    }
}
