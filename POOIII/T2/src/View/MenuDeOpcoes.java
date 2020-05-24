package src.View;

import src.Nivel;
import src.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class MenuDeOpcoes extends JPanel {

    public JPanel getPanel(JPanel painelPrincipal, Tabuleiro tabuleiro, Nivel nivel, TelaDoJogo telaDoJogo, TelaDeFimDeJogo telaDeFimDeJogo) {
        this.setLayout(new GridLayout(3, 1));
        JButton inicioDeJogo = new JButton("Novo jogo");
        JButton nivelDeDificuldade = new JButton("Nível de dificuldade");
        JButton sairDoJogo = new JButton("Sair do jogo");
        this.add(inicioDeJogo);
        this.add(nivelDeDificuldade);
        this.add(sairDoJogo);
        sairDoJogo.addActionListener(e -> System.exit(0));

        nivelDeDificuldade.addActionListener(e -> {
            CardLayout layout = (CardLayout) (painelPrincipal.getLayout());
            layout.show(painelPrincipal, "1");
        });

        inicioDeJogo.addActionListener(e -> {
            tabuleiro.iniciarJogo(nivel);
            telaDoJogo.criarJogo(tabuleiro, painelPrincipal, nivel, telaDeFimDeJogo);
            CardLayout layout = (CardLayout) (painelPrincipal.getLayout());
            layout.show(painelPrincipal, "2");
        });

        return this;
    }
}
