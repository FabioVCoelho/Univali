package questaoQuatro;

public class FichaCliente {
    private Cliente cliente;
    private Compra compra;

    public FichaCliente(Cliente cliente, Compra compra) {
        this.cliente = cliente;
        this.compra = compra;
    }

    public void inicia() {
        System.out.println("Iniciei a Compra");
    }

    public boolean finaliza() {
        compra.realizarCompra();
        return true;
    }
}