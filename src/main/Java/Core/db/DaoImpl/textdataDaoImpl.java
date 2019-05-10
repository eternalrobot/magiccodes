package Core.db.DaoImpl;

import Core.data.textdata;
import Core.db.Dao.textdataDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository("textdataDaoImpl")
public class textdataDaoImpl implements textdataDao {
    @PersistenceContext
    EntityManager em;
    @Override
    public Object save(textdata textdata) {
        em.persist(textdata);
        return textdata;
    }

    @Override
    public Object update(textdata textdata) {
        em.persist(em.merge(textdata));
        return textdata;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(textdata.class,id));
    }

    @Override
    public List<textdata> findNoPage() {
        String queryString="select * from textdata";
        Query query =em.createNativeQuery(queryString,textdata.class);

        List<?> result=query.getResultList();

        List<textdata> T=new ArrayList<textdata>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                textdata t= (textdata) iterable.next();
                T.add(t);
            }
        }
        return T;
    }

    @Override
    public Object findByID(int id) {
        return em.find(textdata.class,id);
    }
}
