package Core.db.Service;

import Core.data.user;
import Core.db.Dao.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("registerPageService")
public class registerPageService {
    @Autowired
    userDao userDao;

    @Transactional
    public boolean add_new_user(user user){
        try{
            userDao.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean is_has_same_username(String username){
        user user=userDao.findByString(username);
        if(user==null){
            return false;
        }
        else{
            return true;
        }
    }

    @Transactional
    public boolean is_has_same_email(String email){
        user user=userDao.findByString(email);
        if(user==null){
            return false;
        }
        else{
            return true;
        }
    }
}
