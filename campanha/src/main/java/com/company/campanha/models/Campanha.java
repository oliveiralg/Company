package com.company.campanha.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Campanha {

  @Id
  String id;
  String nome;
  String timeCoracao;
  Date vigenciaInicio;
  Date vigenciaFim;

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

  public Date getVigenciaInicio() {
    return vigenciaInicio;
  }

  public void setVigenciaInicio(Date vigenciaInicio) {
    this.vigenciaInicio = vigenciaInicio;
  }

  public Date getVigenciaFim() {
    return vigenciaFim;
  }

  public void setVigenciaFim(Date vigenciaFim) {
    this.vigenciaFim = vigenciaFim;
  }



}
