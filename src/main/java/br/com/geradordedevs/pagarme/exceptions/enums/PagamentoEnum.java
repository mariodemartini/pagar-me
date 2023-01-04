package br.com.geradordedevs.pagarme.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PagamentoEnum {
    PAGAMENTO_INVALIDO("PAGAMENTO_INVALIDO", "Opção da pagamento invalido", 400);
    private String code;
    private String message;
    private Integer statusCode;
}
