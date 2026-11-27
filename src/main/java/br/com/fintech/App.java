package br.com.fintech;

public class App {
    public static void main(String[] args) {
        Banco banco = new Banco("FinBank");
        ContaCorrente ana = new ContaCorrente("001", "Ana Souza", 500);
        ContaPoupanca bruno = new ContaPoupanca("002", "Bruno Lima", 0.005);
        ContaCorrente carla = new ContaCorrente("003", "Carla Dias", 1000);
        banco.abrir(ana);
        banco.abrir(bruno);
        banco.abrir(carla);

        ana.depositar(1500);
        bruno.depositar(5000);
        carla.depositar(800);
        ana.sacar(1800);
        banco.fecharMes();
        bruno.renderJuros();

        System.out.println("== Ranking por saldo ==");
        banco.ranking().forEach(System.out::println);
        System.out.printf("Patrimônio total: R$ %.2f%n", banco.patrimonioTotal());
        System.out.println("Saldo acima de R$ 1.000: " + banco.titularesComSaldoAcimaDe(1000));

        System.out.println("== Extrato da conta 001 ==");
        banco.buscar("001").ifPresent(conta ->
                conta.getExtrato().forEach(t -> System.out.println("  " + t)));

        String titular = banco.buscar("999").map(Conta::getTitular).orElse("conta não encontrada");
        System.out.println("Conta 999: " + titular);
    }
}
