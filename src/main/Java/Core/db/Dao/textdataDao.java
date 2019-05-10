package Core.db.Dao;

import Core.data.textdata;

import java.util.List;

public interface textdataDao {
    Object save(textdata textdata);

    Object update(textdata textdata);

    void deleteByID(int id);

    List<textdata> findNoPage();

    Object findByID(int id);
}
