package sn.tbalde.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sn.tbalde.interfaces.Crud;
import sn.tbalde.model.Etudiant;

public class EtudiantDao implements Crud<Etudiant> {
    Connection connection;

    public EtudiantDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Etudiant etu) {
        String sql = "INSERT INTO etudiant( nom, prenom, email, niveau) VALUES( ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, etu.getNom());
            ps.setString(2, etu.getPrenom());
            ps.setString(3, etu.getEmail());
            ps.setString(4, etu.getNiveau());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Etudiant findById(int id) {
        String sql = "SELECT * FROM etudiant WHERE id_etudiant=?";
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
    public List<Etudiant> findAll() {
        List<Etudiant> listeEtudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                listeEtudiants.add(mapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEtudiants;
    }

    @Override
    public void update(Etudiant element) {
        String sql = "UPDATE etudiant SET nom=? ,prenom=? , email=? ,niveau=? WHERE id_etudiant=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, element.getNom());
            ps.setString(2, element.getPrenom());
            ps.setString(3, element.getEmail());
            ps.setString(4, element.getNiveau());
            ps.setInt(5, element.getIdEtudiant());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id_etudiant=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Etudiant mapper(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_etudiant");
        String nom = rs.getString("nom");
        String prenom = rs.getString("prenom");
        String email = rs.getString("email");
        String niveau = rs.getString("niveau");
        return new Etudiant(id, nom, prenom, email, niveau);
    }

}
