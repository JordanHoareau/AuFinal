package com.mycompany.biblio.business;

import com.mycompany.polypro.model.Ticket;
import java.util.List;
import javax.persistence.*;
import javax.ejb.Stateless;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateless
public class TicketEJB {

    // ======================================
    // =             Attributes             =
    // ======================================
    @PersistenceContext(unitName = "jsfExamplePU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Ticket> findAll() {
        Query query = em.createNamedQuery(Ticket.FIND_ALL);
        return query.getResultList();
    }

    public Ticket create(Ticket ticket) {
        em.persist(ticket);
        return ticket;
    }

    public void delete(List<Ticket> list) {
        for (Ticket ticket : list) {
            delete(ticket);
        }
    }

    public void delete(Ticket ticket) {
        em.remove(em.merge(ticket));
    }    
      
    public void update(Ticket ticket) {
        em.merge(ticket);
    }
}
