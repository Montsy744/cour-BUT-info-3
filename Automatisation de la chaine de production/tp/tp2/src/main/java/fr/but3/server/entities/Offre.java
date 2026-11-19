package fr.but3.server.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "offre")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Offre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull(message = "La date du créneau ne peut pas être nulle")
  @Size(max = 255, message = "La date du créneau ne peut pas dépasser 255 caractères")
  private String dateCreneau;

  @NotNull(message = "L'heure de début ne peut pas être nulle")
  @Size(max = 255, message = "L'heure de début ne peut pas dépasser 255 caractères")
  private String heureDebut;

  @NotNull(message = "L'heure de fin ne peut pas être nulle")
  @Size(max = 255, message = "L'heure de fin ne peut pas dépasser 255 caractères")
  private String heureFin;

  @NotNull(message = "L'adresse ne peut pas être nulle")
  @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères")
  private String adresse;

  @NotNull(message = "Le code postal ne peut pas être nul")
  @Size(max = 255, message = "Le code postal ne peut pas dépasser 255 caractères")
  private String codePostal;

  @NotNull(message = "La ville ne peut pas être nulle")
  @Size(max = 255, message = "La ville ne peut pas dépasser 255 caractères")
  private String ville;

  @Size(max = 255, message = "La description ne peut pas dépasser 255 caractères")
  private String description;

  @NotNull(message = "Le prix ne peut pas être nul")
  private Integer prix;

  private Integer note = 0;

  @Column(name = "type", insertable = false, updatable = false)
  private String type;

  @ManyToOne private Utilisateur posteur;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Utilisateur> candidats = new ArrayList<>();
}
