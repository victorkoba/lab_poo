package br.com.fintech;

// Um seguro NÃO é uma conta, mas também paga imposto: por isso interface, e não herança.
public class SeguroDeVida implements Tributavel {

    private final String segurado;
    private final double premioAnual;

    public SeguroDeVida(String segurado, double premioAnual) {
        this.segurado = segurado;
        this.premioAnual = premioAnual;
    }

    @Override
    public double calcularImposto() {
        return premioAnual * 0.02;
    }

    public String getSegurado() {
        return segurado;
    }
}
