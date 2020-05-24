package src.View;

import src.Nivel;
import src.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class ControleVisual extends JPanel {

    public JPanel getPanel() {
        Nivel nivel = new Nivel();
        Tabuleiro tabuleiro = new Tabuleiro();
        this.setLayout(new CardLayout());
        MenuDeOpcoes menuDeOpcoes = new MenuDeOpcoes();
        MenuDeDificuldade menuDeDificuldade = new MenuDeDificuldade();
        TelaDoJogo telaDoJogo = new TelaDoJogo();
        TelaDeFimDeJogo telaDeFimDeJogo = new TelaDeFimDeJogo();

        JPanel telaDoJogoPanel = telaDoJogo.getPanel();
        JPanel menuDeOpcoesPanel = menuDeOpcoes.getPanel(this, tabuleiro, nivel, telaDoJogo, telaDeFimDeJogo);
        JPanel menuDeDificuldadePanel = menuDeDificuldade.getPanel(this, nivel);
        JPanel telaDeFimDeJogoPanel = telaDeFimDeJogo.getPanel(this);

        this.add(menuDeOpcoesPanel, "0");
        this.add(menuDeDificuldadePanel, "1");
        this.add(telaDoJogoPanel, "2");
        this.add(telaDeFimDeJogoPanel, "3");

        return this;
    }
}
