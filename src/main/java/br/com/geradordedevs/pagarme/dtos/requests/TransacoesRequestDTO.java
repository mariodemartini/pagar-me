package br.com.geradordedevs.pagarme.dtos.requests;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacoesRequestDTO {
    private BigDecimal valorTransacao;
    private String descricaoTransacao;
    private MetodoPagamentoEnum metodoPagamento;
    private String numeroCartao;
    private String nomePortador;
    private String validadeCartao;
    private String cvv;
    private PagamentoEntity pagamento;

}
