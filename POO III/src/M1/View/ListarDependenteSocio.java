package M1.View;

import M1.Dependente;
import M1.Socio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListarDependenteSocio extends JPanel {
    DefaultListModel defaultListModel = new DefaultListModel();
    JList listaDependente = new JList(defaultListModel);
    Socio socio;
    JButton voltar = new JButton("Voltar");
    JButton remover = new JButton("Remover");
    JButton atualizar = new JButton("Atualizar");
    JButton cadastrar = new JButton("Cadastrar");

    public JPanel getPanel(JPanel panels, AcaoSocio acaoSocio, CadastroDependente cadastroDependente) {
        this.setLayout(new FlowLayout());
        this.add(listaDependente);
        this.add(voltar);
        this.add(atualizar);
        this.add(cadastrar);
        this.add(remover);

        atualizar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            cadastroDependente.preencherCampos((Dependente) listaDependente.getSelectedValue());
            layout.show(panels, "2");
        });

        cadastrar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            cadastroDependente.cleanAll();
            cadastroDependente.setSocio(this.socio.getNumeroCartaoSocio());
            layout.show(panels, "2");
        });

        remover.addActionListener(e -> {
            socio.removerDependente((Dependente) listaDependente.getSelectedValue());
            atualizarLista();
        });

        voltar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            acaoSocio.atualizarLista();
            layout.show(panels, "0");
        });

        return this;
    }

    public void atualizarLista() {
        defaultListModel.clear();
        List<Dependente> objs = socio.getDependetes();
        for (int i = 0; i < objs.size(); i++) {
            defaultListModel.addElement(objs.get(i));
        }
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

}
