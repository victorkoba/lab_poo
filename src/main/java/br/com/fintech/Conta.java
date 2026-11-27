package br.com.fintech;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta {

    private final String numero;
    private final String titular;
    private double saldo;
    private final List<Transacao> transacoes = new ArrayList<>();

    protected Conta(String numero, String titular) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número da conta é obrigatório");
        }
        this.numero = numero;
        this.titular = titular;
    }

    public void depositar(double valor) {
        validarValor(valor);
        saldo += valor;
        registrar(TipoTransacao.DEPOSITO, valor);
    }

    public void sacar(double valor) {
        validarValor(valor);
        if (valor > saldoDisponivel()) {
            throw new SaldoInsuficienteException(saldoDisponivel(), valor);
        }
        saldo -= valor;
        registrar(TipoTransacao.SAQUE, valor);
    }

    public void aplicarTarifaMensal() {
        double tarifa = calcularTarifaMensal();
        if (tarifa > 0) {
            saldo -= tarifa;
            registrar(TipoTransacao.TARIFA, tarifa);
        }
    }

    // as subclasses podem mudar quanto dinheiro está disponível para saque
    protected double saldoDisponivel() {
        return saldo;
    }

    protected void creditarRendimento(double valor) {
        saldo += valor;
        registrar(TipoTransacao.RENDIMENTO, valor);
    }

    // cada tipo de conta TEM que dizer o seu tipo e a sua tarifa
    public abstract String getTipo();

    protected abstract double calcularTarifaMensal();

    private void validarValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo: " + valor);
        }
    }

    private void registrar(TipoTransacao tipo, double valor) {
        transacoes.add(new Transacao(tipo, valor, LocalDate.now()));
    }

    // devolve uma VISÃO somente leitura: ninguém de fora altera o histórico
    public List<Transacao> getExtrato() {
        return Collections.unmodifiableList(transacoes);
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
        return String.format("%s %s (%s) - saldo R$ %.2f", getTipo(), numero, titular, saldo);
    }
}
