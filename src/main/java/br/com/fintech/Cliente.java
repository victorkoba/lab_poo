package br.com.fintech;

// Um record gera sozinho: construtor, campos private final, nome(), cpf(),
// equals, hashCode e toString. Ideal para dados imutáveis (value objects / DTOs).
public record Cliente(String nome, String cpf) {

    public Cliente {                          // construtor compacto: validação
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos: " + cpf);
        }
    }
}
