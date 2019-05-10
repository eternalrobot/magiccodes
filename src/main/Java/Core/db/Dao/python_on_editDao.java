package Core.db.Dao;

import Core.data.python_on_edit;

import java.util.List;

public interface python_on_editDao {
    Object save(python_on_edit python_on_edit);

    Object update(python_on_edit python_on_edit);

    void deleteByID(int id);

    List<python_on_edit> findNoPage();

    Object findByID(int id);

    Object findByUserid(int userid);
}
