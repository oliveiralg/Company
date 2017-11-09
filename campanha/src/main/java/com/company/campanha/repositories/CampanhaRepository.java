package com.company.campanha.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.company.campanha.models.Campanha;

@Transactional
public interface CampanhaRepository extends MongoRepository<Campanha, String> {

  public Campanha findById(String id);

  @Query("{'$and':[ {'vigenciaInicio' : ?0 }, {'vigenciaFim' : { '$gte' : ?1 } } ] }")
  public List<Campanha> buscaPorVigenciaInicio(LocalDate vigenciaInicio,
      LocalDate currentLocalDate);
  
  @Query("{'$and':[ {'vigenciaFim' : ?0 }, {'vigenciaFim' : { '$gte' : ?1 } } ] }")
  public List<Campanha> buscaPorVigenciaFim(LocalDate vigenciaFim, LocalDate currentLocalDate);

  @Query("{'$and':[ {'timeCoracao' : ?0 }, {'vigenciaFim' : { '$gte' : ?1 } } ] }")
  public List<Campanha> buscaPorTimeCoracao(String timeCoracao, LocalDate currentLocalDate);

  @Query("{'$or':[ { 'vigenciaInicio' : { $gte: ?0, $lte: ?1 } }, { 'vigenciaFim' : { $gte: ?0, $lte: ?1 } } ] }")
  public List<Campanha> buscaPorVigencia(LocalDate vigenciaInicio, LocalDate vigenciaFim, Sort sort);

}
