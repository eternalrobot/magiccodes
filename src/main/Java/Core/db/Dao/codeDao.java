package Core.db.Dao;

import Core.data.code;

import java.util.List;

public interface codeDao {
    Object save(code code);

    Object update(code code);

    void deleteByID(int id);

    List<code> findNoPage();

    Object findByID(int id);
}
