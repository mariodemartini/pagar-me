package br.com.geradordedevs.pagarme.entities;

import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

}
