package src.View;

import src.Nivel;

import javax.swing.*;
import java.awt.*;

public class MenuDeDificuldade extends JPanel {

    public JPanel getPanel(ControleVisual painelPrincipal, Nivel nivel) {
        this.setLayout(new GridLayout(3, 1));
        JButton facil = new JButton("Fácil");
        JButton normal = new JButton("Normal");
        JButton dificil = new JButton("Difícil");

        facil.addActionListener(e -> {
            nivel.alterarDificuldade(facil.getText());
            voltarParaOMenuDeOpcoes(painelPrincipal);
        });

        normal.addActionListener(e -> {
            nivel.alterarDificuldade(normal.getText());
            voltarParaOMenuDeOpcoes(painelPrincipal);
        });

        dificil.addActionListener(e -> {
            nivel.alterarDificuldade(dificil.getText());
            voltarParaOMenuDeOpcoes(painelPrincipal);
        });

        this.add(facil);
        this.add(normal);
        this.add(dificil);

        return this;
    }

    private void voltarParaOMenuDeOpcoes(ControleVisual painelPrincipal) {
        CardLayout layout = (CardLayout) (painelPrincipal.getLayout());
        layout.show(painelPrincipal, "0");
    }
}
