package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuario {
    protected String nome;
    protected String local;
    protected String telefone;
    protected String senha;
    protected List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(String nome, String local, String telefone, String senha) {
        this.nome = nome;
        this.local = local;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void emprestar(Material material1) throws MaterialJaEmprestadoException {
        if (!material1.estaReservado()) {
            emprestimos.add(new Emprestimo(new Date(), new DateUtil().daysFromNow(15), material1, this));
        } else {
            throw new MaterialJaEmprestadoException();
        }
    }

    public List<Material> getMaterialEmprestado() {
        List<Material> material = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            material.add(emprestimo.getMaterial());
        }
        return material;
    }

    public void reservar(Material material) {
        material.reservar(this);
    }

    public void devolverMaterial(Material material) {
        material.devolucao();
    }
}
