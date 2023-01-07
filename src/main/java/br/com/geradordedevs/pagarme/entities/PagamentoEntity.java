package br.com.geradordedevs.pagarme.entities;

import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private StatusPagamentoEnum status;
    private LocalDate dataPagamento;

    public PagamentoEntity(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
