package br.com.geradordedevs.pagarme.dtos.requests;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotNull(message = "campo obrigatorio")
    private BigDecimal valorTransacao;
    @NotBlank(message = "campo obrigatorio")
    private String descricaoTransacao;
    @NotNull(message = "campo obrigatorio")
    private MetodoPagamentoEnum metodoPagamento;
    @NotBlank(message = "campo obrigatorio")
    @Size(min = 19, max = 19, message = "numero do cartao invalido")
    private String numeroCartao;
    @NotBlank(message = "campo obrigatorio")
    private String nomePortador;
    @NotNull(message = "campo obrigatorio")
    private String validadeCartao;
    @NotBlank(message = "campo obrigatorio")
    @Size(min = 3, max = 3, message = "codigo de segurança invalido")
    private String cvv;
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
