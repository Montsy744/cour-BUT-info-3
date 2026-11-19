package fr.but3.server.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import fr.but3.server.Constants;
import fr.but3.server.entities.GardeEnfant;
import fr.but3.server.entities.GardePersonneAgee;
import fr.but3.server.entities.Menage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OffreRepositoryTest {
  @Autowired OffreRepository offreRepository;

  @Test
  void repositoryLoads() {
    assertThat(offreRepository.findAll().size()).isGreaterThan(0);
  }

  @Test
  void atLeastOneMenage() {
    assertThat(offreRepository.findByType(Constants.menage).get(0)).isInstanceOf(Menage.class);
  }

  @Test
  void atLeastOneGardeEnfant() {
    assertThat(offreRepository.findByType(Constants.gardeEnfant).get(0))
        .isInstanceOf(GardeEnfant.class);
  }

  @Test
  void atLeastOneGardePersonneAgee() {
    assertThat(offreRepository.findByType(Constants.gardePersonneAgee).get(0))
        .isInstanceOf(GardePersonneAgee.class);
  }
}
