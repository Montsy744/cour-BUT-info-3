package fr.but3.server.entities;

import fr.but3.server.Constants;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@DiscriminatorValue(Constants.gardeEnfant)
public class GardeEnfant extends Offre {

  @NotNull(message = "Le nombre d'enfants ne peut pas être nul")
  @Min(value = 1, message = "Le nombre d'enfants doit être supérieur à zéro")
  private Integer nbEnfants;

  @NotNull(message = "L'âge du plus jeune enfant ne peut pas être nul")
  @Min(value = 1, message = "L'âge du plus jeune enfant doit être strictement supérieur à zéro")
  private Integer ageDuPlusJeune;

  @NotNull(message = "Le champ aideAuDevoir ne peut pas être nul")
  private Boolean aideAuDevoir;
}
