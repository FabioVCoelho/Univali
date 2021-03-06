package src.View;

import src.Mensalidade;
import src.Socio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListarMensalidadeSocio extends JPanel {
    JButton voltar = new JButton("Voltar");
    JButton pagar = new JButton("Pagar");

    DefaultListModel<Mensalidade> defaultListModel = new DefaultListModel<>();
    JList<Mensalidade> listaMensalidade = new JList<>(defaultListModel);

    private Socio socio;

    public JPanel getPanel(JPanel panels, AcaoSocio acaoSocio, QuitarMensalidade quitarMensalidade) {
        this.setLayout(new FlowLayout());
        this.add(listaMensalidade);
        this.add(voltar);
        this.add(pagar);

        voltar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            acaoSocio.atualizarLista();
            layout.show(panels, "0");
        });

        pagar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            quitarMensalidade.setMensalidade(listaMensalidade.getSelectedValue());
            quitarMensalidade.setSocio(this.socio);
            layout.show(panels, "5");
        });

        return this;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void atualizarLista() {
        defaultListModel.clear();
        List<Mensalidade> objs = socio.getMensalidades();
        for (Mensalidade obj : objs) {
            defaultListModel.addElement(obj);
        }
    }


}
