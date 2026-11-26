package br.com.fintech;

public class Conta {

    private final String numero;
    private final String titular;
    private double saldo;

    public Conta(String numero, String titular) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta é obrigatório");
        }
        this.numero = numero;
        this.titular = titular;
    }

    public void depositar(double valor) {
        validarValor(valor);
        saldo += valor;
    }

    public void sacar(double valor) {
        validarValor(valor);
        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente para sacar R$ " + valor);
        }
        saldo -= valor;
    }

    private void validarValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo: " + valor);
        }
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return String.format("Conta %s (%s) - saldo R$ %.2f", numero, titular, saldo);
    }
}
