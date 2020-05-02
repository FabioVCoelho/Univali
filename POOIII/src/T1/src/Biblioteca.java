package src;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    protected String nome;
    protected List<Material> acervo;

    public Biblioteca(String nome) {
        this.nome = nome;
        acervo = new ArrayList<>();
    }

    public void adicionarMaterial(Material material) {
        this.acervo.add(material);
    }

    public List<Material> consultaMaterial(String assunto) {
        List<Material> tmp = new ArrayList<>();
        for (Material material : acervo) {
            if (material.getAssunto().toLowerCase().equals(assunto.toLowerCase())) {
                tmp.add(material);
            }
        }
        return tmp;
    }

    public List<Material> consultaAcervo() {
        return new ArrayList<>(this.acervo);
    }
}
