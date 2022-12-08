package br.com.geradordedevs.pagarme.mapper;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransacoesMapper {
    @Autowired
    private final ModelMapper mapper;

    public TransacoesResponseDTO paraDTO(TransacoesEntity transacoes){
        return mapper.map(transacoes, TransacoesResponseDTO.class);
    }

    public TransacoesEntity paraEntidade(TransacoesRequestDTO requestDTO){
        return mapper.map(requestDTO, TransacoesEntity.class);
    }

    public List<TransacoesResponseDTO> paraListaDTO(List<TransacoesEntity> lista){
        return lista.stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }
}
