package com.company.campanha.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.campanha.models.Campanha;
import com.company.campanha.models.ClienteCampanha;
import com.company.campanha.repositories.CampanhaRepository;
import com.company.campanha.repositories.ClienteCampanhaRepository;

@RestController
@RequestMapping("/campanha")
public class CampanhaController {

  @Autowired
  CampanhaRepository campanhaRepository;

  @Autowired
  ClienteCampanhaRepository clienteCampanhaRepository;

  /**
   * GET /create --> Cria uma nova campanha e salva no banco
   */
  @RequestMapping("/create")
  public Map<String, Object> create(Campanha novaCampanha) {
    // Pega lista de campanhas vigentes no período da nova campanha
    List<Campanha> lista = campanhaRepository.findByVigencia(novaCampanha.getVigenciaInicio(),
        novaCampanha.getVigenciaFim());
    // Adiciona nova campanha
    novaCampanha = campanhaRepository.save(novaCampanha);
    // Percorre lista para adicionar novo dia no final
    for (Campanha campanha : lista) {
      // Acrescenta um dia
      campanha.setVigenciaFim(campanha.getVigenciaFim().plusDays(1));
      // Enquanto houver campanha com o mesmo dia final, acresecenta mais um dia
      while (!campanhaRepository.findByVigenciaFim(campanha.getVigenciaFim(), LocalDate.now())
          .isEmpty())
        campanha.setVigenciaFim(campanha.getVigenciaFim().plusDays(1));
      campanhaRepository.save(campanha);
      // Pega lista de clientes que enquadram na campanha
      List<ClienteCampanha> listaClienteCampanha =
          clienteCampanhaRepository.findByCampanhaId(campanha.getId());
      for (ClienteCampanha clienteCampanha : listaClienteCampanha) {
        // Seta que alterou para modificar cliente
        clienteCampanha.setAlterou(true);
        clienteCampanhaRepository.save(clienteCampanha);
      }
    }
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Campanha criada com sucesso");
    dataMap.put("status", "1");
    dataMap.put("campanha", novaCampanha);
    return dataMap;
  }

  /**
   * GET /read --> Busca campanha por id do banco
   */
  @RequestMapping("/read")
  public Map<String, Object> read(@RequestParam String campanhaId) {
    Campanha campanha = campanhaRepository.findOne(campanhaId);
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Campanha encontrada com sucesso");
    dataMap.put("status", "1");
    dataMap.put("campanha", campanha);
    return dataMap;
  }

  /**
   * GET /update --> Atualiza a campanha e salva no banco
   */
  @RequestMapping("/update")
  public Map<String, Object> update(Campanha campanha) {
    campanha = campanhaRepository.save(campanha);
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Campanha atualizada com sucesso");
    dataMap.put("status", "1");
    dataMap.put("campanha", campanha);
    return dataMap;
  }

  /**
   * GET /delete --> Apaga a campanha do banco
   */
  @RequestMapping("/delete")
  public Map<String, Object> delete(@RequestParam String campanhaId) {
    campanhaRepository.delete(campanhaId);
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Campanha apagada com sucesso");
    dataMap.put("status", "1");
    return dataMap;
  }

  /**
   * GET /read --> Retorna todas campanhas do banco
   */
  @RequestMapping("/read-all")
  public Map<String, Object> readAll() {
    List<Campanha> campanhas = campanhaRepository.findAll();
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("message", "Campanhas encontradas com sucesso");
    dataMap.put("totalCampanha", campanhas.size());
    dataMap.put("status", "1");
    dataMap.put("campanhas", campanhas);
    return dataMap;
  }

}
