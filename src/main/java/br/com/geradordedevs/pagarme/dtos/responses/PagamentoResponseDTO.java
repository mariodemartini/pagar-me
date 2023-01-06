package br.com.geradordedevs.pagarme.dtos.responses;

import br.com.geradordedevs.pagarme.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponseDTO {
    private StatusPagamentoEnum status;
    private LocalDateTime dataPagamento;
}
