package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.exceptions.PagamentoException;
import br.com.geradordedevs.pagarme.exceptions.enums.PagamentoEnum;
import br.com.geradordedevs.pagarme.repositories.PagamentoRepository;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PagamentoServiceImpl implements PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public PagamentoEntity criarPagamento(MetodoPagamentoEnum metodoPagamento) {
        log.info("criando um novo pagamento: {}", metodoPagamento);
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        if (metodoPagamento == null) {
            log.info("pagamento diferente das opções");
            throw new PagamentoException(PagamentoEnum.PAGAMENTO_INVALIDO);
        }
        if (metodoPagamento == MetodoPagamentoEnum.DEBIT_CARD){
            pagamentoEntity.setStatus(StatusPagamentoEnum.PAID);
            pagamentoEntity.setDataPagamento(LocalDateTime.now());
        }
        if (metodoPagamento == MetodoPagamentoEnum.CREDIT_CARD){
            pagamentoEntity.setStatus(StatusPagamentoEnum.WAITING_FUNDS);
            pagamentoEntity.setDataPagamento(LocalDateTime.now().plusDays(30));
        }
        return salvarPagamento(pagamentoEntity);
    }

    public PagamentoEntity salvarPagamento(PagamentoEntity pagamentoEntity) {
        log.info("salvando novo pagamento");
        return pagamentoRepository.save(pagamentoEntity);
    }
}
