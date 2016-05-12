package com.mycompany.biblio.business;

import com.mycompany.agenda.model.Contact;
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
public class ContactEJB {

    // ======================================
    // =             Attributes             =
    // ======================================
    @PersistenceContext(unitName = "jsfExamplePU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Contact> findAll() {
        Query query = em.createNamedQuery(Contact.FIND_ALL);
        return query.getResultList();
    }

    public Contact create(Contact contact) {
        em.persist(contact);
        return contact;
    }

    public void delete(List<Contact> list) {
        for (Contact contact : list) {
            delete(contact);
        }
    }

    public void delete(Contact contact) {
        em.remove(em.merge(contact));
    }
}
