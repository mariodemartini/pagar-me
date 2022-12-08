package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.dtos.requests.TransacoesRequestDTO;
import br.com.geradordedevs.pagarme.dtos.responses.TransacoesResponseDTO;
import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.mapper.TransacoesMapper;
import br.com.geradordedevs.pagarme.repositories.TransacoesRepository;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TransacoesServiceImpl implements TransacoesService {

    @Autowired
    private TransacoesRepository transacoesRepository;
    @Autowired
    private TransacoesMapper transacoesMapper;
    @Override
    public List<TransacoesResponseDTO> listaTransacoes() {
        log.info("listando as transações");
        List<TransacoesEntity> transacoesEntities = new ArrayList<>();
        for (TransacoesEntity transacoesEntity : transacoesRepository.findAll()) {
            transacoesEntities.add(transacoesEntity);
        }
        return transacoesMapper.paraListaDTO(transacoesEntities);
    }

    @Override
    public TransacoesResponseDTO cadastrarTransacao(TransacoesRequestDTO requestDTO){
        log.info("cadastrando nova transação: {}", requestDTO);
        return transacoesMapper.paraDTO(transacoesRepository.save(transacoesMapper.paraEntidade(requestDTO)));
    }

}
