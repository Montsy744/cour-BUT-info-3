package fr.but3.server.repositories;

import fr.but3.server.entities.Offre;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

public interface OffreRepository extends CrudRepository<Offre, Integer> {
  List<Offre> findByType(String type);

  List<Offre> findAll(Specification<Offre> spec);

  List<Offre> findAll();
}
