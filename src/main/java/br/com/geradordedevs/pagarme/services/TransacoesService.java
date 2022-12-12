package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;

import java.util.List;

public interface TransacoesService {

    List<TransacoesResponseDTO> listaTransacoes();
    TransacoesResponseDTO cadastrarTransacao(TransacoesRequestDTO transacoesRequestDTO);
    void deletarTransacao(Long id);
}
