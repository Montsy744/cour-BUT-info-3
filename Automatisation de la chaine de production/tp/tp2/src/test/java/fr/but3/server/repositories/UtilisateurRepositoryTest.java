package fr.but3.server.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fr.but3.server.entities.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class UtilisateurRepositoryTest {
  @Autowired UtilisateurRepository utilisateurRepository;

  @Test
  void repositoryLoads() {
    assertThat(utilisateurRepository.findAll().size()).isGreaterThan(0);
  }

  @Test
  void canOnlyRegisterOneEmail() {
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setEmail("test-email@test-email.com");
    utilisateur.setMdp("test-password");
    utilisateur.setNom("HAZEBROUCK");
    utilisateur.setPrenom("Antoine");

    utilisateurRepository.save(utilisateur);

    Utilisateur utilisateur2 = new Utilisateur();
    utilisateur2.setEmail("test-email@test-email.com");
    utilisateur2.setMdp("test-password2");
    utilisateur2.setNom("HAZEBROUCK2");
    utilisateur2.setPrenom("Antoine2");
    assertThatThrownBy(
            () -> {
              utilisateurRepository.save(utilisateur2);
            })
        .isInstanceOf(DataIntegrityViolationException.class);
  }
}
