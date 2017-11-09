package com.company.campanha.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Document
public class Cliente {

  @Id
  String id;
  String nome;
  String email;
  @JsonSerialize(using = ToStringSerializer.class)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  LocalDate dataNascimento;
  String timeCoracao;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    LocalDate currentDate = LocalDate.now();
    if (dataNascimento.compareTo(currentDate) > 0)
      throw new IllegalArgumentException("Data nascimento maior que data atual");
    this.dataNascimento = dataNascimento;
  }

  public String getTimeCoracao() {
    return timeCoracao;
  }

  public void setTimeCoracao(String timeCoracao) {
    this.timeCoracao = timeCoracao;
  }
}
