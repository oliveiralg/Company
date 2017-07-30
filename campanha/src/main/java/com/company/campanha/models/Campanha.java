package com.company.campanha.models;


import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Campanha {

  @Id
  String id;
  String nome;
  String timeCoracao;
  LocalDate vigenciaInicio;
  LocalDate vigenciaFim;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTimeCoracao() {
    return timeCoracao;
  }

  public void setTimeCoracao(String timeCoracao) {
    this.timeCoracao = timeCoracao;
  }

  public LocalDate getVigenciaInicio() {
    return vigenciaInicio;
  }

  public void setVigenciaInicio(LocalDate vigenciaInicio) {
    LocalDate currentDate = LocalDate.now();
    if (vigenciaInicio.compareTo(currentDate) < 0)
      throw new IllegalArgumentException("Data início de vigência menor que data atual");
    this.vigenciaInicio = vigenciaInicio;
  }

  public LocalDate getVigenciaFim() {
    return vigenciaFim;
  }

  public void setVigenciaFim(LocalDate vigenciaFim) {
    this.vigenciaFim = vigenciaFim;
  }



}
