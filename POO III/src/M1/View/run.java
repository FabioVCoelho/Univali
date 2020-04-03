package M1.View;

import M1.Clube;

import javax.swing.*;
import java.awt.*;

public class run {
    public static void main(String[] args) {
        Clube clube = new Clube("AABB");
        AcaoSocio acaoSocio = new AcaoSocio();
        SolicitarAssossiacao solicitarAssossiacao = new SolicitarAssossiacao();
        CadastroDependente cadastroDependente = new CadastroDependente();
        GerarMensalidadeSocio mensalidadeSocio = new GerarMensalidadeSocio();
        ListarMensalidadeSocio listarMensalidadeSocio = new ListarMensalidadeSocio();
        QuitarMensalidade quitarMensalidade = new QuitarMensalidade();
        ListarDependenteSocio listarDependenteSocio = new ListarDependenteSocio();

        JFrame principal = new JFrame();
        principal.setSize(700, 300);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panels = new JPanel(new CardLayout());
        JPanel socioCRUDPanel = acaoSocio.getPanel(panels, solicitarAssossiacao, listarMensalidadeSocio, listarDependenteSocio, clube);
        JPanel cadastroSocioPanel = solicitarAssossiacao.getPanel(panels, acaoSocio, clube);
        JPanel cadastroDependentePanel = cadastroDependente.getPanel(panels, acaoSocio, clube);
        JPanel gerarMensalidadeSocio = mensalidadeSocio.getPanel(panels, acaoSocio, clube);
        JPanel listarMensalideSocio = listarMensalidadeSocio.getPanel(panels, acaoSocio, quitarMensalidade);
        JPanel quitarMensalidadePanel = quitarMensalidade.getPanel(panels);
        JPanel listarDependenteSocioPanel = listarDependenteSocio.getPanel(panels, acaoSocio, cadastroDependente);

        panels.add(socioCRUDPanel, "0");
        panels.add(cadastroSocioPanel, "1");
        panels.add(cadastroDependentePanel, "2");
        panels.add(gerarMensalidadeSocio, "3");
        panels.add(listarMensalideSocio, "4");
        panels.add(quitarMensalidadePanel, "5");
        panels.add(listarDependenteSocioPanel, "6");

        principal.add(panels);
        principal.setVisible(true);
    }


}