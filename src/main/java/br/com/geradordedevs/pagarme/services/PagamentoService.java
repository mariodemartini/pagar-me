package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;

public interface PagamentoService {

    PagamentoResponseDTO criarPagamento(MetodoPagamentoEnum metodoPagamentoEnum);
}
