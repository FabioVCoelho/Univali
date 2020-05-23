package src;

public class Dependente extends Pessoa {

    Socio socio;

    public Dependente(String nome, String email, String telefone) {
        super(nome, email, telefone);
    }

    public long registrar(Socio socio) {
        this.socio = socio;
        return socio.numeroCartaoSocio;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
