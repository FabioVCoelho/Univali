package src.View;

import src.ControleJogo;

import javax.swing.*;
import java.awt.*;

public class JogadorPanel extends JPanel {
    private final ControleJogo controle;
    private final ControlePanel controlePanel;
    JLabel palavraSecretaLabel = new JLabel("");
    JTextField letraChuteJogador = new JTextField("");
    JButton chutar = new JButton("Enviar");
    JLabel letrasJaChutadas = new JLabel("");

    public JogadorPanel(ControleJogo controle, ControlePanel controlePanel) {
        this.controle = controle;
        this.controlePanel = controlePanel;
    }

    public JPanel getPanel() {
        this.setLayout(new GridLayout(3, 1));
        this.add(palavraSecretaLabel);
        JPanel chutarPanel = new JPanel();
        chutarPanel.setLayout(new GridLayout(1, 2));
        chutarPanel.add(letraChuteJogador);
        chutarPanel.add(chutar);
        this.add(chutarPanel);
        this.add(letrasJaChutadas);
        palavraSecretaLabel.setFont(new Font(palavraSecretaLabel.getFont().getName(), Font.PLAIN, 28));
        letrasJaChutadas.setFont(new Font(letrasJaChutadas.getFont().getName(), Font.PLAIN, 28));
        chutar.addActionListener(e -> {
            controle.jogadorChutou(letraChuteJogador.getText());
            controlePanel.atualizarLabels();
            letraChuteJogador.setText("");
        });

        return this;
    }

    public void setPalavraChave(String palavraSecreta) {
        palavraSecretaLabel.setText(palavraSecreta);
    }

    public void setLetrasJaUsadas(String letrasJaUsada) {
        letrasJaChutadas.setText(letrasJaUsada);
    }

    public void desabilitarTudo() {
        chutar.setEnabled(false);
        letraChuteJogador.setEnabled(false);
    }
}
