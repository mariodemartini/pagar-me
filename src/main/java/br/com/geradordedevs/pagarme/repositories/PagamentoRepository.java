package br.com.geradordedevs.pagarme.repositories;

import br.com.geradordedevs.pagarme.entities.PagamentoEntity;
import org.springframework.data.repository.CrudRepository;

public interface PagamentoRepository extends CrudRepository<PagamentoEntity, Long> {
}
