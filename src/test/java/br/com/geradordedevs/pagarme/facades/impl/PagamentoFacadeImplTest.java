package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.mapper.PagamentoMapper;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PagamentoFacadeImplTest {
    @InjectMocks
    private PagamentoFacadeImpl pagamentoFacade;
    @Mock
    private PagamentoService pagamentoService;
    @Mock
    private PagamentoMapper mapper;

    private static final Long MOCK_ID = 1L;
    private static final StatusPagamentoEnum MOCK_STATUS = StatusPagamentoEnum.PAID;
    private static final LocalDateTime MOCK_DATA = LocalDateTime.now();
    private static final MetodoPagamentoEnum MOCK_METODO_PGTO = MetodoPagamentoEnum.DEBIT_CARD;

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(pagamentoService.salvarPagamento(retornaObjetoPagamentoEntity())).thenReturn(retornaObjetoPagamentoEntity());
        when(pagamentoService.criarPagamento(MOCK_METODO_PGTO)).thenReturn(retornaObjetoPagamentoEntity());

        when(mapper.paraDTO(retornaObjetoPagamentoEntity())).thenReturn(retornaObjetoPagamentoResponseDTO());
    }

    @Test
    public void criarPagamentoDeveRetornarOk() throws Exception{
        assertEquals(retornaObjetoPagamentoResponseDTO(), pagamentoFacade.criarPagamento(MOCK_METODO_PGTO));
    }

    private PagamentoResponseDTO retornaObjetoPagamentoResponseDTO() {
        return new PagamentoResponseDTO(MOCK_STATUS, MOCK_DATA);
    }

    private PagamentoEntity retornaObjetoPagamentoEntity() {
        return new PagamentoEntity(MOCK_ID, MOCK_STATUS, MOCK_DATA);
    }

}

