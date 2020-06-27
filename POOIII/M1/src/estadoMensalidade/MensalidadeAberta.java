package src.estadoMensalidade;

import src.Mensalidade;

import java.util.Date;

public class MensalidadeAberta implements MensalidadeState {

    @Override
    public void quitar(Mensalidade mensalidade, double valorPago) {
        if (new Date().after(mensalidade.getData_vencimento())) {
            mensalidade.setState(new MensalidadeAtrasada());
            mensalidade.quitar(valorPago);
        }
        if (mensalidade.getValor() == valorPago) {
            mensalidade.setState(new MensalidadeQuitada());
            mensalidade.quitar(valorPago);
        }
    }

    @Override
    public boolean getPagarEnable() {
        return true;
    }

    @Override
    public boolean getValorPagoEnable() {
        return true;
    }

    @Override
    public String getJuros() {
        return "0";
    }
}
