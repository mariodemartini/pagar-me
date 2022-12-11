package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.repositories.PagamentoRepository;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class PagamentoServiceImpl implements PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public PagamentoEntity criarPagamento(MetodoPagamentoEnum metodoPagamento) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        if (metodoPagamento == MetodoPagamentoEnum.DEBIT_CARD){
            pagamentoEntity.setStatus(StatusPagamentoEnum.PAID);
            pagamentoEntity.setDataPagamento(LocalDate.now());
        }
        if (metodoPagamento == MetodoPagamentoEnum.CREDIT_CARD){
            pagamentoEntity.setStatus(StatusPagamentoEnum.WAITING_FUNDS);
            pagamentoEntity.setDataPagamento(LocalDate.now().plusDays(30));
        }
        return salvarPagamento(pagamentoEntity);
    }

    public PagamentoEntity salvarPagamento(PagamentoEntity pagamentoEntity) {
        return pagamentoRepository.save(pagamentoEntity);
    }
}
