package fr.but3.server.controller;

import fr.but3.server.configuration.UserService;
import fr.but3.server.entities.Offre;
import fr.but3.server.entities.Utilisateur;
import fr.but3.server.repositories.OffreRepository;
import fr.but3.server.repositories.UtilisateurRepository;
import fr.but3.server.services.EmailService;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UtilisateurController {
  private final UtilisateurRepository utilisateurRepository;
  private final OffreRepository offreRepository;
  private final UserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private static final SecureRandom secureRandom = new SecureRandom();
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

  @Autowired private EmailService emailService;

  @GetMapping("/users")
  public Iterable<Utilisateur> getUsers() {
    var utilisateurs = utilisateurRepository.findAll();
    return utilisateurs;
  }

  @GetMapping("/users/{id}/offers")
  public Iterable<Offre> getUserOffers(@PathVariable Integer id) {
    return utilisateurRepository
        .findById(id)
        .orElseThrow(() -> new NoSuchElementException("User with id " + id + " was not found"))
        .getOffre();
  }

  @GetMapping("/users/{id}/applications")
  public Iterable<Offre> getUserApplications(@PathVariable Integer id) {
    Utilisateur utilisateur =
        utilisateurRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("User with id " + id + " was not found"));

    List<Offre> offres = offreRepository.findAll();
    return offres.stream()
        .filter(
            (offre) -> {
              return offre.getCandidats().contains(utilisateur);
            })
        .toList();
  }

  @GetMapping("/users/{id}")
  public Utilisateur getUserById(@PathVariable Integer id) {
    return utilisateurRepository
        .findById(id)
        .orElseThrow(() -> new NoSuchElementException("User with id " + id + " was not found"));
  }

  @PostMapping("/users")
  public Utilisateur postUsers(@RequestBody Utilisateur newUtilisateur) {
    String encodedPassword = bCryptPasswordEncoder.encode(newUtilisateur.getMdp());
    newUtilisateur.setMdp(encodedPassword);
    newUtilisateur.setToken(generateNewToken());
    return utilisateurRepository.save(newUtilisateur);
  }

  // @PostMapping("/users")
  // public Utilisateur postUsers(@RequestBody Utilisateur newUtilisateur) {
  // String encodedPassword = bCryptPasswordEncoder.encode(newUtilisateur.getMdp());
  // newUtilisateur.setMdp(encodedPassword);
  // newUtilisateur.setToken(generateNewToken());

  // Utilisateur savedUtilisateur = utilisateurRepository.save(newUtilisateur);

  // String to = newUtilisateur.getEmail();
  // String subject = "Bienvenue sur Dolce Vita";
  // String text = "Cher " + newUtilisateur.getNom() + ",\n\n"
  // + "Bienvenue à Dolce Vita ! Votre quotidien s'apprête à devenir plus serein.\n" + //
  // "\n" + //
  // "À très bientôt,\n" + //
  // "\n" + //
  // "L'équipe Dolce Vita";

  // try {
  // emailService.sendEmail(to, subject, text);
  // } catch (MessagingException e) {
  // e.printStackTrace();
  // }

  // return savedUtilisateur;
  // }

  @PostMapping("/users/{otherId}/sponsorship")
  public Utilisateur postUsersSponsorship(
      @RequestParam Integer myId, @PathVariable Integer otherId, @RequestBody String myToken) {
    // IMPORTANT: myId parraine otherId
    // donc myId ajoute a sa liste otherId
    // TODO et otherId ajoute myId comme parrain

    Utilisateur me =
        utilisateurRepository
            .findByToken(myToken)
            .orElseThrow(
                () -> new NoSuchElementException("User with myId " + myId + " was not found"));
    Utilisateur other =
        utilisateurRepository
            .findById(otherId)
            .orElseThrow(
                () ->
                    new NoSuchElementException("User with otherId " + otherId + " was not found"));

    me.getParrains().add(other);

    utilisateurRepository.save(me);

    return me;
  }

  @PostMapping("/users/authenticate")
  public ResponseEntity<Map<String, String>> createAuthenticationToken(
      @RequestParam String username, @RequestParam String password) throws Exception {
    // Use your own UserService to authenticate the user
    if (!userService.authenticate(username, password)) {
      throw new Exception("Authentication failed");
    }

    Utilisateur user = userService.findByEmail(username);
    String token = generateNewToken();
    user.setToken(token);
    userService.save(user);

    Map<String, String> response = new HashMap<>();
    response.put("token", token);
    response.put("id", user.getId().toString());

    return ResponseEntity.ok(response);
  }

  public static String generateNewToken() {
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
  }
}
