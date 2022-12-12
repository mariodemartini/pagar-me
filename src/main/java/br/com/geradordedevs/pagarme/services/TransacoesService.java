package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;

import java.util.List;

public interface TransacoesService {

    List<TransacoesEntity> listaTransacoes();
    TransacoesEntity cadastrarTransacao(TransacoesEntity transacoes);
    void deletarTransacao(Long id);
}
