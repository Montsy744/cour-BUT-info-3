package fr.but3.server.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import lombok.Data;

@Data
@Entity(name = "utilisateur")
public class Utilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Le nom ne peut pas être vide")
  @Size(max = 255, message = "Le nom ne peut pas dépasser 255 caractères")
  private String nom;

  @NotBlank(message = "Le prénom ne peut pas être vide")
  @Size(max = 255, message = "Le prénom ne peut pas dépasser 255 caractères")
  private String prenom;

  @Pattern(regexp = "\\d{10}", message = "Le numéro de téléphone doit contenir 10 chiffres")
  private String telephone;

  @NotBlank(message = "L'adresse ne peut pas être vide")
  @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères")
  private String adresse;

  @Pattern(regexp = "\\d{5}", message = "Le code postal doit contenir 5 chiffres")
  private String codePostal;

  @NotBlank(message = "La ville ne peut pas être vide")
  @Size(max = 255, message = "La ville ne peut pas dépasser 255 caractères")
  private String ville;

  @NotBlank(message = "Le mot de passe ne peut pas être vide")
  private String mdp;

  @Column(unique = true)
  @NotBlank(message = "L'email ne peut pas être vide")
  private String email;

  @Column(columnDefinition = "TEXT")
  private String avatarBase64;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String token;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Offre> offre = new ArrayList<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Utilisateur> parrains = new HashSet<Utilisateur>();

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Utilisateur> filleuls = new HashSet<Utilisateur>();

  public Boolean assezDeParrains() {
    return parrains.size() >= 2;
  }

  @JsonProperty(defaultValue = "null")
  public OptionalDouble getNote() {
    return offre.stream().mapToInt(offre -> offre.getNote()).average();
  }
}
