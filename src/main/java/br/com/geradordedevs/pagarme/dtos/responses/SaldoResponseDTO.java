package br.com.geradordedevs.pagarme.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaldoResponseDTO {

    @JsonProperty("valor_recebido")
    private BigDecimal saldoDebito;

    @JsonProperty("valor_a_receber")
    private BigDecimal saldoCredito;
}
