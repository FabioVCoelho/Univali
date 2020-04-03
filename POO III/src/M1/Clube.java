package M1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Clube {
    String nome;
    List<Socio> socioList;
    List<Pessoa> candidatosList;

    public Clube(String nome) {
        this.nome = nome;
        this.socioList = new ArrayList<>();
    }

    public void validarCandidato(Pessoa pessoa) {
        candidatosList.add(pessoa);
    }

    public void avaliarSolicitacao(Pessoa pessoa) {
        Socio socio = new Socio(pessoa.nome, pessoa.email, pessoa.telefone);
        socio.registrar();
        socioList.add(socio);
        candidatosList.remove(pessoa);
    }

    public void gerarMensalidade(Date dataParaGerar) {
        for (Socio socio : socioList) {
            Mensalidade mensalidade = new Mensalidade(dataParaGerar, null, 150D, false, 0D, 0D);
            socio.registrarMensalidade(mensalidade);
        }
    }

    public void removerSocio(Socio socio) {
        socioList.remove(socio);
    }

    public void atualizarSocio(Socio socioOld, Socio socioNew) {
        removerSocio(socioOld);
        adicionarSocio(socioNew);
    }

    private void adicionarSocio(Socio socio) {
        socioList.add(socio);
    }

    public List<Socio> consultaSocio() {
        return socioList;
    }
}
