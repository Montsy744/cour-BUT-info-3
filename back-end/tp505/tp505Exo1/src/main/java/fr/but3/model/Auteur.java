package fr.but3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Auteur {

    @Id 
    private int ano;
    private String nom;
    private String prenom;
    private String email;

    public int getAno() {
        return ano;
    }
    public String getEmail() {
        return email;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Auteur [ano=" + ano + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
    }
}
