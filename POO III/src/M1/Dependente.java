package M1;

public class Dependente extends Pessoa {

    Socio socio;

    public Dependente(String nome, String email, String telefone) {
        super(nome, email, telefone);
    }

    public Dependente(Pessoa pessoa) {
        super(pessoa.nome, pessoa.email, pessoa.telefone);
    }

    public long registrar(Socio socio) {
        this.socio = socio;
        return socio.numeroCartaoSocio;
    }
}
