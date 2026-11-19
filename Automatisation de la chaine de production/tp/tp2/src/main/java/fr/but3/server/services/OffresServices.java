package fr.but3.server.services;

import fr.but3.server.entities.Offre;
import fr.but3.server.entities.Utilisateur;
import fr.but3.server.exceptions.NotEnoughParrainsException;
import fr.but3.server.repositories.OffreRepository;
import fr.but3.server.repositories.UtilisateurRepository;
import fr.but3.server.specifications.OffreSpecifications;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OffresServices {
  private final OffreRepository offreRepository;
  private final UtilisateurRepository utilisateurRepository;

  public List<Offre> getOffres(String type, String codePostal) {
    Specification<Offre> specType = OffreSpecifications.withType(type);
    Specification<Offre> specCodePostal = OffreSpecifications.withCodePostal(codePostal);
    Specification<Offre> spec = Specification.where(specType).and(specCodePostal);

    return offreRepository.findAll(spec);
  }

  public Offre setOffreMenage(int userId, Offre offre) {
    Utilisateur utilisateur =
        utilisateurRepository
            .findById(userId)
            .orElseThrow(
                () -> new NoSuchElementException("User with id " + userId + " was not found"));

    offre.setPosteur(utilisateur);
    utilisateur.getOffre().add(offre);
    utilisateurRepository.save(utilisateur);

    return offre;
  }

  public Offre applyToOffer(Integer id, Integer userId) throws NotEnoughParrainsException {
    Utilisateur utilisateur =
        utilisateurRepository
            .findById(userId)
            .orElseThrow(
                () -> new NoSuchElementException("User with id " + userId + " was not found"));

    Offre offre =
        offreRepository
            .findById(id)
            .orElseThrow(
                () -> new NoSuchElementException("Offer with id " + id + " was not found"));

    // if
    // (
    // offre.getCandidats()
    // .contains(utilisateur)
    // )
    // {
    // throw new IllegalArgumentException(
    // "User with id " + userId + " already applied to offer id " + id);
    // } else
    // {
    // utilisateur.getOffre().add(offre);
    offre.getCandidats().add(utilisateur);

    return offreRepository.save(offre);
    // }
  }
}
