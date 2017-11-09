package com.company.campanha.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.company.campanha.models.Cliente;

@Transactional
public interface ClienteRepository extends MongoRepository<Cliente, String> {

  public Cliente findById(String id);
  public Cliente findByEmail(String email);
  
}
