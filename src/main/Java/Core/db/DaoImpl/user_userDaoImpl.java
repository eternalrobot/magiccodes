package Core.db.DaoImpl;

import Core.data.user_user;
import Core.db.Dao.user_userDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository("user_userDaoImpl")
public class user_userDaoImpl implements user_userDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public Object save(user_user user_user) {
        em.persist(user_user);
        return user_user;
    }

    @Override
    public Object update(user_user user_user) {
        em.persist(em.merge(user_user));
        return user_user;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(user_user.class,id));
    }

    @Override
    public List<user_user> findNoPage() {
        String queryString="select * from user_user";
        Query query =em.createNativeQuery(queryString,user_user.class);

        List<?> result=query.getResultList();

        List<user_user> T=new ArrayList<user_user>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                user_user t= (user_user) iterable.next();
                T.add(t);
            }
        }
        return T;
    }

    @Override
    public Object findByID(int id) {
        return em.find(user_user.class,id);
    }

    @Override
    public Object findUser_UserByTwoUsername(String username1, String username2) {
        String queryString="select * from user_user where username1=\""+username1+"\"and username2=\""+username2+"\"";
        Query query =em.createNativeQuery(queryString,user_user.class);

        List<?> result=query.getResultList();

        user_user user_user=null;

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            if(iterable.hasNext()) user_user = (user_user) iterable.next();
        }
        return user_user;
    }
}
