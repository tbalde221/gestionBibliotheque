package sn.tbalde.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sn.tbalde.interfaces.Crud;
import sn.tbalde.model.Emprunt;

public class EmpruntDao implements Crud<Emprunt> {
    private Connection connection;

    public EmpruntDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Emprunt element) {
        String sql = """
                INSERT INTO emprunt
                (date_emprunt, date_retour_prevue, date_retour_effective, statut, id_livre, id_etudiant)
                VALUES
                (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, element.getDateEmprunt());
            ps.setDate(2, element.getDateRetourPrevu());
            ps.setDate(3, element.getDateRetourEffective());
            ps.setString(4, element.getStatut());
            ps.setInt(5, element.getIdLivre());
            ps.setInt(6, element.getIdEtudiant());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Emprunt findById(int id) {
        String sql = """
                SELECT * FROM emprunt
                WHERE id_emprunt=?
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapper(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emprunt> findAll() {
        List<Emprunt> listeEmprunts = new ArrayList<>();
        String sql = """
                SELECT * FROM emprunt
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listeEmprunts.add(mapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEmprunts;
    }

    @Override
    public void update(Emprunt element) {
        String sql = """
                UPDATE
                    emprunt
                SET
                    date_emprunt=?,
                    date_retour_prevue=?,
                    date_retour_effective=?
                    statut=?,
                    id_livre=?,
                    id_etudiant=?
                WHERE
                    id_emprunt=?
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, element.getDateEmprunt());
            ps.setDate(2, element.getDateRetourPrevu());
            ps.setDate(3, element.getDateRetourEffective());
            ps.setString(4, element.getStatut());
            ps.setInt(5, element.getIdLivre());
            ps.setInt(6, element.getIdEtudiant());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = """
                DELETE FROM
                    emprunt
                WHERE
                    id_emprunt=?
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Emprunt mapper(ResultSet rs) throws SQLException {
        int idEmprunt = rs.getInt("id_emprunt");
        Date dateEmprunt = rs.getDate("date_emprunt");
        Date dateRetourPrevue = rs.getDate("date_retour_prevue");
        Date dateRetourEffective = rs.getDate("date_retour_effective");
        String statut = rs.getString("statut");
        int idLivre = rs.getInt("id_livre");
        int idEtudiant = rs.getInt("id_etudiant");
        return new Emprunt(idEmprunt, dateEmprunt, dateRetourPrevue, dateRetourEffective, statut, idLivre, idEtudiant);
    }

}
