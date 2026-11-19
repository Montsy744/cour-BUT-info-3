package fr.but3.server.configuration;

import fr.but3.server.entities.Utilisateur;
import fr.but3.server.repositories.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UtilisateurRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserService(
      UtilisateurRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public boolean authenticate(String username, String password) {
    Utilisateur user = userRepository.findByEmail(username);
    if (user == null) {
      System.out.println("User not found");
      return false;
    }

    return bCryptPasswordEncoder.matches(password, user.getMdp());
  }

  public Utilisateur findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Utilisateur save(Utilisateur user) {
    return userRepository.save(user);
  }
}
