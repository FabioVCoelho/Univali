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

    public void adicionarDependente(Dependente dependente) {
        dependenteList.add(dependente);
        dependente.registrar(this);
    }

    @Override
    public String toString() {
        return "Socio{" +
                "numeroCartaoSocio=" + numeroCartaoSocio +
                ", dependenteList=" + dependenteListToString() +
                ", mensalidadeList=" + mensalidadeListToString() +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    private String mensalidadeListToString() {
        StringBuilder mensalidadeStr = new StringBuilder();
        for (Mensalidade mensalidade : mensalidadeList) {
            mensalidadeStr.append(mensalidade.toString());
        }
        return mensalidadeStr.toString();
    }

    private String dependenteListToString() {
        StringBuilder dependentes = new StringBuilder();
        for (Dependente dependente : dependenteList) {
            dependentes.append(dependente.toString());
        }
        return dependentes.toString();
    }

    public void registrarMensalidade(Mensalidade mensalidade) {
        mensalidadeList.add(mensalidade);
    }

    public List<Mensalidade> getMensalidades() {
        return mensalidadeList;
    }

    public List<Dependente> getDependetes() {
        return dependenteList;
    }

    public void removerDependente(Dependente dependente) {
        dependenteList.remove(dependente);
    }

    public void atualizarDependente(Dependente dependenteOld, Dependente dependente) {
        removerDependente(dependenteOld);
        adicionarDependente(dependente);
    }
}