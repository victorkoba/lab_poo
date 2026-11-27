package br.com.fintech;

import java.time.LocalDate;

public record Transacao(TipoTransacao tipo, double valor, LocalDate data) {

    @Override
    public String toString() {
        return String.format("%s  %-10s %s R$ %8.2f", data, tipo.getDescricao(),
                tipo.isEntrada() ? "+" : "-", valor);
    }
}
