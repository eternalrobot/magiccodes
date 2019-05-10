package Core.db.Dao;

import Core.data.chat;

import java.util.List;

public interface chatDao {
    Object save(chat chat);

    Object update(chat chat);

    void deleteByID(int id);

    List<chat> findNoPage();

    Object findByID(int id);
}
