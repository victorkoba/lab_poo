package br.com.fintech;

public class App {
    public static void main(String[] args) {
        Conta contaDaAna = new Conta("001", "Ana Souza");
        contaDaAna.depositar(1000);
        contaDaAna.sacar(100);
        System.out.println(contaDaAna);

        Conta contaDoBruno = new Conta("002", "Bruno Lima");
        contaDoBruno.depositar(250);
        System.out.println(contaDoBruno);

        System.out.println("Tentando sacar R$ 5.000 da conta do Bruno...");
        contaDoBruno.sacar(5000);
        System.out.println("Esta linha nunca será impressa.");
    }
}
