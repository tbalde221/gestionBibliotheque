package sn.tbalde.model;

import sn.tbalde.util.StringUtils;

public class Etudiant {
    private int idEtudiant;
    private String nom;
    private String prenom;
    private String email;
    private String niveau;

    public Etudiant(String nom, String prenom, String email, String niveau) {
        StringUtils.validateNom(nom);
        StringUtils.validatePrenom(prenom);
        StringUtils.validateString(email);
        StringUtils.validateString(niveau);
        this.nom = nom.toUpperCase();
        this.prenom = StringUtils.capitalize(prenom);
        this.email = email;
        this.niveau = niveau;
    }

    public Etudiant(int idEtudiant, String nom, String prenom, String email, String niveau) {
        this(nom, prenom, email, niveau);
        this.idEtudiant = idEtudiant;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        StringUtils.validateNom(nom);
        this.nom = nom.toUpperCase();
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        StringUtils.validatePrenom(prenom);
        this.prenom = StringUtils.capitalize(prenom);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        StringUtils.validateString(email);
        this.email = email;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        StringUtils.validateString(niveau);
        this.niveau = niveau;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idEtudiant;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Etudiant other = (Etudiant) obj;
        if (idEtudiant != other.idEtudiant)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Etudiant [idEtudiant=" + idEtudiant + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
                + ", niveau=" + niveau + "]";
    }

}
