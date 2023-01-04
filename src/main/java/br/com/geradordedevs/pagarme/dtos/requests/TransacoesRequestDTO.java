package br.com.geradordedevs.pagarme.dtos.requests;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacoesRequestDTO {
    @NotNull
    private BigDecimal valorTransacao;
    @NotBlank
    private String descricaoTransacao;
    @NotNull
    private MetodoPagamentoEnum metodoPagamento;
    @NotBlank
    @Size(min = 16, max = 16, message = "numero do cartao invalido")
    private String numeroCartao;
    @NotBlank
    private String nomePortador;
    @NotNull
    private String validadeCartao;
    @Size(min = 3, max = 3, message = "codigo de segurança invalido")
    private String cvv;
    @NotNull
    private PagamentoEntity pagamento;

    @Override
    public String toString() {
        return "TransacoesRequestDTO{" +
                "valorTransacao=" + valorTransacao +
                ", descricaoTransacao='" + descricaoTransacao + '\'' +
                ", metodoPagamento=" + metodoPagamento +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", nomePortador='" + nomePortador + '\'' +
                ", validadeCartao='" + validadeCartao + '\'' +
                ", pagamento=" + pagamento +
                '}';
    }
}
