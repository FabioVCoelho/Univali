package src;

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
        this.candidatosList = new ArrayList<>();
    }

    public void solicitarAssossiacao(String nome, String telefone, String email) {
        candidatosList.add(new Pessoa(nome, email, telefone));
    }

    public void aceitarSolicitacao(Pessoa pessoa) {
        Socio socio = new Socio(pessoa.nome, pessoa.email, pessoa.telefone);
        socio.registrar();
        socioList.add(socio);
        candidatosList.remove(pessoa);
    }

    public void gerarMensalidade(Date dataParaGerar) throws Exception {
        if (dataParaGerar == null) {
            throw new Exception("");
        }
        for (Socio socio : socioList) {
            Mensalidade mensalidade = new Mensalidade(dataParaGerar, null, 150D, 0D, 0D);
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

    public void adicionarSocio(Socio socio) {
        socioList.add(socio);
    }

    public List<Socio> consultaSocio() {
        return socioList;
    }

    public List<Pessoa> consultaCandidato() {
        return this.candidatosList;
    }
}
