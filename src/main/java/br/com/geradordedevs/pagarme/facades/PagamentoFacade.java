package br.com.geradordedevs.pagarme.facades;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;

public interface PagamentoFacade {
    PagamentoResponseDTO criarPagamento(MetodoPagamentoEnum metodoPagamento);

}
