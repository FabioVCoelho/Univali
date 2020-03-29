package M1.View;

import M1.Database;
import M1.Socio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AcaoSocio extends JPanel {
    Database database = Database.getInstance();
    DefaultListModel defaultListModel = new DefaultListModel();
    JList listaSocios = new JList(defaultListModel);
    JButton adicionarSocio = new JButton("Adicionar Sócio");
    JButton removerSocio = new JButton("Remover Sócio");
    JButton atualizarSocio = new JButton("Atualizar Sócio");
    JButton quitarMensalidade = new JButton("Quitar mensalidade Sócio");
    JButton gerarMensalidade = new JButton("Gerar Mensalidade");
    JButton listarDependetes = new JButton("Listar Dependentes");

    public JPanel getPanel(JPanel panels, CadastroSocio cadastroSocio, ListarMensalidadeSocio listarMensalidadeSocio, ListarDependenteSocio listarDependenteSocio) {
        this.add(adicionarSocio);
        this.add(atualizarSocio);
        this.add(removerSocio);
        this.add(gerarMensalidade);
        this.add(quitarMensalidade);
        this.add(listarDependetes);
        this.add(listaSocios);

        adicionarSocio.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            cadastroSocio.cleanAll();
            layout.show(panels, "1");
        });

        removerSocio.addActionListener(e -> {
            database.removerSocio((Socio) listaSocios.getSelectedValue());
            atualizarLista();
        });

        atualizarSocio.addActionListener(e -> {
            cadastroSocio.preencherCampos((Socio) listaSocios.getSelectedValue());
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "1");
        });

        gerarMensalidade.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "3");
        });

        quitarMensalidade.addActionListener(e -> {
            listarMensalidadeSocio.setSocio((Socio) listaSocios.getSelectedValue());
            listarMensalidadeSocio.atualizarLista();
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "4");
        });

        listarDependetes.addActionListener(e -> {
            listarDependenteSocio.setSocio((Socio) listaSocios.getSelectedValue());
            listarDependenteSocio.atualizarLista();
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "6");
        });

        return this;
    }

    public void atualizarLista() {
        defaultListModel.clear();
        List<Socio> objs = database.consultaSocio();
        for (int i = 0; i < objs.size(); i++) {
            defaultListModel.addElement(objs.get(i));
        }
    }
}
