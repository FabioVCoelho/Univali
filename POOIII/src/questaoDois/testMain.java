package questaoDois;

import java.sql.Time;

public class testMain {
    public static void main(String[] args) {
        Ator keanu = new Ator("Keanu Reeves");
        Ator laurence = new Ator("Laurence Fishburne");
        Filme matrix = new Filme("Matrix", new Time(120));
        Filme johnWich = new Filme("Jonh Wick", new Time(100));
        matrix.adicionaAtuacao("Morpheus", laurence);
        matrix.adicionaAtuacao("Neo", keanu);
        keanu.addAtuacao("John Wick", johnWich);
        keanu.addAtuacao("Neo", matrix);

        System.out.println("Atuações do Keanu Reeves");
        for (Atua atuacao : keanu.getAtuacoes()) {
            System.out.println("Filme em que atuou: " + atuacao.getFilme());
            System.out.println("Papel no filme: " + atuacao.getPapel());
        }

        System.out.println("Elenco do filme Matrix");
        for (Atua eleco : matrix.getElenco()) {
            System.out.println("Ator: " + eleco.getAtor());
            System.out.println("Papel no filme: " + eleco.getPapel());
        }
    }
}
