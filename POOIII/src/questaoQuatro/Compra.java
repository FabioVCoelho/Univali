package questaoQuatro;

import java.util.ArrayList;
import java.util.List;

public class Compra {

    protected List<Pedido> pedidos = new ArrayList<Pedido>();
    protected Integer valorTotal = 0;
    protected List<Pagamento> pagamentos = new ArrayList<Pagamento>();
    protected FichaCliente fichaCliente;

    public FichaCliente getFichaCliente() {
        return fichaCliente;
    }

    public void setFichaCliente(FichaCliente fichaCliente) {
        this.fichaCliente = fichaCliente;
    }

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
        System.out.println("Compra realizada com sucesso no valor total de " + getValorTotal());
        System.out.println("Formas de pagamentos");
        getFormasDePagamento();
    }

    public void addPagamento(Pagamento pagamento) {
        pagamento.setCompra(this);
        pagamentos.add(pagamento);
    }

    public void addPedido(Pedido pedido) {
        pedido.setCompra(this);
        pedidos.add(pedido);
    }

    public void removePedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    public void removePagamento(Pagamento pagamento) {
        pagamentos.remove(pagamento);
    }

    public void getFormasDePagamento() {
        for (Pagamento pagamento : this.getPagamentos()) {
            System.out.println(pagamento.getTipoPagamento());
            System.out.println("Valor de " + pagamento.getValor());
        }
    }

}