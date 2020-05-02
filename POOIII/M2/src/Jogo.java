package src;

import src.View.ControlePanel;

import javax.swing.*;

public class Jogo {
    public static void main(String[] args) {
        ControleJogo controle = new ControleJogo();
        controle.iniciarJogo();

        JFrame principal = new JFrame();
        principal.setSize(1200, 800);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ControlePanel panel = new ControlePanel(controle);
        principal.add(panel.getPanel());
        principal.setVisible(true);
    }
}
