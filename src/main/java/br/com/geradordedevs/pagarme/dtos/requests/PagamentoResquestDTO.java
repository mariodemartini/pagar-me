package br.com.geradordedevs.pagarme.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResquestDTO {
    private String status;
    private LocalDate dataPagamento;
}
