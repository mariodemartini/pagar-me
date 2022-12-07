package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.entities.TransacoesEntity;

import java.util.List;

public interface TransacoesService {
    List<TransacoesEntity> listaTransacoes();
    TransacoesEntity cadastrarTransacao(TransacoesEntity transacoesEntity);
}
