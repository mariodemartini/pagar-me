package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.mapper.TransacoesMapper;
import br.com.geradordedevs.pagarme.repositories.PagamentoRepository;
import br.com.geradordedevs.pagarme.repositories.TransacoesRepository;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        log.info("cadastrando nova transação: {}",requestDTO);
        log.info("retorno metodoPagamento: {}", requestDTO.getMetodoPagamento());
        requestDTO.setPagamento(pagamentoService.criarPagamento(requestDTO.getMetodoPagamento()));
        return transacoesMapper.paraDTO(transacoesRepository.save(transacoesMapper.paraEntidade(requestDTO)));
    }

    @Override
    public void deletarTransacao(Long id) {
        log.info("removendo transação id {}", id);
        transacoesRepository.deleteById(id);
    }

    public SaldoResponseDTO consultarSaldo(BigDecimal valor){
        BigDecimal saldoDebito = BigDecimal.ZERO;
        BigDecimal saldoCredito = BigDecimal.ZERO;
        for (TransacoesEntity transacoesEntity : transacoesRepository.findAll()) {
            if (transacoesEntity.getMetodoPagamento() == MetodoPagamentoEnum.DEBIT_CARD){
                BigDecimal taxa = (transacoesEntity.getValorTransacao().multiply(new BigDecimal("0.03")));
                BigDecimal valorTaxado = transacoesEntity.getValorTransacao().subtract(taxa);
                saldoDebito = saldoDebito.add(valorTaxado);
            }
            if (transacoesEntity.getMetodoPagamento() == MetodoPagamentoEnum.CREDIT_CARD){
                BigDecimal taxa = (transacoesEntity.getValorTransacao().multiply(new BigDecimal("0.05")));
                BigDecimal valorTaxado = transacoesEntity.getValorTransacao().subtract(taxa);
                saldoCredito = saldoCredito.add(valorTaxado);
            }
        }
        SaldoResponseDTO saldoResponseDTO = new SaldoResponseDTO();
        saldoResponseDTO.setSaldoCredito(saldoCredito);
        saldoResponseDTO.setSaldoDebito(saldoDebito);
        return saldoResponseDTO;
    }
}
