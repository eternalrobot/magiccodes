package Core.db.Service;

import Core.data.user;
import Core.db.Dao.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("mainPageService")
public class mainPageService {
    @Autowired
    userDao userDao;

    @Transactional
    public user getUserByName(String username){
        List<user> users=userDao.findNoPage();
        user theresult=null;
        for(user t :users){
            if(t.getUsername().equals(username)){
                theresult=t;
                break;
            }
        }
        return theresult;
    }
}
