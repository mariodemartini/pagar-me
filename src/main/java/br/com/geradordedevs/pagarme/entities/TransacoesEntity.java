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
    private MetodoPagamentoEnum metodoPagamento;
    private String numeroCartao;
    private String nomePortador;
    private String validadeCartao;
    private String cvv;
    @ManyToOne
    private PagamentoEntity pagamento;

    public TransacoesEntity(BigDecimal valorTransacao, String descricaoTransacao, MetodoPagamentoEnum metodoPagamento, String numeroCartao, String nomePortador, String validadeCartao, String cvv, PagamentoEntity pagamento) {
        this.valorTransacao = valorTransacao;
        this.descricaoTransacao = descricaoTransacao;
        this.metodoPagamento = metodoPagamento;
        this.numeroCartao = numeroCartao;
        this.nomePortador = nomePortador;
        this.validadeCartao = validadeCartao;
        this.cvv = cvv;
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "TransacoesEntity{" +
                "id=" + id +
                ", valorTransacao=" + valorTransacao +
                ", descricaoTransacao='" + descricaoTransacao + '\'' +
                ", metodoPagamento=" + metodoPagamento +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", nomePortador='" + nomePortador + '\'' +
                ", validadeCartao='" + validadeCartao + '\'' +
                ", pagamento=" + pagamento +
                '}';
    }
}
