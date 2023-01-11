package br.com.geradordedevs.pagarme.service.impl;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.repositories.PagamentoRepository;
import br.com.geradordedevs.pagarme.services.impl.PagamentoServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PagamentoServiceImplTest {

    @InjectMocks
    private PagamentoServiceImpl pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;


    private static final MetodoPagamentoEnum MOCK_PGTO_CREDITO = MetodoPagamentoEnum.CREDIT_CARD;
    private static final Long MOCK_ID_PGTO = 1L;
    private static final StatusPagamentoEnum MOCK_STATUS_CREDITO = StatusPagamentoEnum.WAITING_FUNDS;
    private static final LocalDate MOCK_DATA_CREDITO = LocalDate.now().plusDays(30);

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(pagamentoRepository.save(retornaObjetoPagamentoEntity())).thenReturn(retornaObjetoPagamentoEntity());
    }

    @Test
    public void criarPagamentoDeveRetornarOk() throws Exception{
        assertEquals(retornaObjetoPagamentoEntity(), pagamentoService.criarPagamento(MOCK_PGTO_CREDITO));
    }

    private PagamentoEntity retornaObjetoPagamentoEntity() {
        return new PagamentoEntity(MOCK_STATUS_CREDITO, MOCK_DATA_CREDITO);
    }

}
