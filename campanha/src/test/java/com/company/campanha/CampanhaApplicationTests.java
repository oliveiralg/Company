package com.company.campanha;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.company.campanha.models.Campanha;

@SpringBootApplication
public class CampanhaApplicationTests {

  @Test
  public static void criarCampanhas() {

    Campanha campanha = new Campanha();
    campanha.setNome("Campanha 1");
    campanha.setTimeCoracao("1");
    campanha.setVigenciaInicio(LocalDate.of(2017, 10, 1));
    campanha.setVigenciaFim(LocalDate.of(2017, 10, 2));

    RestTemplate rest = new RestTemplate();
    String url = "http://localhost:8080/campanha/create";
    ResponseEntity<Campanha> response = rest.postForEntity(url, campanha, Campanha.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    campanha = new Campanha();
    campanha.setNome("Campanha 2");
    campanha.setTimeCoracao("2");
    campanha.setVigenciaInicio(LocalDate.of(2017, 10, 1));
    campanha.setVigenciaFim(LocalDate.of(2017, 10, 2));

    response = rest.postForEntity(url, campanha, Campanha.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    campanha = new Campanha();
    campanha.setNome("Campanha 3");
    campanha.setTimeCoracao("1");
    campanha.setVigenciaInicio(LocalDate.of(2017, 10, 1));
    campanha.setVigenciaFim(LocalDate.of(2017, 10, 3));

    response = rest.postForEntity(url, campanha, Campanha.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());

  }

  public static void main(String args[]) {
    criarCampanhas();
  }

}
