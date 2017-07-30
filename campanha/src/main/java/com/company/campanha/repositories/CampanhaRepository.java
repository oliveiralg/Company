package com.company.campanha.repositories;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.company.campanha.models.Campanha;

@Transactional
public interface CampanhaRepository extends MongoRepository<Campanha, String> {


  public Campanha findById(String id);

  @Query("{'vigenciaInicio' : { '$e' : ?0 }, 'vigenciaFim' : { '$gte' : ?1 }}")
  public List<Campanha> findByVigenciaInicio(LocalDate vigenciaInicio, LocalDate currentDate);

  @Query("{'vigenciaFim' : { '$e' : ?0 }, 'vigenciaFim' : { '$gte' : ?1 }}")
  public List<Campanha> findByVigenciaFim(LocalDate vigenciaFim, LocalDate currentDate);

  @Query("{'timeCoracao' : { '$e' : ?0 }, 'vigenciaFim' : { '$gte' : ?1 }}")
  public List<Campanha> findByTimeCoracao(String timeCoracao, LocalDate currentDate);

  public List<Campanha> findByNome(String nome);

  @Query("{'$or':[ { 'VigenciaInicio' : { $gte: ?0, $lte: ?1 } }, { 'VigenciaFim' : { $gte: ?0, $lte: ?1 } } ] }")
  public List<Campanha> findByVigencia(LocalDate vigenciaInicio, LocalDate vigenciaFim);

}
