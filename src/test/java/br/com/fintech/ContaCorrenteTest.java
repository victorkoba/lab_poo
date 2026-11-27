package br.com.fintech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContaCorrenteTest {

    private ContaCorrente conta;

    @BeforeEach
    void prepararCenario() {                         // roda antes de CADA teste
        conta = new ContaCorrente("001", "Ana Souza", 500);
        conta.depositar(1000);
    }

    @Test
    @DisplayName("Depósito aumenta o saldo")
    void depositoAumentaSaldo() {
        conta.depositar(250);                            // Act (agir)
        assertEquals(1250.0, conta.getSaldo(), 0.001);   // Assert (verificar)
    }

    @Test
    @DisplayName("Saque pode usar o limite do cheque especial")
    void saquePodeUsarLimite() {
        conta.sacar(1300);
        assertEquals(-300.0, conta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Saque acima de saldo + limite é recusado")
    void saqueAcimaDoLimiteERecusado() {
        SaldoInsuficienteException erro = assertThrows(SaldoInsuficienteException.class,
                () -> conta.sacar(1600));
        assertEquals(1500.0, erro.getSaldoDisponivel(), 0.001);
        assertEquals(1000.0, conta.getSaldo(), 0.001, "o saldo não pode mudar");
    }

    @Test
    @DisplayName("Depósito negativo é recusado")
    void depositoNegativoERecusado() {
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(-50));
    }

    @Test
    @DisplayName("Tarifa mensal da conta corrente é R$ 12,90")
    void tarifaMensal() {
        conta.aplicarTarifaMensal();
        assertEquals(987.10, conta.getSaldo(), 0.001);
    }
}
