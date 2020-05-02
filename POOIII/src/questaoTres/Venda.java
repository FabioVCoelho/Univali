package questaoTres;

public class Venda {

    private Comprador comprador;
    private Vendedor vendedor;
    private Produto produto;

    public Venda(Comprador comprador, Vendedor vendedor, Produto produto) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.produto = produto;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void concretizaVenda() {
        try {
            comprador.compra();
            vendedor.vende();
            produto.vendido();
        } catch (ExceptionNotEnoughMoney exceptionNotEnoughMoney) {
            exceptionNotEnoughMoney.printStackTrace();
        }
    }

    public void cancelaVenda() {
        this.comprador = null;
        this.vendedor = null;
        this.produto = null;
    }
}
