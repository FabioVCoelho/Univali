package src.View;

import src.Clube;
import src.Socio;

import javax.swing.*;
import java.awt.*;

public class SolicitarAssossiacao extends JPanel {
    JLabel nomeLabel = new JLabel("Nome: ");
    JTextField nomeText = new JTextField();
    JLabel emailLabel = new JLabel("E-mail: ");
    JTextField emailText = new JTextField();
    JLabel telefoneLabel = new JLabel("Telefone: ");
    JTextField telefoneText = new JTextField();
    JLabel errorMsg = new JLabel("");
    JButton solicitar_pedido = new JButton("Cadastrar");
    JButton voltar = new JButton("Voltar");
    private boolean atualizar = false;
    private Socio socio;

    public JPanel getPanel(JPanel panels, AcaoSocio acaoSocio, Clube clube) {
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
        this.add(errorMsg, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipadx = 0;
        constraints.gridx = 2;
        constraints.gridy = 5;
        this.add(solicitar_pedido, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipadx = 0;
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(voltar, constraints);

        solicitar_pedido.addActionListener(e -> {
            if (validarForm() && !atualizar) {
                clube.solicitarAssossiacao(nomeText.getText(), telefoneText.getText(), emailText.getText());
                solicitar_pedido.setEnabled(false);
                errorMsg.setText("");
            } else if (atualizar) {
                Socio socioOld = this.socio;
                this.socio.setNome(nomeText.getText());
                this.socio.setEmail(emailText.getText());
                this.socio.setTelefone(telefoneText.getText());
                clube.atualizarSocio(socioOld, this.socio);
            } else {
                errorMsg.setText("Erro no registro");
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

    private boolean validarForm() {
        if (nomeText.getText().length() < 4) {
            return false;
        }
        if (!emailText.getText().contains("@") && emailText.getText().length() < 10) return false;
        return telefoneText.getText().length() >= 8;
    }

    public void cleanAll() {
        nomeText.setText("");
        emailText.setText("");
        telefoneText.setText("");
        solicitar_pedido.setEnabled(true);
        solicitar_pedido.setText("Cadastrar");
        atualizar = false;
    }

    public void preencherCampos(Socio socio) {
        nomeText.setText(socio.getNome());
        emailText.setText(socio.getEmail());
        telefoneText.setText(socio.getTelefone());
        atualizar = true;
        this.socio = socio;
        solicitar_pedido.setEnabled(true);
        solicitar_pedido.setText("Atualizar");
    }
}
