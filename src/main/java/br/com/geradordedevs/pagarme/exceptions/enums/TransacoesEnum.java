package br.com.geradordedevs.pagarme.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransacoesEnum {
    TRANSACAO_INVALIDA("TRANSACAO_INVALIDA", "Dados invalidos", 400);

    private String code;
    private String message;
    private Integer statusCode;

}
