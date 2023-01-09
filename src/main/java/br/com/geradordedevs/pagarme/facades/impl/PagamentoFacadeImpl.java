package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.facades.PagamentoFacade;
import br.com.geradordedevs.pagarme.mapper.PagamentoMapper;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class PagamentoFacadeImpl implements PagamentoFacade {
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private PagamentoMapper mapper;

    @Override
    public PagamentoResponseDTO criarPagamento(MetodoPagamentoEnum metodoPagamento) {
        PagamentoEntity pagamento = new PagamentoEntity();
        if (metodoPagamento == MetodoPagamentoEnum.CREDIT_CARD){
            log.info("cadastrando pagamento de CREDITO");
            pagamento.setDataPagamento(LocalDate.now());
            pagamento.setStatus(StatusPagamentoEnum.WAITING_FUNDS);
        }
        if (metodoPagamento == MetodoPagamentoEnum.DEBIT_CARD){
            log.info("cadastrando pagamento de DEBITO");
            pagamento.setDataPagamento(LocalDate.now().plusDays(30));
            pagamento.setStatus(StatusPagamentoEnum.PAID);
        }
        return mapper.paraDTO(pagamento);
    }
}
