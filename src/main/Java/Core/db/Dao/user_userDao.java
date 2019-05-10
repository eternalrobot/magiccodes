package Core.db.Dao;

import Core.data.user_user;

import java.util.List;

public interface user_userDao {
    Object save(user_user user_user);

    Object update(user_user user_user);

    void deleteByID(int id);

    List<user_user> findNoPage();

    Object findByID(int id);

    Object findUser_UserByTwoUsername(String username1,String username2);
}
