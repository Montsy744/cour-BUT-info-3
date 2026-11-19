package fr.but3.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import fr.but3.server.Constants;
import fr.but3.server.entities.Menage;
import fr.but3.server.exceptions.NotEnoughParrainsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OffreControllerTest {
  @Autowired OffreController offreController;

  @Test
  void getOffresTest() {
    assertThat(offreController.getOffres(null, null).size()).isGreaterThan(0);

    assertThat(offreController.getOffres(Constants.menage, null))
        .hasOnlyElementsOfType(Menage.class);

    assertThat(offreController.getOffres(null, "73000"))
        .allMatch(offre -> "73000".equals(offre.getCodePostal()));

    assertThat(offreController.getOffres(Constants.menage, "73000"))
        .allMatch(offre -> "73000".equals(offre.getCodePostal()) && offre instanceof Menage);
  }

  @Test
  void postOffresApplyTest() throws NotEnoughParrainsException {
    var offre = offreController.getOffre(6);
    assertThat(offre.getCandidats().isEmpty()).isTrue();
    offreController.postOffresApply(6, 2);
    assertThat(offreController.getOffre(6).getCandidats().size()).isEqualTo(1);
  }

  /*
   * @Test
   * public void test_that_wont_work() {
   * assertTrue(false);
   * }
   */

  /*
   * @Test
   * void postOffresApplyTest_crash_if_not_enough_parrains() throws
   * NotEnoughParrainsException {
   * var offre = offreController.getOffre(5);
   * assertThat(offre.getCandidats().isEmpty()).isTrue();
   * assertThatThrownBy(() -> {
   * offreController.postOffresApply(5, 3);
   * }).isInstanceOf(NotEnoughParrainsException.class);
   *
   * }
   */

}
