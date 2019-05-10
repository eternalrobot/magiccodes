package Core.db.DaoImpl;

import Core.data.movie;
import Core.db.Dao.movieDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository("movieDaoImpl")
public class movieDaoImpl implements movieDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public Object save(movie movie) {
        em.persist(movie);
        return movie;
    }

    @Override
    public Object update(movie movie) {
        em.persist(em.merge(movie));
        return movie;
    }

    @Override
    public void deleteByID(int id) {
        em.remove(em.find(movie.class,id));
    }

    @Override
    public List<movie> findNoPage() {
        String queryString="select * from movie";
        Query query =em.createNativeQuery(queryString,movie.class);

        List<?> result=query.getResultList();

        List<movie> T=new ArrayList<movie>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                movie t= (movie) iterable.next();
                T.add(t);
            }
        }
        return T;
    }

    @Override
    public Object findByID(int id) {
        return em.find(movie.class,id);
    }

    @Override
    public List<movie> findByNameMOHU(String name) {
        String queryString="select * from movie where moviename like '%"+name+"%'";
        Query query =em.createNativeQuery(queryString,movie.class);

        List<?> result=query.getResultList();

        List<movie> T=new ArrayList<movie>();

        if(result!=null){
            Iterator<?> iterable=result.iterator();
            while(iterable.hasNext()){
                movie t= (movie) iterable.next();
                T.add(t);
            }
        }
        return T;
    }
}
