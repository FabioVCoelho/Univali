package questaoTres;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private float comissao;
    private List<Venda> vendas = new ArrayList<Venda>();

    public List<Venda> getVendas() {
        return vendas;
    }

    public float getComissao() {
        return comissao;
    }

    public void setComissao(float comissao) {
        this.comissao = comissao;
    }

    public void vende() {

    }
}
