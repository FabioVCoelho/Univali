package src.observerView;

import src.Mensalidade;

public interface Observer {
    void quitarMensalidade(Mensalidade mensalidade, double parseDouble);
}
