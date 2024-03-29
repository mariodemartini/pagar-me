package br.com.geradordedevs.pagarme.exceptions;

import br.com.geradordedevs.pagarme.exceptions.enums.TransacoesEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransacoesException extends PagarmeException{
    private static final long serialVersionUID = -4589179341768493322L;
    private final TransacoesEnum error;

    public TransacoesException(TransacoesEnum error){
        super(error.getMessage());
        this.error = error;
    }

}
