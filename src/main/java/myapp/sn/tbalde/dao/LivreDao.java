package sn.tbalde.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sn.tbalde.interfaces.Crud;
import sn.tbalde.model.Livre;

public class LivreDao implements Crud<Livre> {
    Connection connection;

    public LivreDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Livre element) {
        String sql = """
                INSERT INTO livre(titre,auteur,categorie,quantite_disponible) VALUES(?,?,?,?)
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, element.getTitre());
            ps.setString(2, element.getAuteur());
            ps.setString(3, element.getCategorie());
            ps.setInt(4, element.getQuantiteDisponible());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Livre findById(int id) {
        String sql = """
                SELECT * FROM livre
                WHERE id_livre=?
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
    public List<Livre> findAll() {
        List<Livre> listeLivres = new ArrayList<>();
        String sql = """
                SELECT * FROM livre
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listeLivres.add(mapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeLivres;
    }

    @Override
    public void update(Livre element) {
        String sql = """
                UPDATE livre
                SET titre=?, auteur=?, categorie=?, quantite_disponible=?
                WHERE id_livre=?
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, element.getTitre());
            ps.setString(2, element.getAuteur());
            ps.setString(3, element.getCategorie());
            ps.setInt(4, element.getQuantiteDisponible());
            ps.setInt(5, element.getIdLivre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = """
                DELETE FROM livre
                WHERE id_livre=?
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Livre mapper(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_livre");
        String titre = rs.getString("titre");
        String auteur = rs.getString("auteur");
        String categorie = rs.getString("categorie");
        int qte = rs.getInt("quantite_disponible");
        return new Livre(id, titre, auteur, categorie, qte);
    }

}
