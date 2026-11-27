package br.com.fintech;

public class App {
    public static void main(String[] args) {
        ContaCorrente corrente = new ContaCorrente("001", "Ana Souza", 500);
        corrente.depositar(2000);
        SeguroDeVida seguro = new SeguroDeVida("Ana Souza", 1200);

        CalculadoraDeImpostos calculadora = new CalculadoraDeImpostos();
        calculadora.registrar(corrente);     // é uma Conta E é Tributavel
        calculadora.registrar(seguro);       // não é Conta, mas é Tributavel

        System.out.println(corrente.resumoImposto());
        System.out.println(seguro.resumoImposto());
        System.out.printf("Total de impostos: R$ %.2f%n", calculadora.total());
    }
}
