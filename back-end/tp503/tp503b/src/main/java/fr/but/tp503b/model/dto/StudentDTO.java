package model.dto;

public class StudentDTO {
    private final int id;
    private final String nom;
    private final String prenom;
    private final String groupe;

    public StudentDTO(final int id, final String nom, final String prenom, final String groupe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.groupe = groupe;
    }

    public final int getId() {
        return id;
    }
    public final String getGroupe() {
        return groupe;
    }
    public final String getNom() {
        return nom;
    }
    public final String getPrenom() {
        return prenom;
    }
}
