package com.company.campanha.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.company.campanha.models.ClienteCampanha;

@Transactional
public interface ClienteCampanhaRepository extends MongoRepository<ClienteCampanha, String> {

  public List<ClienteCampanha> findByCampanhaId(String campanhaId);

}
