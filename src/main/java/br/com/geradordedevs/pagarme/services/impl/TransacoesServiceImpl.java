package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.mapper.PagamentoMapper;
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
    private PagamentoMapper pagamentoMapper;
    @Autowired
    private PagamentoService pagamentoService;

    @Override
    public List<TransacoesEntity> listaTransacoes() {
        log.info("listando as transações");
        List<TransacoesEntity> transacoesEntities = new ArrayList<>();
        for (TransacoesEntity transacoesEntity : transacoesRepository.findAll()) {
            transacoesEntities.add(transacoesEntity);
        }
        return transacoesEntities;
    }

    @Override
    public TransacoesEntity cadastrarTransacao(TransacoesEntity transacoes){
        log.info("cadastrando nova transação: {}",transacoes);
        log.info("retorno metodoPagamento: {}", transacoes.getMetodoPagamento());
        transacoes.setPagamento(pagamentoService.criarPagamento(transacoes.getMetodoPagamento()));
        return transacoesRepository.save(transacoes);
    }

}
