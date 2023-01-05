package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.exceptions.TransacoesException;
import br.com.geradordedevs.pagarme.exceptions.enums.TransacoesEnum;
import br.com.geradordedevs.pagarme.facades.TransacoesFacade;
import br.com.geradordedevs.pagarme.mapper.TransacoesMapper;
import br.com.geradordedevs.pagarme.services.PagamentoService;
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
    private PagamentoService pagamentoService;
    @Autowired
    private TransacoesMapper mapper;

    @Override
    public List<TransacoesResponseDTO> listaTransacoes() {
        return mapper.paraListaDTO(transacoesService.listarTransacoes());
    }

    @Override
    public TransacoesResponseDTO cadastrarTransacao(TransacoesRequestDTO requestDTO) {
        if (requestDTO.getMetodoPagamento() == null){
            throw new TransacoesException(TransacoesEnum.TRANSACAO_INVALIDA);
        }
        requestDTO.setNumeroCartao(escondeNumeroCartao(requestDTO.getNumeroCartao()));
        requestDTO.setPagamento(pagamentoService.criarPagamento(requestDTO.getMetodoPagamento()));
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

    private String escondeNumeroCartao(String numero){
        final String digitosCartao = "**** **** **** ";
        String ultimosDigitos = numero.substring(numero.length() - 4);
        return digitosCartao + ultimosDigitos;
    }
}
