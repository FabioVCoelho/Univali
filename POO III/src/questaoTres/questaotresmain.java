package questaoTres;

public class questaotresmain {

    public static void main(String[] args) {
        // Comprador tentando comprar sem verba. Error
        Produto produto = new Produto(10, 200);
        Comprador comprador = new Comprador();
        Vendedor vendedor = new Vendedor();
        Venda venda = new Venda(comprador, vendedor, produto);
        venda.concretizaVenda();
        venda.cancelaVenda();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Comprador com verba
        Comprador compradorComVerba = new Comprador(100);
        Venda vendaOk = new Venda(compradorComVerba, vendedor, produto);
        vendaOk.concretizaVenda();
    }
}
