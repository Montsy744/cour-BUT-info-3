package fr.but3.server.repositories;

import fr.but3.server.entities.Utilisateur;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {

  Utilisateur findByEmail(String email);

  Optional<Utilisateur> findByToken(String token);

  List<Utilisateur> findAll();
}
