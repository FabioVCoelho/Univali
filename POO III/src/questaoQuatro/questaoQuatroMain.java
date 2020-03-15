package questaoQuatro;

public class questaoQuatroMain {
    public static void main(String[] args) {
        Produto produto = new Produto("Agua", 2);
        Cliente cliente = new Cliente("1", "Pedro");
        Compra compra = new Compra();
        FichaCliente fichaCliente = new FichaCliente(cliente, compra);
        fichaCliente.inicia();
        Pedido pedido = new Pedido();
        pedido.addProduto(produto);
        Pagamento pagamentoC = new Pagamento(TipoPagamento.CREDITO, 1);
        Pagamento pagamentoD = new Pagamento(TipoPagamento.DEBITO, 1);
        compra.addPagamento(pagamentoC);
        compra.addPagamento(pagamentoD);
        compra.addPedido(pedido);
        fichaCliente.finaliza();
    }
}
