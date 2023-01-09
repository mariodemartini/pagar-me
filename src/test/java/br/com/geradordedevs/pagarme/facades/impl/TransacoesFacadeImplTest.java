package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.mapper.PagamentoMapper;
import br.com.geradordedevs.pagarme.mapper.TransacoesMapper;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TransacoesFacadeImplTest {
    @InjectMocks
    private TransacoesFacadeImpl transacoesFacade;
    @Mock
    private TransacoesService transacoesService;
    @Mock
    private PagamentoService pagamentoService;
    @Mock
    private TransacoesMapper mapper;
    @Mock
    private PagamentoMapper pagamentoMapper;

    private final Long ID_TRANSACAO = 1L;
    private final BigDecimal VALOR = BigDecimal.valueOf(500.00);
    private final String DESCRICAO = "Vinho";
    private final MetodoPagamentoEnum PAGAMENTO_CREDITO = MetodoPagamentoEnum.CREDIT_CARD;
    private final MetodoPagamentoEnum PAGAMENTO_DEBITO = MetodoPagamentoEnum.DEBIT_CARD;
    private final String NUMERO_CARTAO = "1111.1111.1111.1111";
    private final String NOME_PORTADOR = "JOSE SILVA";
    private final String VALIDADE_CARTAO = "10/23";
    private final String CVV = "123";
    private final Long ID_PGTO = 1L;
    private final StatusPagamentoEnum STATUS_PAGO = StatusPagamentoEnum.PAID;
    private final StatusPagamentoEnum STATUS_ESPERA = StatusPagamentoEnum.WAITING_FUNDS;
    private final LocalDate DATA_DEBITO = LocalDate.now();
    private final LocalDate DATA_CREDITO = LocalDate.now().plusDays(30);


    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transacoesService.listarTransacoes()).thenReturn(retornaListaDeTransacoesEntity());
        when(transacoesService.cadastrarTransacao(retornaObjetoTransacoesEntity())).thenReturn((retornaObjetoTransacoesEntity()));

        when(mapper.paraListaDTO(retornaListaDeTransacoesEntity())).thenReturn(retornaListaDeTransacoesResponseDTO());
        when(mapper.paraDTO(retornaObjetoTransacoesEntity())).thenReturn(retornaObjetoTransacoesResponseDTO());
        when(mapper.paraEntidade(retornaObjetoTransacoesRequestDTO())).thenReturn(retornaObjetoTransacoesEntity());
        when(pagamentoMapper.paraDTO(retornaObjetoPagamentoEntity())).thenReturn(retornaObjetoPagamentoResponseDTO());

    }


    @Test
    public void listarTransacoesDeveRetornarOk() throws Exception{
        assertEquals(retornaListaDeTransacoesResponseDTO(), transacoesService.listarTransacoes());
    }

    @Test
    public void cadastrarTransacaoDeveRetornarOk() throws Exception{

    }

    @Test
    public void deletarTransacaoDeveRetornarOk() throws Exception{

    }

    @Test
    public void consultarSaldoDeveRetornarOk() throws Exception{

    }

    private List<TransacoesEntity> retornaListaDeTransacoesEntity() {
        List<TransacoesEntity> lista = new ArrayList<>();
        lista.add(retornaObjetoTransacoesEntity());
        return lista;
    }

    private TransacoesEntity retornaObjetoTransacoesEntity() {
        return new TransacoesEntity(ID_TRANSACAO, VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaObjetoPagamentoEntity());
    }

    private PagamentoEntity retornaObjetoPagamentoEntity() {
        return new PagamentoEntity(ID_PGTO, STATUS_PAGO, DATA_DEBITO);
    }
    private PagamentoResponseDTO retornaObjetoPagamentoResponseDTO() {
        return new PagamentoResponseDTO(STATUS_PAGO, DATA_DEBITO);
    }
    private TransacoesRequestDTO retornaObjetoTransacoesRequestDTO() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaObjetoPagamentoResponseDTO());
    }

    private List<TransacoesResponseDTO> retornaListaDeTransacoesResponseDTO() {
        List<TransacoesResponseDTO> lista = new ArrayList<>();
        lista.add(retornaObjetoTransacoesResponseDTO());
        return lista;
    }

    private TransacoesResponseDTO retornaObjetoTransacoesResponseDTO() {
        return new TransacoesResponseDTO(ID_TRANSACAO, VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaObjetoPagamentoResponseDTO());
    }


}
