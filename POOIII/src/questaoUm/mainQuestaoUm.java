package questaoUm;

public class mainQuestaoUm {

    public static void main(String[] args) {
        // new Paciente("Joao")
        Assegurado pacienteAssegurado = new Assegurado("Pedro", "21321", "Unimed");
        NaoAssegurado pacienteNaoAssegurado = new NaoAssegurado("Joao", 10, 100, "219210121");

        System.out.println("Dados do paciente assegurado");
        System.out.println("Nome " + pacienteAssegurado.getNome());
        System.out.println("Numero do seguro " + pacienteAssegurado.getNumSeguro());
        System.out.println("Nome da seguradora " + pacienteAssegurado.getNomeSeguradora());

        System.out.println("Dados do paciente não assegurado");
        System.out.println("Nome " + pacienteNaoAssegurado.getNome());
        System.out.println("Valor da consulta " + pacienteNaoAssegurado.getValorConsulta());
        System.out.println("Código do banco " + pacienteNaoAssegurado.getCodBanco());
        System.out.println("Numero do cheque " + pacienteNaoAssegurado.getNumCheque());
    }
}
