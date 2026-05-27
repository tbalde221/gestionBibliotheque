package sn.tbalde.service;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sn.tbalde.dao.EmpruntDao;
import sn.tbalde.dao.EtudiantDao;
import sn.tbalde.dao.LivreDao;
import sn.tbalde.model.Emprunt;
import sn.tbalde.model.Etudiant;
import sn.tbalde.model.Livre;

public class ServiceEmprunt {
    private EmpruntDao empruntDao;
    private LivreDao livreDao;
    private EtudiantDao etudiantDao;

    public ServiceEmprunt(Connection connection) {
        this.empruntDao = new EmpruntDao(connection);
        this.livreDao = new LivreDao(connection);
        this.etudiantDao = new EtudiantDao(connection);
    }

    public void emprunterLivre(Date dateEmprunt, Date dateRetourPrevu, String statut,
            int idLivre,
            int idEtudiant) {

        if (!livreExists(idLivre) || !livreDisponible(idLivre)) {
            System.out.println("Livre indisponible");
            return;
        }
        if (!etudiantExists(idEtudiant)) {
            System.out.println("Etudiant indisponible");
            return;
        }

        Emprunt emprunt = new Emprunt(dateEmprunt, dateRetourPrevu, statut, idLivre,
                idEtudiant);
        empruntDao.create(emprunt);
        Livre livre = livreDao.findById(idLivre);
        decrement(livre);

    }

    public void rendreLivre(int idLivre, int idEtudiant) {
        if (!empruntExists(idLivre, idEtudiant)) {
            System.out.println("Emprunt non enregistre");
            return;
        }
        Livre livre = livreDao.findById(idLivre);
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        Emprunt emprunt = empruntDao.findByIdLivreIdEtudiant(idLivre, idEtudiant);
        emprunt.setDateRetourEffective(date);
        emprunt.setStatut("Rendu");
        empruntDao.update(emprunt);
        increment(livre);
    }

    public void listerLivres() {
        List<Livre> livres = new ArrayList<>();
        livres = livreDao.findAll();
        for (Livre livre : livres) {
            System.out.println(
                    "Auteur: " + livre.getAuteur() + "\tTitre: " + livre.getTitre() + "\tID: " + livre.getIdLivre());
        }
    }

    private boolean livreExists(int idLivre) {
        Livre livre = livreDao.findById(idLivre);
        if (livre != null) {
            return true;
        }
        return false;
    }

    private boolean livreDisponible(int idLivre) {

        Livre livre = livreDao.findById(idLivre);
        if (livre == null) {
            return false;
        }
        if (livre.getQuantiteDisponible() > 0) {
            return true;
        }
        return false;
    }

    private boolean etudiantExists(int idEtudiant) {
        Etudiant etudiant = etudiantDao.findById(idEtudiant);
        if (etudiant != null) {
            return true;
        }
        return false;
    }

    private boolean empruntExists(int idLivre, int idEtudiant) {
        Emprunt emprunt = empruntDao.findByIdLivreIdEtudiant(idLivre, idEtudiant);
        if (emprunt != null) {
            return true;
        }
        return false;
    }

    private void decrement(Livre livre) {
        int qte = livre.getQuantiteDisponible() - 1;
        livre.setQuantiteDisponible(qte);
        livreDao.update(livre);
    }

    private void increment(Livre livre) {
        int qte = livre.getQuantiteDisponible() + 1;
        livre.setQuantiteDisponible(qte);
        livreDao.update(livre);
    }

    public void supprimerLivre(int idLivre) {
        Livre livre = livreDao.findById(idLivre);
        if (livre == null) {
            System.out.println("Livre non disponible.");
            return;
        }
        livreDao.delete(livre.getIdLivre());
    }

    public void supprimerEtudiant(int idEtudiant) {
        Etudiant etudiant = etudiantDao.findById(idEtudiant);
        if (etudiant == null) {
            System.out.println("Etudiant inexistant.");
            return;
        }
        etudiantDao.delete(etudiant.getIdEtudiant());
    }

    public void ajouterLivre(String titre, String auteur, String categorie, int quantiteDisponible) {
        Livre livre = new Livre(titre, auteur, categorie, quantiteDisponible);
        livreDao.create(livre);
    }

    public void ajouterEtudiant(String nom, String prenom, String email, String niveau) {
        Etudiant etudiant = new Etudiant(nom, prenom, email, niveau);
        etudiantDao.create(etudiant);
    }

}
