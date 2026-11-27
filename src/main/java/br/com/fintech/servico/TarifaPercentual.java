package br.com.fintech.servico;

public class TarifaPercentual implements PoliticaDeTarifa {

    private final double percentual;
    private final double minimo;

    public TarifaPercentual(double percentual, double minimo) {
        this.percentual = percentual;
        this.minimo = minimo;
    }

    @Override
    public double calcular(double valor) {
        return Math.max(valor * percentual, minimo);
    }
}
