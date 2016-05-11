/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.AuFinal.Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author norabbit
 */
@Entity
@Table(name = "SEANCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seance.findAll", query = "SELECT s FROM Seance s"),
    @NamedQuery(name = "Seance.findBySeanceid", query = "SELECT s FROM Seance s WHERE s.seanceid = :seanceid"),
    @NamedQuery(name = "Seance.findByDayofweek", query = "SELECT s FROM Seance s WHERE s.dayofweek = :dayofweek"),
    @NamedQuery(name = "Seance.findByRemainingplaces", query = "SELECT s FROM Seance s WHERE s.remainingplaces = :remainingplaces")})
public class Seance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEANCEID")
    private Integer seanceid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAYOFWEEK")
    private int dayofweek;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REMAININGPLACES")
    private int remainingplaces;
    @OneToMany(mappedBy = "seanceid")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "ROOMID", referencedColumnName = "ROOMID")
    @ManyToOne(optional = false)
    private Room roomid;
    @JoinColumn(name = "MOVIEID", referencedColumnName = "MOVIEID")
    @ManyToOne
    private Movie movieid;

    public Seance() {
    }

    public Seance(Integer seanceid) {
        this.seanceid = seanceid;
    }

    public Seance(Integer seanceid, int dayofweek, int remainingplaces) {
        this.seanceid = seanceid;
        this.dayofweek = dayofweek;
        this.remainingplaces = remainingplaces;
    }

    public Integer getSeanceid() {
        return seanceid;
    }

    public void setSeanceid(Integer seanceid) {
        this.seanceid = seanceid;
    }

    public int getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(int dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getRemainingplaces() {
        return remainingplaces;
    }

    public void setRemainingplaces(int remainingplaces) {
        this.remainingplaces = remainingplaces;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public Room getRoomid() {
        return roomid;
    }

    public void setRoomid(Room roomid) {
        this.roomid = roomid;
    }

    public Movie getMovieid() {
        return movieid;
    }

    public void setMovieid(Movie movieid) {
        this.movieid = movieid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seanceid != null ? seanceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seance)) {
            return false;
        }
        Seance other = (Seance) object;
        if ((this.seanceid == null && other.seanceid != null) || (this.seanceid != null && !this.seanceid.equals(other.seanceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "af.AuFinal.Model.Seance[ seanceid=" + seanceid + " ]";
    }
    
}
