package questaoUm;

public class NaoAssegurado extends Paciente {
    private Integer valorConsulta;
    private Integer codBanco;
    private String numCheque;

    public NaoAssegurado(String nome, Integer valorConsulta, Integer codBanco, String numCheque) {
        super(nome);
        this.valorConsulta = valorConsulta;
        this.codBanco = codBanco;
        this.numCheque = numCheque;
    }

    public Integer getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(Integer valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public Integer getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(Integer codBanco) {
        this.codBanco = codBanco;
    }

    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }
}
