package Core.db.Service;

import Core.data.*;
import Core.db.Dao.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("utilsService")
public class utilsService {
    @Autowired
    projectDao projectDao;

    @Autowired
    textdataDao textdataDao;

    @Autowired
    python_on_editDao python_on_editDao;

    @Autowired
    Core.db.Dao.userDao userDao;

    @Autowired
    movieDao movieDao;

    public List<movie> findmovieByNameMOHU(String moviename) {
        try {
            return movieDao.findByNameMOHU(moviename);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean addmovie(movie movie){
        try{
            movieDao.save(movie);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean savetextdata(textdata textdata) {
        try {
            textdataDao.save(textdata);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean saveprojectdata(project project) {
        try {
            projectDao.save(project);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public project findprojectbyid(int projectid) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(projectDao.findByID(projectid), project.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public project findprojectbyname(String projectname) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(projectDao.findByName(projectname), project.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public python_on_edit findpython_on_editbyuserid(int userid) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.convertValue(python_on_editDao.findByUserid(userid), python_on_edit.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public user finduserbyname(String username) {
        List<user> users = userDao.findNoPage();
        user theresult = null;
        for (user t : users) {
            if (t.getUsername().equals(username)) {
                theresult = t;
                break;
            }
        }
        return theresult;
    }

    @Transactional
    public python_on_edit findpython_on_editbyusername(String username) {
        try {
            user user = finduserbyname(username);
            return findpython_on_editbyuserid(user.getId());
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public boolean savepython_on_edit(python_on_edit python_on_edit) {
        try {
            python_on_editDao.save(python_on_edit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updatepython_on_edit(python_on_edit python_on_edit) {
        try {
            python_on_editDao.update(python_on_edit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
