package br.com.geradordedevs.pagarme.mappers;

import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagamentoMapper {

    @Autowired
    private final ModelMapper mapper;

    public PagamentoResponseDTO paraDTO(PagamentoEntity pagamento){
        log.info("convertendo entidade para DTO: {}", pagamento);
        return mapper.map(pagamento, PagamentoResponseDTO.class);
    }

}
