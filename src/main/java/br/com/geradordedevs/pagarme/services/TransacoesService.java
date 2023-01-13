package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.entities.TransacoesEntity;

public interface TransacoesService {

    Iterable<TransacoesEntity> listarTransacoes();

    TransacoesEntity cadastrarTransacao(TransacoesEntity transacoesEntity);
    void deletarTransacao(Long id);

}
