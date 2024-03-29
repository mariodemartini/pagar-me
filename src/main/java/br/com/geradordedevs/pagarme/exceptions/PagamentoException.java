package br.com.geradordedevs.pagarme.exceptions;

import br.com.geradordedevs.pagarme.exceptions.enums.PagamentoEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class PagamentoException extends PagarmeException{
    private static final long serialVersionUID = -4589179341768493322L;
    private final PagamentoEnum error;

    public PagamentoException(PagamentoEnum error){
        super(error.getMessage());
        this.error = error;
    }


}
