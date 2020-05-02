package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Material {
    protected String codigo;
    protected String assunto;
    protected String titulo;
    protected List<Reserva> reservas = new ArrayList<>();
    protected List<Emprestimo> emprestimos = new ArrayList<>();

    public Material(String codigo, String assunto, String titulo) {
        this.codigo = codigo;
        this.assunto = assunto;
        this.titulo = titulo;
    }

    public List<Reserva> getDataDeReserva() {
        return reservas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean estaReservado() {
        return reservas.size() > 0;
    }

    public void reservado(Emprestimo emprestimo) {
        this.reservas.add(new Reserva(new Date(), new DateUtil().daysFromNow(15)));
        emprestimos.add(emprestimo);
    }

    @Override
    public String toString() {
        return "Material{" +
                "codigo='" + codigo + '\'' +
                ", assunto='" + assunto + '\'' +
                ", titulo='" + titulo + '\'' +
                ", reservas=" + reservas +
                ", emprestimos=" + emprestimos +
                '}';
    }

    public void reservar(Usuario usuario) {
        if (reservas.size() > 0) {
            Reserva reserva = this.getDataDeReserva().get(reservas.size() - 1);
            reservas.add(new Reserva(reserva.getDataValidade(), new DateUtil().daysFrom(reserva.getDataValidade(), 10)));
        } else {
            reservas.add(new Reserva(new Date(), new DateUtil().daysFromNow(15)));
        }
    }

    public void devolucao() {
        reservas.remove(reservas.get(0));
        emprestimos.get(0).devolucao();
    }
}
