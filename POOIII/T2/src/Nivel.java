package src;

public class Nivel {
    protected String dificuldade = "Fácil";

    public Integer obterQuantidadeDeCartas() {
        if (dificuldade.equals("Normal"))
            return 30;
        if (dificuldade.equals("Difícil"))
            return 40;
        return 20;
    }

    public void alterarDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Integer obterTempoMaximo() {
        if (dificuldade.equals("Normal"))
            return 120;
        if (dificuldade.equals("Difícil"))
            return 70;
        return 120;
    }
}
