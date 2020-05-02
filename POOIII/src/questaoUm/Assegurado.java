package questaoUm;

public class Assegurado extends Paciente {

    private String numSeguro;
    private String nomeSeguradora;

    public Assegurado(String nome, String numSeguro, String nomeSeguradora) {
        super(nome);
        this.numSeguro = numSeguro;
        this.nomeSeguradora = nomeSeguradora;
    }

    public String getNomeSeguradora() {
        return nomeSeguradora;
    }

    public void setNomeSeguradora(String nomeSeguradora) {
        this.nomeSeguradora = nomeSeguradora;
    }

    public String getNumSeguro() {
        return numSeguro;
    }

    public void setNumSeguro(String numSeguro) {
        this.numSeguro = numSeguro;
    }
}
