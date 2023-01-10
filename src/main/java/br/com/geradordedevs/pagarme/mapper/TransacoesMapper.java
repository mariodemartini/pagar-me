package br.com.geradordedevs.pagarme.mapper;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransacoesMapper {
    @Autowired
    private final ModelMapper mapper;

    @Autowired
    private PagamentoService pagamentoService;

    public TransacoesResponseDTO paraDTO(TransacoesEntity transacoes){
        log.info("convertendo entidade para DTO: {}", transacoes);
        return mapper.map(transacoes, TransacoesResponseDTO.class);
    }

    public TransacoesEntity paraEntidade(TransacoesRequestDTO requestDTO){
        log.info("convertendo DTO para entidade: {}", requestDTO);
        TransacoesEntity transacoesEntity = mapper.map(requestDTO, TransacoesEntity.class);
        PagamentoEntity pagamentoEntity = pagamentoService.criarPagamento(transacoesEntity.getMetodoPagamento());
        transacoesEntity.setPagamento(pagamentoEntity);
        return transacoesEntity;
    }

    public List<TransacoesResponseDTO> paraListaDTO(Iterable<TransacoesEntity> lista){
        log.info("convertendo lista entidade para lista DTO: {}", lista);
        List<TransacoesEntity> resultado = new ArrayList<>();
        lista.forEach(resultado::add);
        return resultado.stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }
}
