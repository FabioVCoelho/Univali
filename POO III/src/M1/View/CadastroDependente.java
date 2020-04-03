package M1.View;

import M1.Clube;
import M1.Dependente;
import M1.Socio;

import javax.swing.*;
import java.awt.*;

public class CadastroDependente extends JPanel {

    JLabel nomeLabel = new JLabel("Nome: ");
    JTextField nomeText = new JTextField();
    JLabel emailLabel = new JLabel("E-mail: ");
    JTextField emailText = new JTextField();
    JLabel telefoneLabel = new JLabel("Telefone: ");
    JTextField telefoneText = new JTextField();
    JLabel errorMsg = new JLabel("");
    JButton cadastrar_Dependente = new JButton("Cadastrar");
    JButton voltar = new JButton("Voltar");
    Socio socio;
    private boolean atualizar = false;
    private Dependente dependente;
    private Clube clube;

    public JPanel getPanel(JPanel panels, AcaoSocio acaoSocio, Clube clube) {
        this.clube = clube;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        nomeText.setColumns(20);
        emailText.setColumns(20);
        telefoneText.setColumns(20);
        this.setMaximumSize(new Dimension(100, nomeLabel.getHeight()));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(nomeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.ipadx = 150;
        this.add(nomeText, constraints);
        constraints.ipadx = 0;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(emailLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.ipadx = 150;
        this.add(emailText, constraints);
        constraints.ipadx = 0;
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(telefoneLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.ipadx = 150;
        this.add(telefoneText, constraints);
        constraints.ipadx = 0;
        constraints.gridx = 0;
        constraints.gridy = 3;

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipadx = 0;
        constraints.gridx = 2;
        constraints.gridy = 5;
        this.add(cadastrar_Dependente, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipadx = 0;
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(voltar, constraints);

        cadastrar_Dependente.addActionListener(e -> {
            if (atualizar) {
                Dependente dependenteOld = this.dependente;
                this.dependente.setEmail(emailText.getText());
                this.dependente.setNome(nomeText.getText());
                this.dependente.setTelefone(telefoneText.getText());
                socio.atualizarDependente(dependenteOld, this.dependente);
            } else {
                Dependente dependente = new Dependente(nomeText.getText(), emailText.getText(), telefoneText.getText());
                socio.adicionarDependente(dependente);
            }
        });

        voltar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            acaoSocio.atualizarLista();
            acaoSocio.atualizarListaPessoas();
            layout.show(panels, "0");
        });

        return this;
    }

    public void setSocio(Long numeroCartao) {
        for (Socio socio : clube.consultaSocio()) {
            if (socio.getNumeroCartaoSocio().equals(numeroCartao)) {
                this.socio = socio;
            }
        }
    }

    public void cleanAll() {
        nomeText.setText("");
        emailText.setText("");
        telefoneText.setText("");
        cadastrar_Dependente.setText("Cadastrar");
        atualizar = false;
    }

    public void preencherCampos(Dependente dependente) {
        nomeText.setText(dependente.getNome());
        emailText.setText(dependente.getEmail());
        telefoneText.setText(dependente.getTelefone());
        atualizar = true;
        cadastrar_Dependente.setText("Atualizar");
        this.dependente = dependente;
    }

}
