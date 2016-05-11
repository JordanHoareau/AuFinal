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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author norabbit
 */
@Entity
@Table(name = "MOVIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findByMovieid", query = "SELECT m FROM Movie m WHERE m.movieid = :movieid"),
    @NamedQuery(name = "Movie.findByMovietitle", query = "SELECT m FROM Movie m WHERE m.movietitle = :movietitle"),
    @NamedQuery(name = "Movie.findByMovieprice", query = "SELECT m FROM Movie m WHERE m.movieprice = :movieprice"),
    @NamedQuery(name = "Movie.findByMoviedesc", query = "SELECT m FROM Movie m WHERE m.moviedesc = :moviedesc"),
    @NamedQuery(name = "Movie.findByMovieyear", query = "SELECT m FROM Movie m WHERE m.movieyear = :movieyear")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVIEID")
    private Integer movieid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "MOVIETITLE")
    private String movietitle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOVIEPRICE")
    private int movieprice;
    @Size(max = 40)
    @Column(name = "MOVIEDESC")
    private String moviedesc;
    @Column(name = "MOVIEYEAR")
    private Integer movieyear;
    @OneToMany(mappedBy = "movieid")
    private Collection<Seance> seanceCollection;

    public Movie() {
    }

    public Movie(Integer movieid) {
        this.movieid = movieid;
    }

    public Movie(Integer movieid, String movietitle, int movieprice) {
        this.movieid = movieid;
        this.movietitle = movietitle;
        this.movieprice = movieprice;
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public int getMovieprice() {
        return movieprice;
    }

    public void setMovieprice(int movieprice) {
        this.movieprice = movieprice;
    }

    public String getMoviedesc() {
        return moviedesc;
    }

    public void setMoviedesc(String moviedesc) {
        this.moviedesc = moviedesc;
    }

    public Integer getMovieyear() {
        return movieyear;
    }

    public void setMovieyear(Integer movieyear) {
        this.movieyear = movieyear;
    }

    @XmlTransient
    public Collection<Seance> getSeanceCollection() {
        return seanceCollection;
    }

    public void setSeanceCollection(Collection<Seance> seanceCollection) {
        this.seanceCollection = seanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieid != null ? movieid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.movieid == null && other.movieid != null) || (this.movieid != null && !this.movieid.equals(other.movieid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "af.AuFinal.Model.Movie[ movieid=" + movieid + " ]";
    }
    
}
