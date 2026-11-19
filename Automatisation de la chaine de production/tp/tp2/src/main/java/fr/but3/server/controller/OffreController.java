package fr.but3.server.controller;

import fr.but3.server.entities.GardeEnfant;
import fr.but3.server.entities.GardePersonneAgee;
import fr.but3.server.entities.Jardinage;
import fr.but3.server.entities.Menage;
import fr.but3.server.entities.Offre;
import fr.but3.server.exceptions.NotEnoughParrainsException;
import fr.but3.server.repositories.OffreRepository;
import fr.but3.server.services.OffresServices;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OffreController {
  private final OffresServices offresServices;
  private final OffreRepository offreRepository;

  @GetMapping("/offers")
  public List<Offre> getOffres(
      @RequestParam(required = false) String type,
      @RequestParam(required = false) String codePostal) {
    return this.offresServices.getOffres(type, codePostal);
  }

  @GetMapping("/offers/{id}")
  public Offre getOffre(@PathVariable Integer id) {
    return offreRepository
        .findById(id)
        .orElseThrow(() -> new NoSuchElementException("User with id " + id + " was not found"));
  }

  @PostMapping("/offers/{id}/apply")
  public Offre postOffresApply(@PathVariable Integer id, @RequestParam Integer userId)
      throws NotEnoughParrainsException {
    return offresServices.applyToOffer(id, userId);
  }

  @PostMapping("/offers/menage")
  public Offre postOffresMenage(@RequestParam Integer userId, @RequestBody Menage menage) {
    return offresServices.setOffreMenage(userId, menage);
  }

  @PostMapping("/offers/gardePersonneAgee")
  public Offre postOffresMenage(
      @RequestParam Integer userId, @RequestBody GardePersonneAgee gardePersonneAgee) {
    return offresServices.setOffreMenage(userId, gardePersonneAgee);
  }

  @PostMapping("/offers/gardeEnfant")
  public Offre postOffresMenage(
      @RequestParam Integer userId, @RequestBody GardeEnfant gardeEnfant) {
    return offresServices.setOffreMenage(userId, gardeEnfant);
  }

  @PostMapping("/offers/jardinage")
  public Offre postOffresMenage(@RequestParam Integer userId, @RequestBody Jardinage jardinage) {
    return offresServices.setOffreMenage(userId, jardinage);
  }
}
