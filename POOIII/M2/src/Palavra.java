package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        } catch (IOException e) {
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

    private String carregarPalavraSecreta() throws IOException {
        if (getClass().getClassLoader().getResource("words.txt") == null) {
            throw new FileNotFoundException("Não encontrou o arquivo");
        }
        List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().getResource("words.txt").getPath()));
        Random random = new Random();
        return lines.get(random.nextInt(lines.size()));
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
