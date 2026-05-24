package sn.tbalde.model;

import sn.tbalde.util.StringUtils;

public class Livre {
    private int idLivre;
    private String titre;
    private String auteur;
    private String categorie;
    private int quantiteDisponible;

    public Livre(int idLivre, String titre, String auteur, String categorie, int quantiteDisponible) {
        this(titre, auteur, categorie, quantiteDisponible);
        this.idLivre = idLivre;

    }

    public Livre(String titre, String auteur, String categorie, int quantiteDisponible) {
        StringUtils.validateString(titre);
        StringUtils.validateString(auteur);
        StringUtils.validateString(categorie);
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.quantiteDisponible = quantiteDisponible;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        StringUtils.validateString(titre);
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        StringUtils.validateString(auteur);
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        StringUtils.validateString(categorie);
        this.categorie = categorie;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idLivre;
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
        Livre other = (Livre) obj;
        if (idLivre != other.idLivre)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Livre [idLivre=" + idLivre + ", titre=" + titre + ", auteur=" + auteur + ", categorie=" + categorie
                + ", quantiteDisponible=" + quantiteDisponible + "]";
    }

}
