package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransacoesService {

    List<TransacoesResponseDTO> listaTransacoes();
    TransacoesResponseDTO cadastrarTransacao(TransacoesRequestDTO transacoesRequestDTO);
    void deletarTransacao(Long id);
    SaldoResponseDTO consultarSaldo(BigDecimal valor);

}
