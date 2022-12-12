package br.com.geradordedevs.pagarme.mapper;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagamentoMapper {
    @Autowired
    private final ModelMapper mapper;

    public PagamentoResponseDTO paraDTO(PagamentoEntity pagamento){
        return mapper.map(pagamento, PagamentoResponseDTO.class);
    }

}
