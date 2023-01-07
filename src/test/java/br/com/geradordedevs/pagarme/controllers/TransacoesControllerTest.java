package br.com.geradordedevs.pagarme.controllers;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.facades.TransacoesFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransacoesController.class)
@AutoConfigureMockMvc
public class TransacoesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacoesFacade transacoesFacade;

    private final String ROTA_TRANSACOES = "/transacoes";
    private final String ROTA_TRANSACOES_COM_PARAMETRO = "/transacoes/1";
    private final String ROTA_SALDO = "/transacoes/saldo";

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
    private final LocalDateTime DATA_DEBITO = LocalDateTime.now();
    private final LocalDateTime DATA_CREDITO = LocalDateTime.now().plusDays(30);

    @Before
    public void setupMock(){
        MockitoAnnotations.openMocks(this);
        when(transacoesFacade.cadastrarTransacao(cadastroDeTransacoesCompletoRequestDTODebito())).thenReturn(cadastroDeTransacoesCompletoResponseDTO());
    }

    @Test
    public void listarTransacoesDeveRetornarOK() throws Exception{
        mockMvc.perform(get(ROTA_TRANSACOES)).andExpect(status().isOk());
    }

    @Test
    public void listarTransacoesComRotaErradaDeveRetornarNotFound() throws Exception{
        mockMvc.perform(get("/transacao")).andExpect(status().isNotFound());
    }

    @Test
    public void deletarTransacaoDeveRetornarOk() throws Exception{
        mockMvc.perform(delete(ROTA_TRANSACOES_COM_PARAMETRO)).andExpect(status().isOk());
    }

    @Test
    public void deletarTransacaoSemParametroDeveRetornarErro() throws Exception{
        mockMvc.perform(delete(ROTA_TRANSACOES)).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void consultarSaldoDeveRetornarOk() throws Exception{
        mockMvc.perform(get(ROTA_SALDO)).andExpect(status().isOk());
    }

    @Test
    public void consultarSaldoComRotaErradaDeveRetornarNotFound() throws Exception{
        mockMvc.perform(get("/saldo")).andExpect(status().isNotFound());
    }

    @Test
    public void cadastrarTransacoesDeveRetornarOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(cadastroDeTransacoesCompletoRequestDTODebito())))
                .andExpect(status().isOk());
    }

    private TransacoesRequestDTO cadastroDeTransacoesCompletoRequestDTODebito() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaPagamentoDTODebito());
    }

    private PagamentoEntity retornaPagamentoEntityDebito() {
        return new PagamentoEntity(ID_PGTO, STATUS_PAGO, DATA_DEBITO);
    }

    private TransacoesResponseDTO cadastroDeTransacoesCompletoResponseDTO() {
        return new TransacoesResponseDTO(ID_TRANSACAO, VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaPagamentoDTODebito());
    }

    private PagamentoResponseDTO retornaPagamentoDTODebito() {
        return new PagamentoResponseDTO(STATUS_PAGO, DATA_DEBITO);
    }
}
