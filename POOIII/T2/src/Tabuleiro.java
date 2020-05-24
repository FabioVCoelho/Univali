package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tabuleiro {
    protected Cronometro cronometro;
    protected List<Carta> cartas = new ArrayList<>();
    protected Carta jogada = null;
    protected Integer numeroDeJogadas;
    private boolean fimDeJogo;

    private List<Carta> carregarCartas(Nivel nivel) {
        Integer quantidadeDeCartas = nivel.obterQuantidadeDeCartas();
        List<Carta> listaDeCartas = new ArrayList<>();
        for (int i = 0; i < quantidadeDeCartas; i++) {
            listaDeCartas.add(new Carta(i));
        }
        return listaDeCartas;
    }

    public void iniciarJogo(Nivel nivel) {
        cronometro = new Cronometro();
        cartas = carregarCartas(nivel);
        Collections.shuffle(cartas);
        numeroDeJogadas = 0;
        fimDeJogo = false;
    }

    public int obterQuantidadeDeCarta() {
        return this.cartas.size();
    }

    public Carta obterCarta(int i) {
        return this.cartas.get(i);
    }

    public void cartaSelecionada(Carta carta) {
        if (carta.estaVirada() || fimDeJogo) {
            return;
        }
        carta.virar();
        if (jogada == null) {
            jogada = carta;
        } else {
            if (!carta.getId().equals(jogada.getId())) {
                carta.desvirar();
                jogada.desvirar();
            }
            jogada = null;
            numeroDeJogadas++;
        }

        fimDeJogo = jogoFinalizado();
    }

    public boolean jogoFinalizado() {
        return cartas.stream().allMatch(Carta::estaVirada) || cronometro.acabouOTempo();
    }

    public Integer obterNumeroDeJogadas() {
        return numeroDeJogadas;
    }

    public List<Carta> obterCartas() {
        return new ArrayList<>(cartas);
    }

    public Integer obterTempo() {
        return cronometro.obterTempo();
    }

    public void iniciarCronometro(Nivel nivel) {
        cronometro.iniciar(nivel);
    }

    public void pararCronometro() {
        cronometro.parar();
    }
}
