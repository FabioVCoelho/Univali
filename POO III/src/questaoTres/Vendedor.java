package questaoTres;

public class Vendedor {

    private float comissao = 0;

    public Vendedor(float comissao) {
        this.comissao = comissao;
    }

    public Vendedor() {
    }

    public float getComissao() {
        return comissao;
    }

    public void vende() {
        System.out.println("Vendedor recebeu comissão pela venda");
        comissao += 10;
    }
}
