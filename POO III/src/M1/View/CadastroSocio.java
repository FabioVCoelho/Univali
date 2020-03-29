package M1.View;

import M1.Database;
import M1.Socio;

import javax.swing.*;
import java.awt.*;

public class CadastroSocio extends JPanel {
    JLabel nomeLabel = new JLabel("Nome: ");
    JTextField nomeText = new JTextField();
    JLabel emailLabel = new JLabel("E-mail: ");
    JTextField emailText = new JTextField();
    JLabel telefoneLabel = new JLabel("Telefone: ");
    JTextField telefoneText = new JTextField();
    JLabel cartaoSocioLabel = new JLabel("Numero cartão: ");
    JTextField cartaoNumeroText = new JTextField();
    JLabel errorMsg = new JLabel("");
    JButton cadastrar_Socio = new JButton("Cadastrar");
    JButton voltar = new JButton("Voltar");
    JButton cadastrar_dependente = new JButton("Cadastrar Dependente");
    Database database = Database.getInstance();
    private boolean atualizar = false;
    private Socio socio;

    public JPanel getPanel(JPanel panels, AcaoSocio acaoSocio) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        nomeText.setColumns(20);
        emailText.setColumns(20);
        telefoneText.setColumns(20);
        cartaoNumeroText.setColumns(20);
        cartaoNumeroText.setEnabled(false);
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
        this.add(cartaoSocioLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.ipadx = 150;
        this.add(cartaoNumeroText, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.ipadx = 150;
        this.add(errorMsg, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipadx = 0;
        constraints.gridx = 1;
        constraints.gridy = 5;
        this.add(cadastrar_dependente, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipadx = 0;
        constraints.gridx = 2;
        constraints.gridy = 5;
        this.add(cadastrar_Socio, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.ipadx = 0;
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(voltar, constraints);

        cadastrar_dependente.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            layout.show(panels, "2");
        });

        cadastrar_Socio.addActionListener(e -> {
            if (validarForm() && !atualizar) {
                Socio socio = new Socio(nomeText.getText(), emailText.getText(), telefoneText.getText());
                cartaoNumeroText.setText(socio.registrar().toString());
                cadastrar_Socio.setEnabled(false);
                errorMsg.setText("");
            } else if (atualizar) {
                Socio socioOld = this.socio;
                this.socio.setNome(nomeText.getText());
                this.socio.setEmail(emailText.getText());
                this.socio.setTelefone(telefoneText.getText());
                database.atualizarSocio(socioOld, this.socio);
            } else {
                errorMsg.setText("Erro no registro");
            }
        });

        voltar.addActionListener(e -> {
            CardLayout layout = (CardLayout) (panels.getLayout());
            acaoSocio.atualizarLista();
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
        cartaoNumeroText.setText("");
        cadastrar_Socio.setEnabled(true);
        cadastrar_Socio.setText("Cadastrar");
        atualizar = false;
    }

    public void preencherCampos(Socio socio) {
        nomeText.setText(socio.getNome());
        emailText.setText(socio.getEmail());
        telefoneText.setText(socio.getTelefone());
        cartaoNumeroText.setText(socio.getNumeroCartaoSocio().toString());
        atualizar = true;
        this.socio = socio;
        cadastrar_Socio.setEnabled(true);
        cadastrar_Socio.setText("Atualizar");
    }
}
