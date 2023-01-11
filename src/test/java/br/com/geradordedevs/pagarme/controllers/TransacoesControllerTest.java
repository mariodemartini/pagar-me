package br.com.geradordedevs.pagarme.controllers;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import br.com.geradordedevs.pagarme.facades.TransacoesFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
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

    private static final String ROTA_TRANSACOES = "/transacoes";
    private static final String ROTA_TRANSACOES_COM_PARAMENTRO = "/transacoes/1";
    private static final String ROTA_SALDO_TRANSACOES = "/transacoes/saldo";

    private static final BigDecimal VALOR = BigDecimal.valueOf(100.00);
    private static final String DESCRICAO = "Vinho";
    private static final MetodoPagamentoEnum PAGAMENTO_DEBITO = MetodoPagamentoEnum.DEBIT_CARD;
    private static final String NUMERO_CARTAO = "**** **** **** 1111";
    private static final String NOME_PORTADOR = "JOSE SILVA";
    private static final String VALIDADE_CARTAO = "10/23";
    private static final String CVV = "123";

    @Test
    public void listarTransacoesDeveRetornarOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(ROTA_TRANSACOES)).andExpect(status().isOk());
    }

    @Test
    public void deletarTransacaoDeveRetornarOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(delete(ROTA_TRANSACOES_COM_PARAMENTRO))
                .andExpect(status().isOk());
    }

    @Test
    public void consultarSaldoDeveRetornarOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(get(ROTA_SALDO_TRANSACOES)).andExpect(status().isOk());
    }

    @Test
    public void cadastrarTransacaoDeveRetornarOk() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTOCorreto())))
                .andExpect(status().isOk());
    }
    @Test
    public void cadastrarTransacaoValorNuloDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTOValorNulo())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoDescricaoNuloDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTODescricaoNulo())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoPagamentoNuloDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTOPagamentoNulo())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoNumeroCartaoNuloDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTONumeroCartaoNulo())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoNomePortadorNuloDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTONomePortadorNulo())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoValidadeCartaoNuloDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTOValidadeCartaoNulo())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoCvvNuloDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTOCvvNulo())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoCvvMaiorDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTOCvvMaior())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarTransacaoCvvMenorDeveRetornarBadRequest() throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc.perform(post(ROTA_TRANSACOES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(retornTransacoesRequestDTOCvvMenor())))
                .andExpect(status().isBadRequest());
    }

    private TransacoesRequestDTO retornTransacoesRequestDTOCorreto() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR, VALIDADE_CARTAO, CVV);
    }

    private TransacoesRequestDTO retornTransacoesRequestDTOValorNulo() {
        return new TransacoesRequestDTO(null, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR, VALIDADE_CARTAO, CVV);
    }

    private TransacoesRequestDTO retornTransacoesRequestDTODescricaoNulo() {
        return new TransacoesRequestDTO(VALOR, "", PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR, VALIDADE_CARTAO, CVV);
    }

    private TransacoesRequestDTO retornTransacoesRequestDTOPagamentoNulo() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, null, NUMERO_CARTAO, NOME_PORTADOR, VALIDADE_CARTAO, CVV);
    }

    private TransacoesRequestDTO retornTransacoesRequestDTONumeroCartaoNulo() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, "", NOME_PORTADOR, VALIDADE_CARTAO, CVV);
    }

    private TransacoesRequestDTO retornTransacoesRequestDTONomePortadorNulo() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, "", VALIDADE_CARTAO, CVV);
    }

    private TransacoesRequestDTO retornTransacoesRequestDTOValidadeCartaoNulo() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR, null, CVV);
    }

    private TransacoesRequestDTO retornTransacoesRequestDTOCvvNulo() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR, VALIDADE_CARTAO, "");
    }

    private TransacoesRequestDTO retornTransacoesRequestDTOCvvMaior() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR, VALIDADE_CARTAO, "1234");
    }

    private TransacoesRequestDTO retornTransacoesRequestDTOCvvMenor() {
        return new TransacoesRequestDTO(VALOR, DESCRICAO, PAGAMENTO_DEBITO, NUMERO_CARTAO, NOME_PORTADOR, VALIDADE_CARTAO, "12");
    }

}
