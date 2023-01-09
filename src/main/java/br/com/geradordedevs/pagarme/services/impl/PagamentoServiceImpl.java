package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PagamentoServiceImpl implements PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public PagamentoEntity criarPagamento(MetodoPagamentoEnum metodoPagamento) {
        PagamentoEntity pagamento = new PagamentoEntity();
        if (metodoPagamento == MetodoPagamentoEnum.CREDIT_CARD){
            pagamento.setDataPagamento(LocalDate.now());
            pagamento.setStatus(StatusPagamentoEnum.WAITING_FUNDS);
        }
        if (metodoPagamento == MetodoPagamentoEnum.DEBIT_CARD){
            pagamento.setDataPagamento(LocalDate.now().plusDays(30));
            pagamento.setStatus(StatusPagamentoEnum.PAID);
        }
        log.info("salvando pagamento tipo: {}", metodoPagamento);
        return pagamentoRepository.save(pagamento);
    }
    
}
