package src;

public class ControleJogo {
    protected Pessoa pessoa;
    protected Palavra palavra;
    protected String palavraSecreta;

    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    public void iniciarJogo() {
        pessoa = new Pessoa();
        palavra = new Palavra();
        palavraSecreta = palavra.getPalavraSecreta();
    }

    public void jogadorChutou(String letra) {
        if (palavra.possuiALetra(letra)) {
            palavraSecreta = palavra.getPalavraSecreta();
        } else {
            pessoa.mostrarUmMembro();
        }
    }

    public boolean fimDeJogo() {
        return palavra.isCompleta() || pessoa.perdeuTodosOsMembros();
    }

    public String getLetrasJaUsada() {
        return palavra.getLetrasReveladas();
    }

    public MembrosPessoa[] getPessoaMembros() {
        return pessoa.getMembros();
    }

    public String getFimDeJogoMensagem() {
        if (palavra.isCompleta()) {
            return "Parabéns, você venceu!";
        } else {
            return "Perdeu, tente novamente";
        }
    }
}
