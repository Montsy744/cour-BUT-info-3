package fr.but3.server.entities;

import fr.but3.server.Constants;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(Constants.menage)
public class Menage extends Offre {

  @NotNull(message = "La surface ne peut pas être nulle")
  @Positive(message = "La surface doit être strictement positive")
  private Integer surface;

  @NotNull(message = "La fourniture des produits ménagers ne peut pas être nulle")
  private Boolean produits_menages_fournis;

  @NotNull(message = "La prise en charge du ramassage ne peut pas être nulle")
  private Boolean ramassage;

  @NotNull(message = "La présence d'animaux ne peut pas être nulle")
  private Boolean animaux;

  @NotNull(message = "La valeur de linge ne peut pas être nulle")
  private Boolean linge;
}
