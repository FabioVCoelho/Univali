package src.View;

import src.ControleJogo;

import javax.swing.*;
import java.awt.*;

public class ControlePanel extends JPanel {
    private final ControleJogo controle;
    protected PessoaPanel pessoaPanel;
    protected JogadorPanel jogadorPanel;

    public ControlePanel(ControleJogo controle) {
        this.controle = controle;
    }

    public JPanel getPanel() {
        pessoaPanel = new PessoaPanel();
        jogadorPanel = new JogadorPanel(controle, this);

        this.setLayout(new GridLayout(1, 1));
        this.add(pessoaPanel.getPanel());
        this.add(jogadorPanel.getPanel());
        atualizarLabels();
        return this;
    }

    public void atualizarLabels() {
        jogadorPanel.setPalavraChave(controle.getPalavraSecreta());
        jogadorPanel.setLetrasJaUsadas(controle.getLetrasJaUsada());
        pessoaPanel.setPessoaDraw(controle.getPessoaMembros(), pessoaPanel.getGraphics());
        if (controle.fimDeJogo()) {
            String fimDeJogo = controle.getFimDeJogoMensagem();
            JOptionPane.showMessageDialog(null, fimDeJogo);
            jogadorPanel.desabilitarTudo();
        }
    }

}
