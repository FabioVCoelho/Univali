package src;

import java.util.Date;

public class Reserva {
    Date dataReserva;
    Date dataValidade;

    public Reserva(Date dataReserva, Date dataValidade) {
        this.dataReserva = dataReserva;
        this.dataValidade = dataValidade;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "dataReserva=" + dataReserva +
                ", dataValidade=" + dataValidade +
                '}';
    }
}
