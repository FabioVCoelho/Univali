package src.View;

import src.Mensalidade;
import src.Socio;

import javax.swing.*;
import java.awt.*;

public class QuitarMensalidade extends JPanel {
    JLabel dataVencimentoLabel = new JLabel("Data Vencimento: ");
    JLabel dataPagamentoLabel = new JLabel("Data Pagamento: ");
    JLabel valorAPagarLabel = new JLabel("Valor da fatura: ");
    JLabel quitadaLabel = new JLabel("Quitada: ");
    JLabel valorPagoLabel = new JLabel("Valor pago: ");
    JLabel jurosSobreOValorLabel = new JLabel("Juros: ");

    JLabel dataVencimento = new JLabel("");
    JLabel dataPagamento = new JLabel("");
    JLabel valorAPagar = new JLabel("");
    JLabel quitada = new JLabel("");
    JTextField valorPago = new JTextField();
    JLabel jurosSobreOValor = new JLabel("");

    JButton voltar = new JButton("Voltar");
    JButton pagar = new JButton("Pagar");
    private Mensalidade mensalidade;
    private Socio socio;

    public JPanel getPanel(JPanel panels) {
        this.setLayout(new GridLayout(0, 2));
        this.add(dataVencimentoLabel);
        this.add(dataVencimento);
        this.add(dataPagamentoLabel);
        this.add(dataPagamento);
        this.add(valorAPagarLabel);
        this.add(valorAPagar);
        this.add(quitadaLabel);
        this.add(quitada);
        this.add(valorPagoLabel);
        this.add(valorPago);
        valorPago.setColumns(20);
        this.add(jurosSobreOValorLabel);
        this.add(jurosSobreOValor);
        this.add(voltar);
        this.add(pagar);

        voltar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "4");
        });

        pagar.addActionListener(e -> {
            socio.pagarMensalidade(this.mensalidade, Double.parseDouble(valorPago.getText()));
            quitada.setText(this.mensalidade.getQuitada().toString());
            dataPagamento.setText(this.mensalidade.getData_pagamento().toString());
            valorPago.setEnabled(false);
            pagar.setEnabled(false);
        });

        return this;
    }

    public void setMensalidade(Mensalidade mensalidade) {
        this.mensalidade = mensalidade;
        valorAPagar.setText(mensalidade.getValor().toString());
        quitada.setText(mensalidade.getQuitada().toString());
        if (mensalidade.getData_pagamento() != null) {
            dataPagamento.setText(mensalidade.getData_pagamento().toString());
        }
        dataVencimento.setText(mensalidade.getData_vencimento().toString());
        jurosSobreOValor.setText(String.valueOf(mensalidade.calcularJuros()));
        if (mensalidade.getQuitada()) {
            valorPago.setEnabled(false);
            valorPago.setText(mensalidade.getValor_pago().toString());
            pagar.setEnabled(false);
        }
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }
}
