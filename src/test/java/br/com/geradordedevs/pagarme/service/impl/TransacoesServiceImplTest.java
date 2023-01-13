package br.com.geradordedevs.pagarme.service.impl;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.repositories.TransacoesRepository;
import br.com.geradordedevs.pagarme.services.impl.TransacoesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransacoesServiceImplTest {
    @InjectMocks
    private TransacoesServiceImpl transacoesService;

    @Mock
    private TransacoesRepository transacoesRepository;

    private static final Long MOCK_ID = 1L;
    private static final BigDecimal MOCK_VALOR = new BigDecimal(100.00);
    private static final String MOCK_DESCRICAO = "vinho";
    private static final MetodoPagamentoEnum MOCK_PGTO_CREDITO = MetodoPagamentoEnum.CREDIT_CARD;
    private static final String MOCK_NUMERO_CARTAO = "1111 1111 1111 1111";
    private static final String MOCK_NOME_PROTADOR = "JOSE SILVA";
    private static final String MOCK_DATA_VALIDADE = "12/26";
    private static final String MOCK_CVV = "123";
    private static final StatusPagamentoEnum MOCK_STATUS_WAITING = StatusPagamentoEnum.WAITING_FUNDS;
    private static final LocalDate MOCK_DATA_PGTO = LocalDate.now().plusDays(30);

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transacoesRepository.findAll()).thenReturn(retornaListaTransacoesEntity());
        when(transacoesRepository.save(retornaObjetoTransacoesEntitySemId())).thenReturn(retornaObjetoTransacoesEntityComId());
    }

    @Test
    public void listarTransacoesDeveRetornarOk() throws Exception{
        assertEquals(retornaListaTransacoesEntity(), transacoesService.listarTransacoes());
    }

    @Test
    public void cadastrarTransacaoDeveRetornarOk() throws Exception{
        assertEquals(retornaObjetoTransacoesEntityComId(), transacoesService.cadastrarTransacao(retornaObjetoTransacoesEntitySemId()));
    }

    @Test
    public void removerTransacaoDeveRetornarOk() throws Exception{
        transacoesService.deletarTransacao(MOCK_ID);
        verify(transacoesRepository, timeout(1)).deleteById(MOCK_ID);
    }

    private List<TransacoesEntity> retornaListaTransacoesEntity() {
        List<TransacoesEntity> listaTransacoes = new ArrayList<>();
        listaTransacoes.add(retornaObjetoTransacoesEntityComId());
        return listaTransacoes;
    }

    private TransacoesEntity retornaObjetoTransacoesEntitySemId() {
        return new TransacoesEntity(MOCK_VALOR, MOCK_DESCRICAO, MOCK_PGTO_CREDITO, MOCK_NUMERO_CARTAO, MOCK_NOME_PROTADOR,
                MOCK_DATA_VALIDADE, MOCK_CVV, retornaObjetoPagamentoCreditoEntity());
    }

    private TransacoesEntity retornaObjetoTransacoesEntityComId() {
        return new TransacoesEntity(MOCK_ID, MOCK_VALOR, MOCK_DESCRICAO, MOCK_PGTO_CREDITO, MOCK_NUMERO_CARTAO, MOCK_NOME_PROTADOR,
                MOCK_DATA_VALIDADE, MOCK_CVV, retornaObjetoPagamentoCreditoEntity());
    }

    private PagamentoEntity retornaObjetoPagamentoCreditoEntity() {
        return new PagamentoEntity(MOCK_STATUS_WAITING, MOCK_DATA_PGTO);
    }

}
