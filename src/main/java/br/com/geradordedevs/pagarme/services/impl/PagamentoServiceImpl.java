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

    @Autowired //todo identação
    private PagamentoRepository pagamentoRepository;

    @Override
    public PagamentoEntity criarPagamento(MetodoPagamentoEnum metodoPagamento){
        PagamentoEntity pagamento = new PagamentoEntity(); //todo identação
        if (metodoPagamento == MetodoPagamentoEnum.CREDIT_CARD){
            log.info("cadastrando pagamento de CREDITO");
            pagamento.setDataPagamento(LocalDate.now().plusDays(30)); //todo muito bom
            pagamento.setStatus(StatusPagamentoEnum.WAITING_FUNDS);
        }
        if (metodoPagamento == MetodoPagamentoEnum.DEBIT_CARD){
            log.info("cadastrando pagamento de DEBITO");
            pagamento.setDataPagamento(LocalDate.now());
            pagamento.setStatus(StatusPagamentoEnum.PAID);
        }
        return pagamentoRepository.save(pagamento);
    }

}
