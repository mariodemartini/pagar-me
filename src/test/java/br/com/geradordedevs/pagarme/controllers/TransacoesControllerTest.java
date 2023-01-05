package br.com.geradordedevs.pagarme.controllers;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import br.com.geradordedevs.pagarme.facades.TransacoesFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransacoesController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class TransacoesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacoesFacade transacoesFacade;

    private final String ROTA_TRANSACOES = "/transacoes";
    private final String ROTA_TRANSACOES_COM_PARAMETRO = "/transacoes/1";
    private final String ROTA_SALDO = "/transacoes/saldo";
    private final BigDecimal VALOR = BigDecimal.valueOf(500.00);
    private final String DESCRICAO = "Vinho";
    private final MetodoPagamentoEnum METODO_PAGAMENTO = MetodoPagamentoEnum.CREDIT_CARD;
    private final String NUMERO_CARTAO = "1111.1111.1111.1111";
    private final String NOME_PORTADOR = "JOSE SILVA";
    private final String VALIDADE_CARTAO = "10/23";
    private final String CVV = "123";
    private final Long ID_PAGTO = 1L;
    private final StatusPagamentoEnum STATUS = StatusPagamentoEnum.PAID;
    private final LocalDate DATA_PAGAMENTO = LocalDate.now();

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
                .content(ow.writeValueAsString(cadastroDeTransacoesCompleto())))
                .andExpect(status().isOk());
    }

    private TransacoesRequestDTO cadastroDeTransacoesCompleto() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, METODO_PAGAMENTO, NUMERO_CARTAO, NOME_PORTADOR,
                VALIDADE_CARTAO, CVV, retornaPagamento());
    }

    private PagamentoEntity retornaPagamento() {
        return new PagamentoEntity(ID_PAGTO, STATUS.PAID, DATA_PAGAMENTO);
    }

}
