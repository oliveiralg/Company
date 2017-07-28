package com.company.campanha.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.company.campanha.models.Campanha;

@Transactional
public interface CampanhaRepository extends MongoRepository<Campanha, String> {

  public Campanha findById(String id);
  public List<Campanha> findByVigenciaInicio(Date vigenciaInicio);
  public List<Campanha> findByVigenciaFim(Date vigenciaFim);
  public List<Campanha> findByTimeCoracao(String timeCoracao);
  public List<Campanha> findByNome(String nome);
}
