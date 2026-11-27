package br.com.fintech;

public class SaldoInsuficienteException extends RuntimeException {

    private final double saldoDisponivel;
    private final double valorSolicitado;

    public SaldoInsuficienteException(double saldoDisponivel, double valorSolicitado) {
        super(String.format("Saldo insuficiente: disponível R$ %.2f, solicitado R$ %.2f",
                saldoDisponivel, valorSolicitado));
        this.saldoDisponivel = saldoDisponivel;
        this.valorSolicitado = valorSolicitado;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public double getValorSolicitado() {
        return valorSolicitado;
    }
}
