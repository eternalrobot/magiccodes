package Core.db.Dao;

import Core.data.user;

import java.util.List;

public interface userDao {
    Object save(user user);

    Object update(user user);

    void deleteByID(int id);

    List<user> findNoPage();

    Object findByID(int id);

    user findByString(String s);
}
