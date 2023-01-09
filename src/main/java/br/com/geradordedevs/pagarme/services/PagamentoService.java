package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;

public interface PagamentoService {

    PagamentoEntity salvarPagamento(PagamentoEntity pagamentoEntity);

    PagamentoEntity criarPagamento(MetodoPagamentoEnum metodoPagamento);
}
