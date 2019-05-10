package Core.db.DaoImpl;

import Core.data.project;
import Core.db.Dao.projectDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository("projectDaoImpl")
public class projectDaoImpl implements projectDao {
    @PersistenceContext
    EntityManager em;
    @Override
    public Object save(project project) {
        em.persist(project);
        return project;
    }

    @Override
    public Object update(project project) {
        em.persist(em.merge(project));
        return project;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(project.class,id));
    }

    @Override
    public List<project> findNoPage() {
        String queryString="select * from project";
        Query query =em.createNativeQuery(queryString,project.class);

        List<?> result=query.getResultList();

        List<project> T=new ArrayList<project>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                project t= (project) iterable.next();
                T.add(t);
            }
        }
        return T;
    }


    @Override
    public Object findByID(int id) {
        return em.find(project.class,id);
    }

    @Override
    public Object findByName(String name) {
        String queryString="select * from project where thename='"+name+"'";
        Query query =em.createNativeQuery(queryString,project.class);

        List<?> result=query.getResultList();

        project t=null;

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            t= (project) iterable.next();
        }
        return t;
    }
}
