package M1;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final Database databaseSingleton = new Database();
    private static List<Socio> socios = new ArrayList<>();
    private static List<Dependente> dependentes = new ArrayList<>();

    public static Database getInstance() {
        return databaseSingleton;
    }

    public void adicionarSocio(Socio socio) {
        socios.add(socio);
    }

    public void removerSocio(Socio socio) {
        socios.remove(socio);
    }

    public void atualizarSocio(Socio socioOld, Socio socioNew) {
        removerSocio(socioOld);
        adicionarSocio(socioNew);
    }

    public List<Socio> consultaSocio() {
        return socios;
    }
}
