package sn.tbalde.model;

import java.sql.Date;

public class Emprunt {
    private int idEmprunt;
    private Date dateEmprunt;
    private Date dateRetourPrevu;
    private Date dateRetourEffective;
    private String statut;
    private int idLivre;
    private int idEtudiant;

    public Emprunt(Date dateEmprunt, Date dateRetourPrevu, Date dateRetourEffective, String statut, int idLivre,
            int idEtudiant) {
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevu = dateRetourPrevu;
        this.dateRetourEffective = dateRetourEffective;
        this.statut = statut;
        this.idLivre = idLivre;
        this.idEtudiant = idEtudiant;
    }

    public Emprunt(int idEmprunt, Date dateEmprunt, Date dateRetourPrevu, Date dateRetourEffective, String statut,
            int idLivre, int idEtudiant) {
        this(dateEmprunt, dateRetourPrevu, dateRetourEffective, statut, idLivre, idEtudiant);
        this.idEmprunt = idEmprunt;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public void setDateRetourPrevu(Date dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public Date getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(Date dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idEmprunt;
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
        Emprunt other = (Emprunt) obj;
        if (idEmprunt != other.idEmprunt)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Emprunt [idEmprunt=" + idEmprunt + ", dateEmprunt=" + dateEmprunt + ", dateRetourPrevu="
                + dateRetourPrevu + ", dateRetourEffective=" + dateRetourEffective + ", statut=" + statut + ", idLivre="
                + idLivre + ", idEtudiant=" + idEtudiant + "]";
    }

}
