package src.View;

import src.Carta;
import src.Nivel;
import src.Tabuleiro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TelaDoJogo extends JPanel {

    List<JButton> cartas;
    java.util.Timer timer = new Timer();

    public JPanel getPanel() {
        return this;
    }

    public void criarJogo(Tabuleiro tabuleiro, JPanel painelPrincipal, Nivel nivel, TelaDeFimDeJogo telaDeFimDeJogo) {
        cartas = new ArrayList<>();
        this.removeAll();
        int numeroDeCartas = tabuleiro.obterQuantidadeDeCarta();
        this.setLayout(new GridLayout(6, 4));
        JLabel jogadas = new JLabel("Numero de jogadas: ");
        JLabel cronometoLabel = new JLabel("Tempo: ");
        JLabel tempo = new JLabel("120 segundos");
        JButton voltar = new JButton("Voltar");

        voltar.addActionListener(e4 -> {
            CardLayout layout = (CardLayout) (painelPrincipal.getLayout());
            layout.show(painelPrincipal, "0");
        });

        this.add(jogadas);
        this.add(cronometoLabel);
        this.add(tempo);
        this.add(voltar);

        for (int i = 0; i < numeroDeCartas; i++) {
            JButton cartaJButton = new JButton();
            Carta carta = tabuleiro.obterCarta(i);
            cartaJButton.setIcon(new ImageIcon(carta.getImagem().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
            cartaJButton.addActionListener(e -> {
                cartaJButton.setIcon(new ImageIcon(carta.obterImagemDaCarta().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    tabuleiro.cartaSelecionada(carta);
                    atualizarJogo(tabuleiro);
                    jogadas.setText("Numero de jogadas: " + tabuleiro.obterNumeroDeJogadas());
                    if (tabuleiro.jogoFinalizado()) {
                        telaDeFimDeJogo.setText(tabuleiro);
                        CardLayout layout = (CardLayout) (painelPrincipal.getLayout());
                        layout.show(painelPrincipal, "3");
                    }
                });
                thread.start();
            });
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (tabuleiro.obterTempo() == null) {
                        tempo.setText("120 segundos");
                    } else {
                        tempo.setText(tabuleiro.obterTempo() + " segundos");
                    }
                }
            }, 0, 1000);

            cartaJButton.setOpaque(true);
            this.add(cartaJButton);
            cartas.add(cartaJButton);
        }

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            for (Carta carta : tabuleiro.obterCartas()) {
                carta.desvirar();
            }
            atualizarJogo(tabuleiro);
            tabuleiro.iniciarCronometro(nivel);
        });
        thread.start();
    }

    private void atualizarJogo(Tabuleiro tabuleiro) {
        for (int i = 0; i < cartas.size(); i++) {
            Carta cartaTabuleiro = tabuleiro.obterCarta(i);
            cartas.get(i).setIcon(new ImageIcon(cartaTabuleiro.getImagem().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        }
    }
}
