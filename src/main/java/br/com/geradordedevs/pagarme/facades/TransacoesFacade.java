package br.com.geradordedevs.pagarme.facades;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface TransacoesFacade {
    List<TransacoesResponseDTO> listaTransacoes();
    TransacoesResponseDTO cadastrarTransacao(TransacoesRequestDTO requestDTO);
    void deletarTransacao(Long id);
    SaldoResponseDTO consultarSaldo(BigDecimal valor);
}
