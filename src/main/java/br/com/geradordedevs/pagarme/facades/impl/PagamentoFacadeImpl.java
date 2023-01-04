package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.exceptions.PagamentoException;
import br.com.geradordedevs.pagarme.exceptions.enums.PagamentoEnum;
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
        if(metodoPagamento != MetodoPagamentoEnum.CREDIT_CARD || metodoPagamento != MetodoPagamentoEnum.DEBIT_CARD){
            throw new PagamentoException(PagamentoEnum.PAGAMENTO_INVALIDO);
        }
        return pagamentoMapper.paraDTO(pagamentoService.criarPagamento(metodoPagamento));
    }
}
