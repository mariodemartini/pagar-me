package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.mapper.TransacoesMapper;
import br.com.geradordedevs.pagarme.repositories.PagamentoRepository;
import br.com.geradordedevs.pagarme.repositories.TransacoesRepository;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum.CREDIT_CARD;
import static br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum.DEBIT_CARD;

@Service
@Slf4j
public class TransacoesServiceImpl implements TransacoesService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private TransacoesRepository transacoesRepository;
    @Autowired
    private TransacoesMapper transacoesMapper;
    @Autowired
    private PagamentoService pagamentoService;
    @Override
    public List<TransacoesResponseDTO> listaTransacoes() {
        log.info("listando as transações");
        List<TransacoesEntity> transacoesEntities = new ArrayList<>();
        for (TransacoesEntity transacoesEntity : transacoesRepository.findAll()) {
            transacoesEntities.add(transacoesEntity);
        }
        return transacoesMapper.paraListaDTO(transacoesEntities);
    }

    @Override
    public TransacoesResponseDTO cadastrarTransacao(TransacoesRequestDTO requestDTO){
        if (pagamentoService.criarPagamento(DEBIT_CARD)){
            requestDTO.setMetodoPagamentoEnum(DEBIT_CARD);
        } else {
            requestDTO.setMetodoPagamentoEnum(CREDIT_CARD);
        }
        return transacoesMapper.paraDTO(transacoesRepository.save(transacoesMapper.paraEntidade(requestDTO)));
    }

}
