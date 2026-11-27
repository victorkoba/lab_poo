package br.com.fintech.repositorio;

import br.com.fintech.Conta;

import java.util.List;
import java.util.Optional;

// Repository: esconde COMO as contas são guardadas (memória, banco de dados, API...)
public interface ContaRepository {

    void salvar(Conta conta);

    Optional<Conta> buscarPorNumero(String numero);

    List<Conta> listarTodas();
}
