package br.com.fintech;

public enum TipoTransacao {

    DEPOSITO("Depósito", true),
    SAQUE("Saque", false),
    TARIFA("Tarifa", false),
    RENDIMENTO("Rendimento", true);

    private final String descricao;
    private final boolean entrada;

    TipoTransacao(String descricao, boolean entrada) {
        this.descricao = descricao;
        this.entrada = entrada;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isEntrada() {
        return entrada;
    }
}
