package br.com.geradordedevs.pagarme.repositories;

import br.com.geradordedevs.pagarme.entities.TransacoesEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransacoesRepository extends CrudRepository<TransacoesEntity, Long> {
}
