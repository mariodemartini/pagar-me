package br.com.geradordedevs.pagarme.mapper;

import br.com.geradordedevs.pagarme.dtos.requests.PagamentoResquestDTO;
import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.PagamentoResponseDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PagamentoMapper {
    @Autowired
    private final ModelMapper mapper;

    public PagamentoResponseDTO paraDTO(PagamentoEntity pagamento){
        return mapper.map(pagamento, PagamentoResponseDTO.class);
    }

    public PagamentoEntity paraEntity(PagamentoResquestDTO resquestDTO){
        return mapper.map(resquestDTO, PagamentoEntity.class);
    }


}
