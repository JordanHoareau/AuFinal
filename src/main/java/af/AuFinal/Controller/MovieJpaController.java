/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.AuFinal.Controller;

import af.AuFinal.Controller.exceptions.NonexistentEntityException;
import af.AuFinal.Controller.exceptions.PreexistingEntityException;
import af.AuFinal.Controller.exceptions.RollbackFailureException;
import af.AuFinal.Model.Movie;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import af.AuFinal.Model.Seance;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author norabbit
 */
public class MovieJpaController implements Serializable {

    public MovieJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movie movie) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (movie.getSeanceCollection() == null) {
            movie.setSeanceCollection(new ArrayList<Seance>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Seance> attachedSeanceCollection = new ArrayList<Seance>();
            for (Seance seanceCollectionSeanceToAttach : movie.getSeanceCollection()) {
                seanceCollectionSeanceToAttach = em.getReference(seanceCollectionSeanceToAttach.getClass(), seanceCollectionSeanceToAttach.getSeanceid());
                attachedSeanceCollection.add(seanceCollectionSeanceToAttach);
            }
            movie.setSeanceCollection(attachedSeanceCollection);
            em.persist(movie);
            for (Seance seanceCollectionSeance : movie.getSeanceCollection()) {
                Movie oldMovieidOfSeanceCollectionSeance = seanceCollectionSeance.getMovieid();
                seanceCollectionSeance.setMovieid(movie);
                seanceCollectionSeance = em.merge(seanceCollectionSeance);
                if (oldMovieidOfSeanceCollectionSeance != null) {
                    oldMovieidOfSeanceCollectionSeance.getSeanceCollection().remove(seanceCollectionSeance);
                    oldMovieidOfSeanceCollectionSeance = em.merge(oldMovieidOfSeanceCollectionSeance);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMovie(movie.getMovieid()) != null) {
                throw new PreexistingEntityException("Movie " + movie + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movie movie) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Movie persistentMovie = em.find(Movie.class, movie.getMovieid());
            Collection<Seance> seanceCollectionOld = persistentMovie.getSeanceCollection();
            Collection<Seance> seanceCollectionNew = movie.getSeanceCollection();
            Collection<Seance> attachedSeanceCollectionNew = new ArrayList<Seance>();
            for (Seance seanceCollectionNewSeanceToAttach : seanceCollectionNew) {
                seanceCollectionNewSeanceToAttach = em.getReference(seanceCollectionNewSeanceToAttach.getClass(), seanceCollectionNewSeanceToAttach.getSeanceid());
                attachedSeanceCollectionNew.add(seanceCollectionNewSeanceToAttach);
            }
            seanceCollectionNew = attachedSeanceCollectionNew;
            movie.setSeanceCollection(seanceCollectionNew);
            movie = em.merge(movie);
            for (Seance seanceCollectionOldSeance : seanceCollectionOld) {
                if (!seanceCollectionNew.contains(seanceCollectionOldSeance)) {
                    seanceCollectionOldSeance.setMovieid(null);
                    seanceCollectionOldSeance = em.merge(seanceCollectionOldSeance);
                }
            }
            for (Seance seanceCollectionNewSeance : seanceCollectionNew) {
                if (!seanceCollectionOld.contains(seanceCollectionNewSeance)) {
                    Movie oldMovieidOfSeanceCollectionNewSeance = seanceCollectionNewSeance.getMovieid();
                    seanceCollectionNewSeance.setMovieid(movie);
                    seanceCollectionNewSeance = em.merge(seanceCollectionNewSeance);
                    if (oldMovieidOfSeanceCollectionNewSeance != null && !oldMovieidOfSeanceCollectionNewSeance.equals(movie)) {
                        oldMovieidOfSeanceCollectionNewSeance.getSeanceCollection().remove(seanceCollectionNewSeance);
                        oldMovieidOfSeanceCollectionNewSeance = em.merge(oldMovieidOfSeanceCollectionNewSeance);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movie.getMovieid();
                if (findMovie(id) == null) {
                    throw new NonexistentEntityException("The movie with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Movie movie;
            try {
                movie = em.getReference(Movie.class, id);
                movie.getMovieid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movie with id " + id + " no longer exists.", enfe);
            }
            Collection<Seance> seanceCollection = movie.getSeanceCollection();
            for (Seance seanceCollectionSeance : seanceCollection) {
                seanceCollectionSeance.setMovieid(null);
                seanceCollectionSeance = em.merge(seanceCollectionSeance);
            }
            em.remove(movie);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movie> findMovieEntities() {
        return findMovieEntities(true, -1, -1);
    }

    public List<Movie> findMovieEntities(int maxResults, int firstResult) {
        return findMovieEntities(false, maxResults, firstResult);
    }

    private List<Movie> findMovieEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movie.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Movie findMovie(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movie.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovieCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movie> rt = cq.from(Movie.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
