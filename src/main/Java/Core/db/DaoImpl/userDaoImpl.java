package Core.db.DaoImpl;

import Core.data.user;
import Core.db.Dao.userDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository("userDaoImpl")
public class userDaoImpl implements userDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public Object save(user user) {
        em.persist(user);
        return user;
    }

    @Override
    public Object update(user user) {
        em.persist(em.merge(user));
        return user;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(user.class,id));
    }

    @Override
    public List<user> findNoPage() {
        String queryString="select * from user";
        Query query =em.createNativeQuery(queryString,user.class);

        List<?> result=query.getResultList();

        List<user> T=new ArrayList<user>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                user t= (user) iterable.next();
                T.add(t);
            }
        }
        return T;
    }

    @Override
    public Object findByID(int id) {
        return em.find(user.class,id);
    }

    @Override
    public user findByString(String s) {
        String queryString="select * from user where username=\""+s+"\"or email=\""+s+"\"";
        Query query =em.createNativeQuery(queryString,user.class);

        List<?> result=query.getResultList();

        user user=null;

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                user t= (user) iterable.next();
                user=t;
            }
        }
        return user;
    }
}
