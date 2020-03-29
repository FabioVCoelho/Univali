package M1;

import java.util.Date;

public class Mensalidade {
    Date data_vencimento;
    Date data_pagamento;
    Double valor;
    Boolean quitada;
    Double valor_pago;
    Double juros_sobre_o_valor;

    public String consulta() {
        return this.toString();
    }

    public double calcularJuros() {
        if (data_pagamento.after(data_vencimento)) {
            return 100;
        }
        return 0;
    }

    public void quitar() {
        this.quitada = true;
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
