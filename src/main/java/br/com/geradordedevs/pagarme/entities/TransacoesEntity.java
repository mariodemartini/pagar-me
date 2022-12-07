package br.com.geradordedevs.pagarme.entities;

import br.com.geradordedevs.pagarme.enums.MetodoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacoesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal valorTransacao;
    private String descricaoTransacao;
    private MetodoPagamentoEnum metodoPagamentoEnum;
    private String numeroCartao;
    private String nomePortador;
    private String validadeCartao;
    private String cvv;
    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    private PagamentoEntity pagamento;

}
