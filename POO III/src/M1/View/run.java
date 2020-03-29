package M1.View;

import javax.swing.*;
import java.awt.*;

public class run {
    public static void main(String[] args) {
        AcaoSocio acaoSocio = new AcaoSocio();
        CadastroSocio cadastroSocio = new CadastroSocio();

        JFrame principal = new JFrame();
        principal.setSize(500, 300);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panels = new JPanel(new CardLayout());
        JPanel socioCRUDPanel = acaoSocio.getPanel(panels, cadastroSocio);
        JPanel cadastroSocioPanel = cadastroSocio.getPanel(panels, acaoSocio);

        panels.add(socioCRUDPanel, "0");
        panels.add(cadastroSocioPanel, "1");

        principal.add(panels);
        principal.setVisible(true);
    }


}