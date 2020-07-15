package br.com.projeto.cotacaomoeda.repository;

import br.com.projeto.cotacaomoeda.entity.Moeda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoedaRepository extends MongoRepository<Moeda, Integer> {

}
