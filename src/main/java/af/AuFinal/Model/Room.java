/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.AuFinal.Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ROOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByRoomid", query = "SELECT r FROM Room r WHERE r.roomid = :roomid"),
    @NamedQuery(name = "Room.findByMaxplaces", query = "SELECT r FROM Room r WHERE r.maxplaces = :maxplaces"),
    @NamedQuery(name = "Room.findByRoomprice", query = "SELECT r FROM Room r WHERE r.roomprice = :roomprice")})
public class Room implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomid")
    private Collection<Seance> seanceCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROOMID")
    private Integer roomid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAXPLACES")
    private int maxplaces;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROOMPRICE")
    private int roomprice;

    public Room() {
    }

    public Room(Integer roomid) {
        this.roomid = roomid;
    }

    public Room(Integer roomid, int maxplaces, int roomprice) {
        this.roomid = roomid;
        this.maxplaces = maxplaces;
        this.roomprice = roomprice;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public int getMaxplaces() {
        return maxplaces;
    }

    public void setMaxplaces(int maxplaces) {
        this.maxplaces = maxplaces;
    }

    public int getRoomprice() {
        return roomprice;
    }

    public void setRoomprice(int roomprice) {
        this.roomprice = roomprice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomid != null ? roomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.roomid == null && other.roomid != null) || (this.roomid != null && !this.roomid.equals(other.roomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "af.AuFinal.Model.Room[ roomid=" + roomid + " ]";
    }

    @XmlTransient
    public Collection<Seance> getSeanceCollection() {
        return seanceCollection;
    }

    public void setSeanceCollection(Collection<Seance> seanceCollection) {
        this.seanceCollection = seanceCollection;
    }
    
}
