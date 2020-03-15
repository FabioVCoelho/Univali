package questaoQuatro;

public class Pagamento {
    protected Compra compra;
    protected TipoPagamento tipoPagamento;
    protected Integer valor;

    public Pagamento(TipoPagamento tipoPagamento, Integer valor) {
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
    }

    public void setCompra(Compra compra) {
        if (this.compra == null)
            this.compra = compra;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public Integer getValor() {
        return valor;
    }

}