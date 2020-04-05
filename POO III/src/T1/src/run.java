package src;

import java.util.ArrayList;
import java.util.List;

public class run {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("Audores");
        Usuario usuario = new Usuario("Joao", "Centro", "4899993333", "pedra");
        Material material1 = new Material("1", "Biologia", "Mamiferos amados");
        Livro livro = new Livro("2", "Biologia", "Orcas assassinas", "ABC", "herbert", "123213", "José");
        Artigo artigo = new Artigo("Oscar", "Arquiteturas magnificas");
        List<Artigo> artigos = new ArrayList<>();
        artigos.add(artigo);
        Revista revista = null;
        try {
            revista = new Revista("3", "Eng. Civil", "Arquiteturas magnificas", "Arq", "imoveis", artigos);
        } catch (RevistaSemArtigo revistaSemArtigo) {
            revistaSemArtigo.printStackTrace();
        }

        biblioteca.adicionarMaterial(material1);
        biblioteca.adicionarMaterial(livro);
        biblioteca.adicionarMaterial(revista);
        System.out.println();
        System.out.println("------ Materiais no acervo -------");
        System.out.println();
        for (Material material : biblioteca.consultaAcervo()) {
            System.out.println(material);
        }


        List<Material> materiais = biblioteca.consultaMaterial("Biologia");
        System.out.println();
        System.out.println("------ Materiais no acervo com assunto de biologia -------");
        System.out.println();
        for (Material material : materiais) {
            System.out.println(material);
        }

        try {
            usuario.emprestar(material1);
        } catch (MaterialJaEmprestadoException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("------ Materiais emprestados pelo usuário -------");
        System.out.println();

        for (Material material : usuario.getMaterialEmprestado()) {
            System.out.println(material);
        }

        usuario.reservar(livro);

        System.out.println();
        System.out.println("------ Materiais no acervo com assunto de biologia reserva -------");
        System.out.println();

        for (Material material : materiais) {
            System.out.println(material);
        }

        usuario.devolverMaterial(material1);

        System.out.println();
        System.out.println("------ Materiais no acervo com assunto de biologia após devolução -------");
        System.out.println();

        for (Material material : materiais) {
            System.out.println(material);
        }

        System.out.println();
        System.out.println("------ Materiais no acervo com assunto de biologia após devolução realizar um novo empréstimo -------");
        System.out.println();
        try {
            usuario.emprestar(material1);
        } catch (MaterialJaEmprestadoException e) {
            e.printStackTrace();
        }

        for (Material material : materiais) {
            System.out.println(material);
        }

    }
}
