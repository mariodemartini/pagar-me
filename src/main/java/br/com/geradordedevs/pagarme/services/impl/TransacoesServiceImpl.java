package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
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
    @Override
    public List<TransacoesEntity> listaTransacoes() {
        log.info("listando as transações");
        List<TransacoesEntity> transacoesEntities = new ArrayList<>();
        for (TransacoesEntity transacoesEntity : transacoesRepository.findAll()) {
            transacoesEntities.add(transacoesEntity);
        }
        return transacoesEntities;
    }

    @Override
    public TransacoesEntity cadastrarTransacao(TransacoesEntity transacoesEntity){
        log.info("cadastrando nova transação: {}", transacoesEntity);
        return transacoesRepository.save(transacoesEntity);
    }
}
