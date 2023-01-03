package br.com.geradordedevs.pagarme.services.impl;

import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import br.com.geradordedevs.pagarme.repositories.TransacoesRepository;
import br.com.geradordedevs.pagarme.services.TransacoesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransacoesServiceImpl implements TransacoesService {
    @Autowired
    private TransacoesRepository transacoesRepository;

    @Override
    public Iterable<TransacoesEntity> listarTransacoes() {
        log.info("listando as transações");
        return transacoesRepository.findAll();
    }

    @Override
    public TransacoesEntity cadastrarTransacao(TransacoesEntity transacoes){
        log.info("cadastrando nova transação: {}",transacoes);
        return transacoesRepository.save(transacoes);
    }

    @Override
    public void deletarTransacao(Long id) {
        log.info("removendo transação id {}", id);
        transacoesRepository.deleteById(id);
    }
}
