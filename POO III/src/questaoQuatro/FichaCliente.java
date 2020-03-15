package questaoQuatro;

public class FichaCliente {
    protected Cliente cliente;
    protected Compra compra;

    public FichaCliente(Cliente cliente, Compra compra) {
        this.cliente = cliente;
        cliente.setFichaCliente(this);
        this.compra = compra;
        compra.setFichaCliente(this);
    }

    public void inicia() {
        System.out.println("Iniciei a Compra");
    }

    public boolean finaliza() {
        compra.realizarCompra();
        System.out.println("Compra Finalizada.");
        return true;
    }
}