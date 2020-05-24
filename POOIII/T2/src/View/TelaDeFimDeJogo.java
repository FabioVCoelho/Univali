package src.View;

import src.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class TelaDeFimDeJogo extends JPanel {
    JLabel jogadas;
    JLabel cronometoLabel;
    JLabel mensagemDeFimDeJogo;

    public JPanel getPanel(ControleVisual painelPrincipal) {
        this.setLayout(new GridLayout(1, 4));
        JButton voltar = new JButton("Tente novamente");
        jogadas = new JLabel();
        cronometoLabel = new JLabel();
        mensagemDeFimDeJogo = new JLabel("");

        this.add(jogadas);
        this.add(cronometoLabel);
        this.add(mensagemDeFimDeJogo);
        this.add(voltar);


        voltar.addActionListener(e4 -> {
            CardLayout layout = (CardLayout) (painelPrincipal.getLayout());
            layout.show(painelPrincipal, "0");
        });
        return this;
    }

    public void setText(Tabuleiro tabuleiro) {
        jogadas.setText("Numero de jogadas: " + tabuleiro.obterNumeroDeJogadas());
        cronometoLabel.setText("Tempo: " + tabuleiro.obterTempo());
        if (tabuleiro.obterTempo() != null && tabuleiro.obterTempo() < 0) {
            mensagemDeFimDeJogo.setText("Você perdeu, tente novamente");
        } else {
            mensagemDeFimDeJogo.setText("Parabéns, você venceu");
        }

    }
}
