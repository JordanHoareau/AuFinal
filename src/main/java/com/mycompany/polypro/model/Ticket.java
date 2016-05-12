/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.polypro.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Transient;


@Entity
@NamedQuery(name = Ticket.FIND_ALL, query = "SELECT b FROM Ticket b")

public class Ticket {
   public final static String FIND_ALL = "Ticket.findAll";
   public final static String Del_SOM = "Ticket.delete";

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private boolean  remboursable;
    private int numero_place;
    @Transient
    private Boolean selected;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Ticket() {
    }

    public Ticket(boolean r, int np) {
        
       this.remboursable =r;
       this.numero_place=np;

    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    
    
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero_place() {
        return numero_place;
    }
    
    public void setNumero_place(int numero_place) {
        this.numero_place = numero_place;
    }

    public boolean getRemboursable() {
        return this.remboursable;
    }
    
    public void setRemboursable(boolean b){
        this.remboursable=b;
    }
    
    public void setNom(int n){
        this.numero_place=n;
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
        sb.append(", numero_place=").append(numero_place);

        sb.append('}');
        return sb.toString();
    }
}
