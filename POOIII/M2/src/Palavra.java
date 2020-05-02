package src;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Palavra {
    String palavra;
    String palavraSecreta;
    List<Character> letrasReveladas;

    public Palavra() {
        letrasReveladas = new ArrayList<>();
        try {
            palavraSecreta = carregarPalavraSecreta();
        } catch (Exception e) {
            System.out.println("Não encontrou o arquivo");
            System.exit(0);
        }
    }

    public String getLetrasReveladas() {
        return letrasReveladas.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private String esconderLetras(String palavraSecreta) {
        StringBuilder palavraEscondida = new StringBuilder();
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (letrasReveladas.contains(palavraSecreta.charAt(i))) {
                palavraEscondida.append(palavraSecreta.charAt(i));
            } else {
                palavraEscondida.append("_");
            }
        }
        return palavraEscondida.toString();
    }

    private String carregarPalavraSecreta() throws Exception {
        InputStream wordstxtIS = getClass().getClassLoader().getResourceAsStream("words.txt");
        if (wordstxtIS == null) {
            throw new Exception("Não encontrou o arquivo");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(wordstxtIS));
        int lines = 0;
        while (reader.ready()) {
            lines++;
            reader.readLine();
        }
        Random random = new Random();
        int randomValue = random.nextInt(lines);
        wordstxtIS = getClass().getClassLoader().getResourceAsStream("words.txt");
        reader = new BufferedReader(new InputStreamReader(wordstxtIS));
        for (int i = 0; i < randomValue; i++) {
            reader.readLine();
        }
        return reader.readLine();
    }

    public boolean possuiALetra(String letra) {
        letrasReveladas.add(letra.charAt(0));
        return palavraSecreta.contains(letra);
    }

    public String getPalavraSecreta() {
        palavra = esconderLetras(palavraSecreta);
        return palavra;
    }

    public boolean isCompleta() {
        return palavra.equals(palavraSecreta);
    }
}
