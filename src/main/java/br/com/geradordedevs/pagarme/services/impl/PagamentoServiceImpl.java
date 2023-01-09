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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PagamentoServiceImpl implements PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoEntity salvarPagamento(PagamentoEntity pagamentoEntity) {
        log.info("salvando novo pagamento");
        return pagamentoRepository.save(pagamentoEntity);
    }
}
