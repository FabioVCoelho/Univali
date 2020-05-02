package questaoTres;

public class Comprador {

    private float verba;

    public Comprador(float verba) {
        this.verba = verba;
    }

    public Comprador() {
    }

    public float getVerba() {
        return verba;
    }

    public void compra() throws ExceptionNotEnoughMoney {
        if (verba - 10 < 0) {
            System.out.println("Comprador não possui verba para comprar");
            throw new ExceptionNotEnoughMoney();
        }
        System.out.println("Comprador perdeu verba na compra");
        verba -= 10;
    }

}
