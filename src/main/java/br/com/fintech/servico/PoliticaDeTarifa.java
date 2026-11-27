package br.com.fintech.servico;

// Strategy: a regra de tarifa é um objeto que pode ser trocado sem mexer no serviço
@FunctionalInterface
public interface PoliticaDeTarifa {

    double calcular(double valor);

    static PoliticaDeTarifa gratuita() {
        return valor -> 0;
    }
}
