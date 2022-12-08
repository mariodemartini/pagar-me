package br.com.geradordedevs.pagarme.dtos.responses;

import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacoesResponseDTO {
    private Long id;
    private BigDecimal valorTransacao;
    private String descricaoTransacao;
    private MetodoPagamentoEnum metodoPagamentoEnum;
    private String numeroCartao;
    private String nomePortador;
    private String validadeCartao;
    private String cvv;
    private Long pagamento;
}
