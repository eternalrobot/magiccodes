package Core.db.Dao;

import Core.data.movie;

import java.util.List;

public interface movieDao {
    Object save(movie movie);

    Object update(movie movie);

    void deleteByID(int id);

    List<movie> findNoPage();

    Object findByID(int id);

    List<movie> findByNameMOHU(String name);
}
