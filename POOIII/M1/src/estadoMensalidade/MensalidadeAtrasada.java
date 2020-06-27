package src.estadoMensalidade;

import src.Mensalidade;

public class MensalidadeAtrasada implements MensalidadeState {

    private Mensalidade mensalidade;

    @Override
    public void quitar(Mensalidade mensalidade, double valorPago) {
        this.mensalidade = mensalidade;
        if (mensalidade.getValor().equals(mensalidade.getValor_pago())) {
            mensalidade.setState(new MensalidadeQuitada());
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
        return String.valueOf(calcularJuros() - mensalidade.getValor());
    }

    public double calcularJuros() {
        return mensalidade.getValor() * 1.2;
    }

}
