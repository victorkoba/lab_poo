package br.com.fintech.servico;

import br.com.fintech.ContaCorrente;
import br.com.fintech.SaldoInsuficienteException;
import br.com.fintech.repositorio.ContaRepository;
import br.com.fintech.repositorio.ContaRepositoryEmMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransferenciaServiceTest {

    private ContaRepository repositorio;
    private ContaCorrente origem;
    private ContaCorrente destino;

    @BeforeEach
    void preparar() {
        repositorio = new ContaRepositoryEmMemoria();
        origem = new ContaCorrente("001", "Ana", 0);
        destino = new ContaCorrente("002", "Bruno", 0);
        origem.depositar(1000);
        repositorio.salvar(origem);
        repositorio.salvar(destino);
    }

    @Test
    void deveTransferirCobrandoTarifa() {
        PoliticaDeTarifa tarifaFixa = valor -> 2.50;     // dublê de teste com lambda
        TransferenciaService servico = new TransferenciaService(repositorio, tarifaFixa);

        servico.transferir("001", "002", 100);

        assertEquals(897.50, origem.getSaldo(), 0.001);
        assertEquals(100.0, destino.getSaldo(), 0.001);
    }

    @Test
    void naoDeveTransferirParaContaInexistente() {
        TransferenciaService servico = new TransferenciaService(repositorio, PoliticaDeTarifa.gratuita());

        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> servico.transferir("001", "999", 10));
        assertEquals("Conta inexistente: 999", erro.getMessage());
    }

    @Test
    void naoDeveMexerNoSaldoQuandoNaoHaDinheiro() {
        TransferenciaService servico = new TransferenciaService(repositorio, PoliticaDeTarifa.gratuita());

        assertThrows(SaldoInsuficienteException.class, () -> servico.transferir("001", "002", 5000));
        assertEquals(1000.0, origem.getSaldo(), 0.001);
        assertEquals(0.0, destino.getSaldo(), 0.001);
    }
}
