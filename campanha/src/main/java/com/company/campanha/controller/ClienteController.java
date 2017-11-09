package com.company.campanha.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.campanha.models.Campanha;
import com.company.campanha.models.Cliente;
import com.company.campanha.models.ClienteCampanha;
import com.company.campanha.repositories.CampanhaRepository;
import com.company.campanha.repositories.ClienteCampanhaRepository;
import com.company.campanha.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  ClienteRepository clienteRepository;

  @Autowired
  CampanhaRepository campanhaRepository;

  @Autowired
  ClienteCampanhaRepository clienteCampanhaRepository;


  private void cadastrarCampanhas(Cliente cliente) {
    //Caso tenha time do coração, busca campanhas ativas e faz associação
    if (!cliente.getTimeCoracao().equals("")) {
      List<Campanha> listaCampanhasAtivas =
          campanhaRepository.buscaPorTimeCoracao(cliente.getTimeCoracao(), LocalDate.now());
      for (Campanha campanha : listaCampanhasAtivas) {
        ClienteCampanha clienteCampanha = new ClienteCampanha(cliente.getId(), campanha.getId());
        clienteCampanhaRepository.save(clienteCampanha);
      }
    }
  }

  /**
   * GET /create --> Cria uma nova cliente e salva no banco
   */
  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public Map<String, Object> create(@RequestBody Cliente cliente) {
    // Busca cliente existente pelo Email
    Cliente clienteEncontrado = clienteRepository.findByEmail(cliente.getEmail());
    if (clienteEncontrado != null) {
      // Se encontrado, associa novas campanhas
      cadastrarCampanhas(clienteEncontrado);
      Map<String, Object> dataMap = new HashMap<String, Object>();
      dataMap.put("message", "Cliente já cadastrado!");
      dataMap.put("status", "2");
      dataMap.put("cliente", cliente);
      return dataMap;
    } else {
      // Cadastra novo cliente e associa campanhas
      cliente = clienteRepository.save(cliente);
      cadastrarCampanhas(cliente);
      Map<String, Object> dataMap = new HashMap<String, Object>();
      dataMap.put("message", "Cliente cadastrado com sucesso");
      dataMap.put("status", "1");
      dataMap.put("cliente", cliente);
      return dataMap;
    }
  }

  /**
   * GET /read --> Busca cliente por id do banco
   */
  @RequestMapping("/read")
  public Map<String, Object> read(@RequestParam String clienteId) {
    Cliente cliente = clienteRepository.findOne(clienteId);
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Cliente encontrado com sucesso");
    dataMap.put("status", "1");
    dataMap.put("cliente", cliente);
    return dataMap;
  }

  /**
   * GET /update --> Atualiza o cliente e salva no banco
   */
  @RequestMapping("/update")
  public Map<String, Object> update(Cliente cliente) {
    cliente = clienteRepository.save(cliente);
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Cliente atualizado com sucesso");
    dataMap.put("status", "1");
    dataMap.put("cliente", cliente);
    return dataMap;
  }

  /**
   * GET /delete --> Apaga o cliente do banco
   */
  @RequestMapping("/delete")
  public Map<String, Object> delete(@RequestParam String clienteId) {
    clienteRepository.delete(clienteId);
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Cliente apagado com sucesso");
    dataMap.put("status", "1");
    return dataMap;
  }

  /**
   * GET /read --> Retorna todos clientes do banco
   */
  @RequestMapping("/read-all")
  public Map<String, Object> readAll() {
    List<Cliente> clientes = clienteRepository.findAll();
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Clientes encontrados com sucesso");
    dataMap.put("totalCliente", clientes.size());
    dataMap.put("status", "1");
    dataMap.put("clientes", clientes);
    return dataMap;
  }

}
