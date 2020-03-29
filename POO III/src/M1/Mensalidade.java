package M1;

import java.util.Date;

public class Mensalidade {
    Date data_vencimento;
    Date data_pagamento;
    Double valor;
    Boolean quitada;
    Double valor_pago;
    Double juros_sobre_o_valor;

    public Mensalidade(Date data_vencimento, Date data_pagamento, Double valor, Boolean quitada, Double valor_pago, Double juros_sobre_o_valor) {
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.quitada = quitada;
        this.valor_pago = valor_pago;
        this.juros_sobre_o_valor = juros_sobre_o_valor;
    }

    public String consulta() {
        return this.toString();
    }

    public double calcularJuros() {
        if (data_pagamento != null && data_pagamento.after(data_vencimento)) {
            return 100;
        }
        return 0;
    }

    public void quitar(double valorPago) {
        this.quitada = true;
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

    public Boolean getQuitada() {
        return quitada;
    }

    public Double getValor_pago() {
        return valor_pago;
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
