package br.com.fintech;

public class ContaPoupanca extends Conta {

    private final double taxaMensal;

    public ContaPoupanca(String numero, String titular, double taxaMensal) {
        super(numero, titular);
        this.taxaMensal = taxaMensal;
    }

    public void renderJuros() {
        double rendimento = getSaldo() * taxaMensal;
        if (rendimento > 0) {
            creditarRendimento(rendimento);
        }
    }

    @Override
    public String getTipo() {
        return "Conta Poupança";
    }

    @Override
    protected double calcularTarifaMensal() {
        return 0;                            // poupança não tem tarifa
    }
}
