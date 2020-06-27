package src;

import src.estadoMensalidade.MensalidadeAberta;
import src.estadoMensalidade.MensalidadeState;

import java.util.Date;

public class Mensalidade {

    Date data_vencimento;
    Date data_pagamento;
    Double valor;
    MensalidadeState quitada;
    Double valor_pago;
    Double juros_sobre_o_valor;

    public Mensalidade(Date data_vencimento, Date data_pagamento, Double valor, Double valor_pago, Double juros_sobre_o_valor) {
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.quitada = new MensalidadeAberta();
        this.valor_pago = valor_pago;
        this.juros_sobre_o_valor = juros_sobre_o_valor;

    }

    public Double getJuros_sobre_o_valor() {
        return juros_sobre_o_valor;
    }

    public void setJuros_sobre_o_valor(Double juros_sobre_o_valor) {
        this.juros_sobre_o_valor = juros_sobre_o_valor;
    }

    public String consulta() {
        return this.toString();
    }

    public void quitar(double valorPago) {
        quitada.quitar(this, valorPago);
        this.valor_pago = valorPago;
        this.data_pagamento = new Date();
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public MensalidadeState getQuitada() {
        return quitada;
    }

    public Double getValor_pago() {
        return valor_pago;
    }

    public void setState(MensalidadeState newState) {
        quitada = newState;
    }

    @Override
    public String toString() {
        return "Mensalidade{" +
                "data_vencimento=" + data_vencimento +
                ", data_pagamento=" + data_pagamento +
                ", valor=" + valor +
                ", quitada=" + quitada +
                ", valor_pago=" + valor_pago +
                ", juros_sobre_o_valor=" + juros_sobre_o_valor +
                '}';
    }

}
