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
@NamedQuery(name = Film.FIND_ALL, query = "SELECT f FROM Film f")

public class Film {
   public final static String FIND_ALL = "Film.findAll";
   public final static String Del_SOM = "Film.delete";

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String title;
    private String description;
    private String producer;
    private String synopsis;
    /*private enum relation {   
    Film, Famille, FilmPro, Collègue, Ami, Connaissance,   
    }*/
    @Transient
    private Boolean selected;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Film() {
    }

    public Film(String title, String description, String producer, String synopsis) {
        
       this.title = title;
       this.description = description;
       this.producer = producer;
       this.synopsis = synopsis;

    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {    
        this.synopsis = synopsis;
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
        return id + " : " + title;
    }
}
