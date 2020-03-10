package questaoTres;

import java.util.ArrayList;
import java.util.List;

public class Comprador {
    private float verba;
    private List<Venda> compras = new ArrayList<Venda>();

    public List<Venda> getCompras() {
        return compras;
    }

    public float getVerba() {
        return verba;
    }

    public void setVerba(float verba) {
        this.verba = verba;
    }

    public void compra() {

    }

}
