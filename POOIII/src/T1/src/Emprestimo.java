package src;

import java.util.Date;

public class Emprestimo {
    protected Date dataEmprestimo;
    protected Date dataRetorno;
    protected boolean atraso = false;
    protected Usuario usuario;
    protected Material material;

    public Emprestimo(Date dataEmprestimo, Date dataRetorno, Material material, Usuario usuario) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataRetorno = dataRetorno;
        this.material = material;
        this.usuario = usuario;
        material.reservado(this);
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public boolean isAtraso() {
        return atraso;
    }

    public void setAtraso(boolean atraso) {
        this.atraso = atraso;
    }

    public Material getMaterial() {
        return this.material;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "dataEmprestimo=" + dataEmprestimo +
                ", dataRetorno=" + dataRetorno +
                ", atraso=" + atraso +
                '}';
    }

    public void devolucao() {
        if (new Date().after(this.dataRetorno)) {
            this.setAtraso(true);
        }
        this.setDataRetorno(new Date());
    }
}
