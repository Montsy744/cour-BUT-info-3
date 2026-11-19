package fr.but3.server.specifications;

import fr.but3.server.entities.Offre;
import org.springframework.data.jpa.domain.Specification;

public class OffreSpecifications {
  public static Specification<Offre> withType(String type) {
    return (root, query, builder) ->
        type == null ? null : builder.like(root.get("type"), type + "%");
  }

  public static Specification<Offre> withCodePostal(String codePostal) {
    return (root, query, builder) ->
        codePostal == null ? null : builder.like(root.get("codePostal"), codePostal + "%");
  }
}
