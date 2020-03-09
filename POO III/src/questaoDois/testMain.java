package questaoDois;

import java.sql.Time;

public class testMain {
    public static void main(String[] args) {
        Ator keanu = new Ator("Keanu");
        Filme matrix = new Filme("Matrix", new Time(120));
        keanu.getAtuacoes().add(new Atua("Neo", keanu, matrix));
        for (Atua atuacao : keanu.getAtuacoes()) {
            System.out.println(atuacao.getPapel());
            System.out.println(atuacao.getAtor());
            System.out.println(atuacao.getFilme());
        }
    }
}
