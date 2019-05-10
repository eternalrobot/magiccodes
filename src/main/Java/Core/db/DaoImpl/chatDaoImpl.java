package Core.db.DaoImpl;

import Core.data.chat;
import Core.db.Dao.chatDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository("chatDaoImpl")
public class chatDaoImpl  implements chatDao {
    @PersistenceContext
    EntityManager em;
    @Override
    public Object save(chat chat) {
        em.persist(chat);
        return chat;
    }

    @Override
    public Object update(chat chat) {
        em.persist(em.merge(chat));
        return chat;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(chat.class,id));
    }

    @Override
    public List<chat> findNoPage() {
        String queryString="select * from chat";
        Query query =em.createNativeQuery(queryString,chat.class);

        List<?> result=query.getResultList();

        List<chat> T=new ArrayList<chat>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                chat t= (chat) iterable.next();
                T.add(t);
            }
        }
        return T;
    }

    @Override
    public Object findByID(int id) {
        return em.find(chat.class,id);
    }
}
