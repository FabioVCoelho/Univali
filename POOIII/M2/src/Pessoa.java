package src;

import java.util.Arrays;

public class Pessoa {

    protected MembrosPessoa[] todosOsMembros;
    protected MembrosPessoa[] membros = new MembrosPessoa[6];

    public Pessoa() {
        todosOsMembros = MembrosPessoa.values();
    }

    public void adicionarUmMembro() {
        for (int i = 0; i < membros.length; i++) {
            if (membros[i] == null) {
                membros[i] = todosOsMembros[i];
                break;
            }
        }
    }

    public boolean perdeuTodosOsMembros() {
        return Arrays.equals(todosOsMembros, membros);
    }

    public MembrosPessoa[] getMembros() {
        return membros;
    }
}
