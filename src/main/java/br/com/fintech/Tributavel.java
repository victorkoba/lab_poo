package br.com.fintech;

public interface Tributavel {

    double calcularImposto();              // contrato: quem implementa TEM que ter

    default String resumoImposto() {       // método default: já vem pronto
        return String.format("%s -> imposto devido: R$ %.2f",
                getClass().getSimpleName(), calcularImposto());
    }
}
