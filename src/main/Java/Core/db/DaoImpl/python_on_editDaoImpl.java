package Core.db.DaoImpl;

import Core.data.python_on_edit;
import Core.db.Dao.python_on_editDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository("python_on_editDaoImpl")
public class python_on_editDaoImpl implements python_on_editDao {
    @PersistenceContext
    EntityManager em;
    @Override
    public Object save(python_on_edit python_on_edit) {
        em.persist(python_on_edit);
        return python_on_edit;
    }

    @Override
    public Object update(python_on_edit python_on_edit) {
        em.persist(em.merge(python_on_edit));
        return python_on_edit;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(python_on_edit.class,id));
    }

    @Override
    public List<python_on_edit> findNoPage() {
        String queryString="select * from python_on_edit";
        Query query =em.createNativeQuery(queryString,python_on_edit.class);

        List<?> result=query.getResultList();

        List<python_on_edit> T=new ArrayList<python_on_edit>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                python_on_edit t= (python_on_edit) iterable.next();
                T.add(t);
            }
        }
        return T;
    }

    @Override
    public Object findByID(int id) {
        return em.find(python_on_edit.class,id);
    }

    @Override
    public Object findByUserid(int userid) {
        String queryString="select * from python_on_edit where userid="+userid+"";
        Query query =em.createNativeQuery(queryString,python_on_edit.class);

        List<?> result=query.getResultList();

        python_on_edit t=null;

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            t= (python_on_edit) iterable.next();
        }
        return t;
    }
}
