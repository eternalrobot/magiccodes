package Core.db.DaoImpl;

import Core.data.code;
import Core.db.Dao.codeDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository("codeDaoImpl")
public class codeDaoImpl implements codeDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public Object save(code code) {
        em.persist(code);
        return code;
    }

    @Override
    public Object update(code code) {
        em.persist(em.merge(code));
        return code;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(code.class,id));
    }

    @Override
    public List<code> findNoPage() {
        String queryString="select * from code";
        Query query =em.createNativeQuery(queryString,code.class);

        List<?> result=query.getResultList();

        List<code> T=new ArrayList<code>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                code t= (code) iterable.next();
                T.add(t);
            }
        }
        return T;
    }

    @Override
    public Object findByID(int id) {
        return em.find(code.class,id);
    }
}
