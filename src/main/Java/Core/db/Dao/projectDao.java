package Core.db.Dao;

import Core.data.project;
import Core.data.textdata;

import java.util.List;

public interface projectDao {
    Object save(project project);

    Object update(project project);

    void deleteByID(int id);

    List<project> findNoPage();

    Object findByID(int id);

    Object findByName(String name);
}
