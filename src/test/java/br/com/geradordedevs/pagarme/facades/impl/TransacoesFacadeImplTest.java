package br.com.geradordedevs.pagarme.facades.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.SaldoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.mappers.PagamentoMapper;
import br.com.geradordedevs.pagarme.mappers.TransacoesMapper;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import br.com.geradordedevs.pagarme.services.TransacoesService;
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

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
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

    private static final Long ID_TRANSACAO = 1L;
    private static final BigDecimal VALOR = BigDecimal.valueOf(100.0);
    private static final String DESCRICAO = "Vinho";
    private static final MetodoPagamentoEnum PAGAMENTO_CREDITO = MetodoPagamentoEnum.CREDIT_CARD;
    private static final MetodoPagamentoEnum PAGAMENTO_DEBITO = MetodoPagamentoEnum.DEBIT_CARD;
    private static final String NUMERO_CARTAO = "**** **** **** 1111";
    private static final String NOME_PORTADOR = "JOSE SILVA";
    private static final String VALIDADE_CARTAO = "10/23";
    private static final String CVV = "123";
    private static final Long ID_PGTO = 1L;
    private static final StatusPagamentoEnum STATUS_PAGO = StatusPagamentoEnum.PAID;
    private static final StatusPagamentoEnum STATUS_ESPERA = StatusPagamentoEnum.WAITING_FUNDS;
    private static final LocalDate DATA_DEBITO = LocalDate.now();
    private static final LocalDate DATA_CREDITO = LocalDate.now().plusDays(30);

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transacoesService.listarTransacoes()).thenReturn(retornaListaDeTransacoesEntity());
        when(transacoesService.cadastrarTransacao(retornaObjetoTransacoesEntityDebito())).thenReturn((retornaObjetoTransacoesEntityDebito()));
        when(transacoesService.cadastrarTransacao(retornaObjetoTransacoesEntityCredito())).thenReturn((retornaObjetoTransacoesEntityCredito()));

        when(mapper.paraListaDTO(retornaListaDeTransacoesEntity())).thenReturn(retornaListaDeTransacoesResponseDTO());
        when(mapper.paraDTO(retornaObjetoTransacoesEntityDebito())).thenReturn(retornaObjetoTransacoesResponseDTODebito());
        when(mapper.paraDTO(retornaObjetoTransacoesEntityCredito())).thenReturn(retornaObjetoTransacoesResponseDTOCredito());
        when(mapper.paraEntidade(retornaObjetoTransacoesRequestDTODebito())).thenReturn(retornaObjetoTransacoesEntityDebito());
        when(mapper.paraEntidade(retornaObjetoTransacoesRequestDTOCredito())).thenReturn(retornaObjetoTransacoesEntityCredito());

        when(pagamentoMapper.paraDTO(retornaObjetoPagamentoDebitoEntity())).thenReturn(retornaObjetoPagamentoDebitoResponseDTO());
    }

    @Test
    public void listarTransacoesDeveRetornarOk() throws Exception{
        assertEquals(retornaListaDeTransacoesResponseDTO(), transacoesFacade.listaTransacoes());
    }

    @Test
    public void cadastrarTransacaoDebitoDeveRetornarOk() throws Exception{
        assertEquals(retornaObjetoTransacoesResponseDTODebito(), transacoesFacade.cadastrarTransacao(retornaObjetoTransacoesRequestDTODebito()));
    }

    @Test
    public void cadastrarTransacaoCreditoDeveRetornarOk() throws Exception{
        assertEquals(retornaObjetoTransacoesResponseDTOCredito(), transacoesFacade.cadastrarTransacao(retornaObjetoTransacoesRequestDTOCredito()));
    }

    @Test
    public void deletarTransacaoDeveRetornarOk() throws Exception{
        transacoesFacade.deletarTransacao(ID_TRANSACAO);
        verify(transacoesService, timeout(1)).deletarTransacao(ID_TRANSACAO);
    }

    @Test
    public void consultarSaldoDeveRetornarOk() throws Exception{
        assertEquals(retornaConsultaSaldoResponseDTO(), transacoesFacade.consultarSaldo(VALOR));
    }


    private SaldoResponseDTO retornaConsultaSaldoResponseDTO() {
        BigDecimal saldoCredito = VALOR.subtract(VALOR.multiply(BigDecimal.valueOf(0.05)));
        BigDecimal saldoDebito = VALOR.subtract(VALOR.multiply(BigDecimal.valueOf(0.03)));
        return new SaldoResponseDTO(saldoDebito, saldoCredito);
    }

    private List<TransacoesResponseDTO> retornaListaDeTransacoesResponseDTO() {
        List<TransacoesResponseDTO> lista = new ArrayList<>();
        lista.add(retornaObjetoTransacoesResponseDTODebito());
        lista.add(retornaObjetoTransacoesResponseDTOCredito());
        return lista;
    }

    private List<TransacoesEntity> retornaListaDeTransacoesEntity() {
        List<TransacoesEntity> lista = new ArrayList<>();
        lista.add(retornaObjetoTransacoesEntityDebito());
        lista.add(retornaObjetoTransacoesEntityCredito());
        return lista;
    }

    private TransacoesEntity retornaObjetoTransacoesEntityDebito() {
        return new TransacoesEntity(ID_TRANSACAO, VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaObjetoPagamentoDebitoEntity());
    }

    private TransacoesEntity retornaObjetoTransacoesEntityCredito() {
        return new TransacoesEntity(ID_TRANSACAO, VALOR, DESCRICAO, PAGAMENTO_CREDITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaObjetoPagamentoCreditoEntity());
    }

    private TransacoesRequestDTO retornaObjetoTransacoesRequestDTODebito() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV);
    }

    private TransacoesRequestDTO retornaObjetoTransacoesRequestDTOCredito() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_CREDITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV);
    }

    private TransacoesResponseDTO retornaObjetoTransacoesResponseDTODebito() {
        return new TransacoesResponseDTO(ID_TRANSACAO, VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaObjetoPagamentoDebitoResponseDTO());
    }

    private TransacoesResponseDTO retornaObjetoTransacoesResponseDTOCredito() {
        return new TransacoesResponseDTO(ID_TRANSACAO, VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaObjetoPagamentoCreditoResponseDTO());
    }

    private PagamentoEntity retornaObjetoPagamentoDebitoEntity() {
        return new PagamentoEntity(ID_PGTO, STATUS_PAGO, DATA_DEBITO);
    }

    private PagamentoEntity retornaObjetoPagamentoCreditoEntity() {
        return new PagamentoEntity(ID_PGTO, STATUS_ESPERA, DATA_CREDITO);
    }

    private PagamentoResponseDTO retornaObjetoPagamentoDebitoResponseDTO() {
        return new PagamentoResponseDTO(STATUS_PAGO, DATA_DEBITO);
    }

    private PagamentoResponseDTO retornaObjetoPagamentoCreditoResponseDTO() {
        return new PagamentoResponseDTO(STATUS_ESPERA, DATA_CREDITO);
    }

}
