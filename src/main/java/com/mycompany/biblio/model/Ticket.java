/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.biblio.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


@Entity
@NamedQuery(name = Ticket.FIND_ALL, query = "SELECT b FROM Ticket b")

public class Ticket implements Serializable {
   public final static String FIND_ALL = "Ticket.findAll";
   public final static String Del_SOM = "Ticket.delete";

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private boolean  remboursable;
    private int prixTotal;
    
    @OneToOne
    private Seance seance;
    
    @Transient
    private Boolean selected;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Ticket() {   
    }

    public Ticket(boolean r, int tp) {
       int supplement = 0;
       this.remboursable =r;
       supplement = (!remboursable) ? 0 : 1;
       System.out.println(supplement+"--------------------------------------------------------------------------------------");
       this.prixTotal = supplement;

    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }
    
    
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public boolean getRemboursable() {
        return this.remboursable;
    }
    
    public void setRemboursable(boolean b){
        this.remboursable=b;
    }
 
            
    public Boolean isSelected() {
        return selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Ticket");
        sb.append("{id=").append(id);
        sb.append(", remboursable='").append(remboursable).append('\'');

        sb.append('}');
        return sb.toString();
    }
}
