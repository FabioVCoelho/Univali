package questaoQuatro;

import java.util.ArrayList;
import java.util.List;

public class Compra {
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    private Integer valorTotal = 0;
    private List<Pagamento> pagamentos = new ArrayList<Pagamento>();

    public List<Pagamento> getPagamentos() {
        return new ArrayList<>(pagamentos);
    }

    public List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }

    public Integer getValorTotal() {
        valorTotal = 0;
        for (Pedido pedido : pedidos) {
            for (Produto produto : pedido.getProdutos()) {
                valorTotal += produto.getValor();
            }
        }
        return valorTotal;
    }

    public void realizarCompra() {
        System.out.println("Compra realizada com sucesso");
    }

    public void addPagamento(Pagamento pagamento) {
        pagamentos.add(pagamento);
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public void removePagamento(Pagamento pagamento) {
        pagamentos.remove(pagamento);
    }

}