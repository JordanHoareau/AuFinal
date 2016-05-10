/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms.musicschool.view;

import java.util.List;
import javax.persistence.*;
import javax.ejb.Stateless;
import ms.musicschool.model.Eleve;

@Stateless
public class EleveEJB {

    @PersistenceContext(unitName = "msPU")
    private EntityManager em;
    
    public List<Eleve> findAll(){
        Query query;
        query = em.createNamedQuery("Eleve.findAll");
        return query.getResultList();
    }
    
    public Eleve create(Eleve e) {
        em.persist(e);
        return e;
    }

    public void delete(List<Eleve> list) {
        for (Eleve e : list) {
            delete(e);
        }
    }

    public void delete(Eleve e) {
        em.remove(em.merge(e));
    }
}
