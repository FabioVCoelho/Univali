package src;

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {
    Integer tempo;
    Timer timer = new Timer();

    public boolean acabouOTempo() {
        return tempo <= 0;
    }

    public void iniciar(Nivel nivel) {
        tempo = nivel.obterTempoMaximo();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tempo--;
            }
        }, 0, 1000);
    }

    public void parar() {
        timer.cancel();
    }

    public Integer obterTempo() {
        return tempo;
    }
}
