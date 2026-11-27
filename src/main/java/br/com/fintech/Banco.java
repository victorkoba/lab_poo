package br.com.fintech;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Banco {

    private final String nome;
    private final Map<String, Conta> contas = new LinkedHashMap<>();   // número -> conta

    public Banco(String nome) {
        this.nome = nome;
    }

    public void abrir(Conta conta) {
        if (contas.containsKey(conta.getNumero())) {
            throw new IllegalArgumentException("Já existe a conta " + conta.getNumero());
        }
        contas.put(conta.getNumero(), conta);
    }

    public Optional<Conta> buscar(String numero) {
        return Optional.ofNullable(contas.get(numero));      // pode não existir
    }

    public double patrimonioTotal() {
        return contas.values().stream()
                .mapToDouble(Conta::getSaldo)
                .sum();
    }

    public List<Conta> ranking() {
        return contas.values().stream()
                .sorted(Comparator.comparingDouble(Conta::getSaldo).reversed())
                .toList();
    }

    public List<String> titularesComSaldoAcimaDe(double valor) {
        return contas.values().stream()
                .filter(conta -> conta.getSaldo() > valor)
                .map(Conta::getTitular)
                .toList();
    }

    public void fecharMes() {
        contas.values().forEach(Conta::aplicarTarifaMensal);
    }

    public String getNome() {
        return nome;
    }
}
