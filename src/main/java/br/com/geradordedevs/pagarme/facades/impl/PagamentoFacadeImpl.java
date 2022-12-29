package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.facades.PagamentoFacade;
import br.com.geradordedevs.pagarme.mapper.PagamentoMapper;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;

public class PagamentoFacadeImpl implements PagamentoFacade {
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private PagamentoMapper pagamentoMapper;


    @Override
    public PagamentoResponseDTO criarPagamento(MetodoPagamentoEnum metodoPagamento) {
        return pagamentoMapper.paraDTO(pagamentoService.criarPagamento(metodoPagamento));
    }
}
