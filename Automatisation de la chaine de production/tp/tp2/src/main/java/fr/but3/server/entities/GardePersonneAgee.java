package fr.but3.server.entities;

import fr.but3.server.Constants;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@DiscriminatorValue(Constants.gardePersonneAgee)
public class GardePersonneAgee extends Offre {

  @NotNull(message = "L'âge ne peut pas être nul")
  @Positive(message = "L'âge doit être strictement supérieur à zéro")
  private Integer age;

  @NotNull(message = "La présence de médicaments ne peut pas être nulle")
  private Boolean medicaments;

  @NotNull(message = "Le champ preparationRepas ne peut pas être nul")
  private Boolean preparationRepas;

  @NotNull(message = "Le champ sortie ne peut pas être nul")
  private Boolean sortie;
}
