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
@DiscriminatorValue(Constants.jardinage)
public class Jardinage extends Offre {

  @NotNull(message = "La surface ne peut pas être nulle")
  @Positive(message = "La surface doit être strictement positive")
  private Integer surface;

  @NotNull(message = "Le champ tailleHaie ne peut pas être nul")
  private Boolean tailleHaie;

  @NotNull(message = "Le champ materielJardinageFourni ne peut pas être nul")
  private Boolean materielJardinageFourni;

  @NotNull(message = "Le champ tonte ne peut pas être nul")
  private Boolean tonte;
}
