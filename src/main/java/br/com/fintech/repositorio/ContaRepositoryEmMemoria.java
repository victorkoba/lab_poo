package br.com.fintech.repositorio;

import br.com.fintech.Conta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContaRepositoryEmMemoria implements ContaRepository {

    private final Map<String, Conta> dados = new LinkedHashMap<>();

    @Override
    public void salvar(Conta conta) {
        dados.put(conta.getNumero(), conta);
    }

    @Override
    public Optional<Conta> buscarPorNumero(String numero) {
        return Optional.ofNullable(dados.get(numero));
    }

    @Override
    public List<Conta> listarTodas() {
        return new ArrayList<>(dados.values());
    }
}
