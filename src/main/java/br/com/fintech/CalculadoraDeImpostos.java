package br.com.fintech;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraDeImpostos {

    private final List<Tributavel> itens = new ArrayList<>();

    public void registrar(Tributavel item) {     // aceita QUALQUER coisa tributável
        itens.add(item);
    }

    public double total() {
        double total = 0;
        for (Tributavel item : itens) {
            total += item.calcularImposto();
        }
        return total;
    }
}
