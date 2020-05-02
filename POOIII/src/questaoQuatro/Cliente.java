package questaoQuatro;

public class Cliente {
    protected String id;
    protected String nome;
    protected FichaCliente fichaCliente;

    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public FichaCliente getFichaCliente() {
        return fichaCliente;
    }

    public void setFichaCliente(FichaCliente fichaCliente) {
        this.fichaCliente = fichaCliente;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}