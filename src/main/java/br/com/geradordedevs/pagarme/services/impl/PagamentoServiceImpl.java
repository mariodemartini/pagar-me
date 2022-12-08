package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.mapper.PagamentoMapper;
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
    @Autowired
    private PagamentoMapper pagamentoMapper;


    @Override
    public PagamentoResponseDTO criarPagamento(MetodoPagamentoEnum metodoPagamentoEnum) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        if (metodoPagamentoEnum == metodoPagamentoEnum.DEBIT_CARD){
            pagamentoEntity.setStatus(String.valueOf(StatusPagamentoEnum.PAID));
            pagamentoEntity.setDataPagamento(LocalDate.now());
        }
        if (metodoPagamentoEnum == metodoPagamentoEnum.CREDIT_CARD){
            pagamentoEntity.setStatus(String.valueOf(StatusPagamentoEnum.WAITING_FUNDS));
            pagamentoEntity.setDataPagamento(LocalDate.now().plusDays(30));
        }
        return pagamentoMapper.paraDTO(pagamentoRepository.save(pagamentoEntity));
    }
}
