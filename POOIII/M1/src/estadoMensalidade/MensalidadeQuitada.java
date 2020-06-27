package src.estadoMensalidade;

import src.Mensalidade;

public class MensalidadeQuitada implements MensalidadeState {

    private Mensalidade mensalidade;

    @Override
    public void quitar(Mensalidade mensalidade, double valorPago) {

        this.mensalidade = mensalidade;
    }

    @Override
    public boolean getPagarEnable() {
        return false;
    }

    @Override
    public boolean getValorPagoEnable() {
        return false;
    }

    @Override
    public String getJuros() {
        return String.valueOf(mensalidade.getJuros_sobre_o_valor());
    }
}
