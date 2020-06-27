package src.estadoMensalidade;

import src.Mensalidade;

public interface MensalidadeState {
    void quitar(Mensalidade mensalidade, double valorPago);

    boolean getPagarEnable();

    boolean getValorPagoEnable();

    String getJuros();
}
