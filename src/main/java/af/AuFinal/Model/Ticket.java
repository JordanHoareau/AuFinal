/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.AuFinal.Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author norabbit
 */
@Entity
@Table(name = "TICKET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByTicketid", query = "SELECT t FROM Ticket t WHERE t.ticketid = :ticketid"),
    @NamedQuery(name = "Ticket.findByTicketprice", query = "SELECT t FROM Ticket t WHERE t.ticketprice = :ticketprice"),
    @NamedQuery(name = "Ticket.findByIsrefundable", query = "SELECT t FROM Ticket t WHERE t.isrefundable = :isrefundable")})
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKETID")
    private Integer ticketid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKETPRICE")
    private int ticketprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISREFUNDABLE")
    private int isrefundable;
    @JoinColumn(name = "SEANCEID", referencedColumnName = "SEANCEID")
    @ManyToOne
    private Seance seanceid;

    public Ticket() {
    }

    public Ticket(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Ticket(Integer ticketid, int ticketprice, int isrefundable) {
        this.ticketid = ticketid;
        this.ticketprice = ticketprice;
        this.isrefundable = isrefundable;
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public int getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(int ticketprice) {
        this.ticketprice = ticketprice;
    }

    public int getIsrefundable() {
        return isrefundable;
    }

    public void setIsrefundable(int isrefundable) {
        this.isrefundable = isrefundable;
    }

    public Seance getSeanceid() {
        return seanceid;
    }

    public void setSeanceid(Seance seanceid) {
        this.seanceid = seanceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketid != null ? ticketid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.ticketid == null && other.ticketid != null) || (this.ticketid != null && !this.ticketid.equals(other.ticketid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "af.AuFinal.Model.Ticket[ ticketid=" + ticketid + " ]";
    }
    
}
