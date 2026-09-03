package model.dto;

public class StudentDTO {
    private int id;
    private String nom;
    private String prenom;
    private String groupe;

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

    public void setGroupe(final String groupe) {
        this.groupe = groupe;
    }
    public void setId(final int id) {
        this.id = id;
    }
    public void setNom(final String nom) {
        this.nom = nom;
    }
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

}
