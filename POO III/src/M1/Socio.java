package M1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Socio extends Pessoa {
    Long numeroCartaoSocio;
    List<Mensalidade> mensalidadeList = new ArrayList<>();
    List<Dependente> dependenteList = new ArrayList<>();
    Database database = Database.getInstance();

    public Socio(String nome, String email, String telefone) {
        super(nome, email, telefone);
        database.adicionarSocio(this);
    }

    public Long getNumeroCartaoSocio() {
        return numeroCartaoSocio;
    }

    public void pagarMensalidade() {

    }

    public Long registrar() {
        database.removerSocio(this);
        this.numeroCartaoSocio = Math.abs(new Random().nextLong());
        database.adicionarSocio(this);
        return this.numeroCartaoSocio;
    }

    public String consultar() {
        return this.toString();
    }

    public String atualizar() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "Socio{" +
                "numeroCartaoSocio=" + numeroCartaoSocio +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}