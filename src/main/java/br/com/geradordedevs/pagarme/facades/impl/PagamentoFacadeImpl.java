package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.facades.PagamentoFacade;
import br.com.geradordedevs.pagarme.mapper.PagamentoMapper;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PagamentoFacadeImpl implements PagamentoFacade {
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private PagamentoMapper pagamentoMapper;


    @Override
    public PagamentoResponseDTO criarPagamento(MetodoPagamentoEnum metodoPagamento) {
        PagamentoEntity pagamento = new PagamentoEntity();
        if (metodoPagamento == MetodoPagamentoEnum.CREDIT_CARD){
            pagamentoService.salvarPagamento(pagamento);
        }
        if (metodoPagamento == MetodoPagamentoEnum.DEBIT_CARD){
            pagamentoService.salvarPagamento(pagamento);
        }
        return pagamentoMapper.paraDTO(pagamento);
    }
}
