package sn.tbalde.app;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import sn.tbalde.dao.EmpruntDao;
import sn.tbalde.dao.EtudiantDao;
import sn.tbalde.dao.LivreDao;
import sn.tbalde.db.ConnexionDb;
import sn.tbalde.model.Emprunt;
import sn.tbalde.model.Etudiant;
import sn.tbalde.model.Livre;
import sn.tbalde.service.ServiceEmprunt;

public class App {

    public static void main(String[] args) {
        ConnexionDb c = new ConnexionDb();
        try (Connection connection = c.getConnection()) {
            // EtudiantDao etudiantDao = new EtudiantDao(connection);
            // Etudiant etu = new Etudiant("BALDE", "Thierno", "maildebalde@gmail.com",
            // "Licence");
            // etudiantDao.create(etu);
            // List<Etudiant> listeEtudiants = etudiantDao.findAll();
            // for (Etudiant etudiant : listeEtudiants) {
            // System.out.println(etudiant.getPrenom() + " " + etudiant.getNom());
            // }
            // Etudiant etudiant = etudiantDao.findById(1);
            // System.out.println(etudiant.getPrenom() + " " + etudiant.getNom());
            // LivreDao livreDao = new LivreDao(connection);
            // Livre livre = livreDao.findById(1);
            // livre.setTitre("La plus Secréte Mémoire des Hommes");
            // livreDao.update(livre);
            // LivreDao livreDao = new LivreDao(connection);
            // EtudiantDao etudiantDao = new EtudiantDao(connection);
            // EmpruntDao empruntDao = new EmpruntDao(connection);
            // Livre livre = new Livre("Une si longue lettre", "Mariama BA", "Roman", 10);
            // Etudiant etudiant = new Etudiant("BAH", "Adama", "sadio@gmail.com",
            // "LICENCE");
            // livreDao.create(livre);
            // etudiantDao.create(etudiant);
            // Date dateEmprunt = Date.valueOf("2026-02-27");
            // Date dateRetourPrevu = Date.valueOf("2025-06-30");
            ServiceEmprunt serviceEmprunt = new ServiceEmprunt(connection);
            // serviceEmprunt.emprunterLivre(dateEmprunt, dateRetourPrevu, "En cours", 2,
            // 2);
            serviceEmprunt.rendreLivre(2, 2);
            // Livre livre = livreDao.findById(1);
            // Etudiant etudiant = etudiantDao.findById(1);
            // Date dateEmprunt = Date.valueOf("2025-01-27");
            // Date dateRetourPrevu = Date.valueOf("2025-04-27");
            // Emprunt emprunt = new Emprunt(dateEmprunt, dateRetourPrevu, "Rendu",
            // livre.getIdLivre(), etudiant.getIdEtudiant());
            // empruntDao.create(emprunt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
