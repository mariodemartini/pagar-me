package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.facades.PagamentoFacade;
import br.com.geradordedevs.pagarme.facades.TransacoesFacade;
import br.com.geradordedevs.pagarme.mapper.TransacoesMapper;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
public class TransacoesFacadeImpl implements TransacoesFacade {
    @Autowired
    private TransacoesService transacoesService;
    @Autowired
    private PagamentoFacade pagamentoFacade;
    @Autowired
    private TransacoesMapper mapper;

    @Override
    public List<TransacoesResponseDTO> listaTransacoes() {
        return mapper.paraListaDTO(transacoesService.listarTransacoes());
    }

    @Override
    public TransacoesResponseDTO cadastrarTransacao(TransacoesRequestDTO requestDTO) {
        requestDTO.setNumeroCartao(escondeNumeroCartao(requestDTO.getNumeroCartao()));
        if (requestDTO.getMetodoPagamento() == MetodoPagamentoEnum.CREDIT_CARD){

            BigDecimal valorTaxa = requestDTO.getValorTransacao().multiply(new BigDecimal("0.05"));
            BigDecimal valorLiquido = requestDTO.getValorTransacao().subtract(valorTaxa);
            requestDTO.setValorTransacao(valorLiquido);
            log.info("descobrindo valor transacao: {}", requestDTO.getValorTransacao());
        }
        if (requestDTO.getMetodoPagamento() == MetodoPagamentoEnum.DEBIT_CARD){
            BigDecimal valorTaxa = requestDTO.getValorTransacao().multiply(new BigDecimal("0.03"));
            BigDecimal valorLiquido = requestDTO.getValorTransacao().subtract(valorTaxa);
            requestDTO.setValorTransacao(valorLiquido);
            log.info("descobrindo valor transacao: {}", requestDTO.getValorTransacao());
        }
        requestDTO.setPagamento(pagamentoFacade.criarPagamento(requestDTO.getMetodoPagamento()));
        return mapper.paraDTO(transacoesService.cadastrarTransacao(mapper.paraEntidade(requestDTO)));
    }

    @Override
    public void deletarTransacao(Long id) {
        transacoesService.deletarTransacao(id);
    }

    public SaldoResponseDTO consultarSaldo(BigDecimal valor){
        BigDecimal saldoDebito = BigDecimal.ZERO;
        BigDecimal saldoCredito = BigDecimal.ZERO;
        log.info("consultando saldo");
        for (TransacoesEntity transacoesEntity : transacoesService.listarTransacoes()) {
            if (transacoesEntity.getMetodoPagamento() == MetodoPagamentoEnum.DEBIT_CARD){
                saldoDebito = saldoDebito.add(transacoesEntity.getValorTransacao());
            }
            if (transacoesEntity.getMetodoPagamento() == MetodoPagamentoEnum.CREDIT_CARD){
                saldoCredito = saldoCredito.add(transacoesEntity.getValorTransacao());
            }
        }
        SaldoResponseDTO saldoResponseDTO = new SaldoResponseDTO();
        saldoResponseDTO.setSaldoCredito(saldoCredito);
        saldoResponseDTO.setSaldoDebito(saldoDebito);
        return saldoResponseDTO;
    }

    private String escondeNumeroCartao(String numero){
        final String digitosCartao = "**** **** **** ";
        String ultimosDigitos = numero.substring(numero.length() - 4);
        return digitosCartao + ultimosDigitos;
    }
}
