package src.View;

import src.Clube;
import src.Pessoa;
import src.Socio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AcaoSocio extends JPanel {
    DefaultListModel<Socio> defaultListModel = new DefaultListModel<>();
    DefaultListModel<Pessoa> defaultListModelPessoas = new DefaultListModel<>();
    JList<Socio> listaSocios = new JList<Socio>(defaultListModel);
    JList<Pessoa> listaPessoasJList = new JList<Pessoa>(defaultListModelPessoas);
    JButton solicitacaoDeSocio = new JButton("Solicitar Assossiação");
    JButton adicionarSocio = new JButton("Aceitar Solicitação");
    JButton removerSocio = new JButton("Remover Sócio");
    JButton atualizarSocio = new JButton("Atualizar Sócio");
    JButton quitarMensalidade = new JButton("Quitar mensalidade Sócio");
    JButton gerarMensalidade = new JButton("Gerar Mensalidade");
    JButton listarDependetes = new JButton("Listar Dependentes");

    Clube clube;

    public JPanel getPanel(JPanel panels, SolicitarAssossiacao cadastroSocio, ListarMensalidadeSocio listarMensalidadeSocio, ListarDependenteSocio listarDependenteSocio, Clube clube) {
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(2, 4));
        this.clube = clube;
        botoes.add(solicitacaoDeSocio);
        botoes.add(adicionarSocio);
        botoes.add(atualizarSocio);
        botoes.add(removerSocio);
        botoes.add(gerarMensalidade);
        botoes.add(quitarMensalidade);
        botoes.add(listarDependetes);
        this.add(botoes);
        JPanel listaSocio = new JPanel();
        listaSocio.setLayout(new GridLayout(0, 1));
        listaSocio.add(new JLabel("Lista de Socios:"));
        listaSocio.add(listaSocios);
        this.add(listaSocio);
        JPanel listaPessoas = new JPanel();
        listaPessoas.setLayout(new GridLayout(0, 1));
        listaPessoas.add(new JLabel("Pessoas para avaliar assossiação:"));
        listaPessoas.add(listaPessoasJList);
        this.add(listaPessoas);

        solicitacaoDeSocio.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            cadastroSocio.cleanAll();
            layout.show(panels, "1");
        });

        adicionarSocio.addActionListener(e -> {
            clube.aceitarSolicitacao(listaPessoasJList.getSelectedValue());
            atualizarLista();
            atualizarListaPessoas();
        });

        removerSocio.addActionListener(e -> {
            clube.removerSocio(listaSocios.getSelectedValue());
            atualizarLista();
        });

        atualizarSocio.addActionListener(e -> {
            cadastroSocio.preencherCampos(listaSocios.getSelectedValue());
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "1");
        });

        gerarMensalidade.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "3");
        });

        quitarMensalidade.addActionListener(e -> {
            listarMensalidadeSocio.setSocio(listaSocios.getSelectedValue());
            listarMensalidadeSocio.atualizarLista();
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "4");
        });

        listarDependetes.addActionListener(e -> {
            listarDependenteSocio.setSocio(listaSocios.getSelectedValue());
            listarDependenteSocio.atualizarLista();
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "6");
        });

        return this;
    }

    public void atualizarLista() {
        defaultListModel.clear();
        List<Socio> objs = clube.consultaSocio();
        for (Socio obj : objs) {
            defaultListModel.addElement(obj);
        }
    }

    public void atualizarListaPessoas() {
        defaultListModelPessoas.clear();
        List<Pessoa> objs = clube.consultaCandidato();
        for (Pessoa obj : objs) {
            defaultListModelPessoas.addElement(obj);
        }
    }

}
