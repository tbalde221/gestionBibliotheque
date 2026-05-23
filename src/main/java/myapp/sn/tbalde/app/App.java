package sn.tbalde.app;

import java.sql.Connection;
import java.util.List;

import sn.tbalde.dao.EtudiantDao;
import sn.tbalde.db.ConnexionDb;
import sn.tbalde.model.Etudiant;

public class App {

    public static void main(String[] args) {
        ConnexionDb c = new ConnexionDb();
        try (Connection connection = c.getConnection()) {
            EtudiantDao etudiantDao = new EtudiantDao(connection);
            // Etudiant etu = new Etudiant("BALDE", "Thierno", "maildebalde@gmail.com",
            // "Licence");
            // etudiantDao.create(etu);
            // List<Etudiant> listeEtudiants = etudiantDao.findAll();
            // for (Etudiant etudiant : listeEtudiants) {
            // System.out.println(etudiant.getPrenom() + " " + etudiant.getNom());
            // }
            Etudiant etudiant = etudiantDao.findById(1);
            System.out.println(etudiant.getPrenom() + " " + etudiant.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
