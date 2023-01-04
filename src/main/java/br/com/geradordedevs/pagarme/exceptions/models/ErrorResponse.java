package br.com.geradordedevs.pagarme.exceptions.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private Long timestamp;
    private Integer status;
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ErrorObject> erros;

}


