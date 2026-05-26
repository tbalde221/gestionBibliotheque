package sn.tbalde.service;

import java.sql.Connection;
import java.sql.Date;

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

    public void emprunterLivre(Date dateEmprunt, Date dateRetourPrevu, Date dateRetourEffective, String statut,
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

        Emprunt emprunt = new Emprunt(dateEmprunt, dateRetourPrevu, dateRetourEffective, statut, idLivre,
                idEtudiant);
        empruntDao.create(emprunt);

        Livre livre = livreDao.findById(idLivre);
        decrement(livre);

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

    private void decrement(Livre livre) {
        int qte = livre.getQuantiteDisponible() - 1;
        livre.setQuantiteDisponible(qte);
        livreDao.update(livre);
    }

}
