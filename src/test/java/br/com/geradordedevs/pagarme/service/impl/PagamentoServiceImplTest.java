package br.com.geradordedevs.pagarme.service.impl;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.repositories.PagamentoRepository;
import br.com.geradordedevs.pagarme.services.impl.PagamentoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class PagamentoServiceImplTest {

    @InjectMocks
    private PagamentoServiceImpl pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;
    
    private static final MetodoPagamentoEnum MOCK_PGTO_CREDITO = MetodoPagamentoEnum.CREDIT_CARD;
    private static final MetodoPagamentoEnum MOCK_PGTO_DEBITO = MetodoPagamentoEnum.DEBIT_CARD;
    private static final StatusPagamentoEnum MOCK_STATUS_CREDITO = StatusPagamentoEnum.WAITING_FUNDS;
    private static final StatusPagamentoEnum MOCK_STATUS_DEBITO = StatusPagamentoEnum.PAID;
    private static final LocalDate MOCK_DATA_CREDITO = LocalDate.now().plusDays(30);
    private static final LocalDate MOCK_DATA_DEBITO = LocalDate.now();

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(pagamentoRepository.save(retornaObjetoPagamentoEntityCredito())).thenReturn(retornaObjetoPagamentoEntityCredito());
        when(pagamentoRepository.save(retornaObjetoPagamentoEntityDebito())).thenReturn(retornaObjetoPagamentoEntityDebito());
    }

    @Test
    public void criarPagamentoCreditoDeveRetornarOk() throws Exception{
        assertEquals(retornaObjetoPagamentoEntityCredito(), pagamentoService.criarPagamento(MOCK_PGTO_CREDITO));
    }

    @Test
    public void criarPagamentoDebitoDeveRetornarOk() throws Exception{
        assertEquals(retornaObjetoPagamentoEntityDebito(), pagamentoService.criarPagamento(MOCK_PGTO_DEBITO));
    }

    private PagamentoEntity retornaObjetoPagamentoEntityCredito() {
        return new PagamentoEntity(MOCK_STATUS_CREDITO, MOCK_DATA_CREDITO);
    }

    private PagamentoEntity retornaObjetoPagamentoEntityDebito() {
        return new PagamentoEntity(MOCK_STATUS_DEBITO, MOCK_DATA_DEBITO);
    }

}
