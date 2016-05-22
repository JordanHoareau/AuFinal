package com.mycompany.biblio.model;


import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Transient;


@Entity
@NamedQuery(name = Seance.FIND_ALL, query = "SELECT b FROM Seance b")

public class Seance implements Serializable {
   public final static String FIND_ALL = "Seance.findAll";
   public final static String Del_SOM = "Seance.delete";

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private int jourSemaine; // Valeur entre 1 et 7 avec 1 : Lundi -> 5: Vendredi.
    private int nbPlacesRestantes; // Nombre de places disponibles pour la s�ance.
    @Transient
    private Boolean selected;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Seance(int JS, int nbPR) {
        
       this.jourSemaine = JS;
       this.nbPlacesRestantes = nbPR;
    }

    public Seance() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public long getId(){
        return id;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public void setId(long id) {
        this.id = id;
    }

    public int getJourSemaine() {
        return jourSemaine;
    }
    public void setJourSemaine(int JS){
        this.jourSemaine = JS;
    }
    
    public int getNbPlacesRestantes(){
        return nbPlacesRestantes;
    }
    
    public void setNbPlacesRestantes(int nbPR){
        this.nbPlacesRestantes = nbPR;
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
        sb.append("Seance");
        sb.append("{id=").append(id);
        sb.append(", JourSemaine='").append(jourSemaine).append('\'');
        sb.append(", Nb Places restantes=").append(nbPlacesRestantes);
        sb.append('}');
        return sb.toString();
    }
}
