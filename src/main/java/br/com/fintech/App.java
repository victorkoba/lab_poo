package br.com.fintech;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ContaCorrente ana = new ContaCorrente("001", "Ana Souza", 500);
        ContaPoupanca bruno = new ContaPoupanca("002", "Bruno Lima", 0.005);
        ContaCorrente carla = new ContaCorrente("003", "Carla Dias", 1000);
        ana.depositar(1000);
        bruno.depositar(3000);
        carla.depositar(200);

        // a lista é de Conta, mas cada objeto continua sendo do seu tipo real
        List<Conta> contas = List.of(ana, bruno, carla);

        System.out.println("== Fechamento do mês ==");
        for (Conta conta : contas) {
            conta.aplicarTarifaMensal();     // cada tipo calcula a SUA tarifa
            System.out.println(conta);
        }

        System.out.println("== Detalhes ==");
        for (Conta conta : contas) {
            System.out.println(descrever(conta));
        }
    }

    // pattern matching no switch (Java 21)
    static String descrever(Conta conta) {
        return switch (conta) {
            case ContaCorrente cc -> cc.getTitular() + ": corrente com limite de R$ " + cc.getLimite();
            case ContaPoupanca cp -> cp.getTitular() + ": poupança que rende todo mês";
            default -> conta.getTitular() + ": outro tipo de conta";
        };
    }
}
