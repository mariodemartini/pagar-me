package br.com.geradordedevs.pagarme.services;

import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;

public interface PagamentoService {

    boolean criarPagamento(MetodoPagamentoEnum metodoPagamentoEnum);
}
