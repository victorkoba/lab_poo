package br.com.fintech;

import br.com.fintech.repositorio.ContaRepository;
import br.com.fintech.repositorio.ContaRepositoryEmMemoria;
import br.com.fintech.servico.PoliticaDeTarifa;
import br.com.fintech.servico.TarifaPercentual;
import br.com.fintech.servico.TransferenciaService;

public class App {
    public static void main(String[] args) {
        ContaRepository repositorio = new ContaRepositoryEmMemoria();
        repositorio.salvar(new ContaCorrente("001", "Ana Souza", 500));
        repositorio.salvar(new ContaPoupanca("002", "Bruno Lima", 0.005));
        repositorio.buscarPorNumero("001").ifPresent(conta -> conta.depositar(1000));

        PoliticaDeTarifa tarifa = new TarifaPercentual(0.01, 1.00);   // 1%, mínimo R$ 1,00
        TransferenciaService servico = new TransferenciaService(repositorio, tarifa);

        servico.transferir("001", "002", 300);
        repositorio.listarTodas().forEach(System.out::println);

        try {
            servico.transferir("002", "999", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Falhou: " + e.getMessage());
        }
    }
}
