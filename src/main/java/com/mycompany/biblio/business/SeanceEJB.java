package com.mycompany.biblio.business;

import com.mycompany.polypro.model.Seance;
import java.util.List;
import javax.persistence.*;
import javax.ejb.Stateless;

@Stateless
public class SeanceEJB {

    // ======================================
    // =             Attributes             =
    // ======================================
    @PersistenceContext(unitName = "jsfExamplePU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Seance> findAll() {
        Query query = em.createNamedQuery(Seance.FIND_ALL);
        return query.getResultList();
    }

    public Seance create(Seance seance) {
        em.persist(seance);
        return seance;
    }

    public void delete(List<Seance> list) {
        for (Seance seance : list) {
            delete(seance);
        }
    }

    public void delete(Seance seance) {
        em.remove(em.merge(seance));
    }
    
    public void update(Seance seance) {
        em.merge(seance);
    }
}
