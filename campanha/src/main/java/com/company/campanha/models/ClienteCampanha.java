package com.company.campanha.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ClienteCampanha {

  String clienteId;
  String campanhaId;
  boolean alterou;

  public String getClienteId() {
    return clienteId;
  }

  public void setClienteId(String clienteId) {
    this.clienteId = clienteId;
  }

  public String getCampanhaId() {
    return campanhaId;
  }

  public void setCampanhaId(String campanhaId) {
    this.campanhaId = campanhaId;
  }

  public boolean isAlterou() {
    return alterou;
  }

  public void setAlterou(boolean alterou) {
    this.alterou = alterou;
  }
}
