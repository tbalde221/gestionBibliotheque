package sn.tbalde.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Crud<E> {

    void create(E element);

    E findById(int id);

    List<E> findAll();

    void update(E element);

    void delete(int id);

    E mapper(ResultSet rs) throws SQLException;

}
