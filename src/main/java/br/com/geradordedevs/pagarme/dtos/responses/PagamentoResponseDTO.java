package br.com.geradordedevs.pagarme.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponseDTO {
    private String status;
    private LocalDate dataPagamento;
}
