package src.View;

import javax.swing.*;

public class main {

    public static void main(String[] args) {
        JFrame principal = new JFrame();
        principal.setSize(1200, 800);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ControleVisual panel = new ControleVisual();
        principal.add(panel.getPanel());
        principal.setVisible(true);
    }

}
