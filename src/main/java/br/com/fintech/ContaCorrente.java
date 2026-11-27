package br.com.fintech;

public class ContaCorrente extends Conta {

    private static final double TARIFA_MENSAL = 12.90;
    private final double limite;

    public ContaCorrente(String numero, String titular, double limite) {
        super(numero, titular);              // chama o construtor da classe mãe
        this.limite = limite;
    }

    @Override
    protected double saldoDisponivel() {
        return getSaldo() + limite;          // a corrente pode usar o cheque especial
    }

    @Override
    public String getTipo() {
        return "Conta Corrente";
    }

    @Override
    protected double calcularTarifaMensal() {
        return TARIFA_MENSAL;
    }

    public double getLimite() {
        return limite;
    }
}
