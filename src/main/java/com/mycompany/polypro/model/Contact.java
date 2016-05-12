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
@NamedQuery(name = Contact.FIND_ALL, query = "SELECT b FROM Contact b")

public class Contact {
   public final static String FIND_ALL = "Contact.findAll";
   public final static String Del_SOM = "Contact.delete";

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String prenom;
    private String nom;
    private String telephone_portable;
    @Column(length = 2000)
    private String telephone_fixe;
    private String telephone_pro;
    private String email;
    private String adresse;
   @Temporal(javax.persistence.TemporalType.DATE)
    private Date anniversaire;
    /*private enum relation {   
    Contact, Famille, ContactPro, Collègue, Ami, Connaissance,   
    }*/
    @Transient
    private Boolean selected;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Contact() {
    }

    public Contact(String prenom, String nom, String telephone_portable, String telephone_fixe, String telephone_pro, String email, String adresse, Date anniversaire) {
        
       this.prenom = prenom;
       this.nom = nom;
       this.telephone_portable = telephone_portable;
       this.telephone_fixe = telephone_fixe;
       this.telephone_pro = telephone_pro;
       this.email = email;
       this.adresse = adresse;
       this.email = email;
       this.anniversaire = anniversaire; 

    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    
    public String getPrenom(){
        return this.prenom;
    }
    
    public void setPrenom(String p){
        this.prenom=p;
    }
    
    public String getNom(){
        return this.nom;
    }
    
    public void setNom(String n){
        this.nom=n;
    }
    
    public String getTelephone_portable(){
        return this.telephone_portable;
    }
    
    public void setTelephone_portable(String t){
        this.telephone_portable=t;
    }
    
    public String getTelephone_fixe(){
        return this.telephone_fixe;
    }
    
    public void setTelephone_fixe(String t){
        this.telephone_fixe=t;
    }
    
    public String getTelephone_pro(){
        return this.telephone_pro;
    }
    
    public void setTelephone_pro(String t){
        this.telephone_pro=t;
    }
    
    public String getAdresse(){
        return this.adresse;
    }
    
    public void setAdresse(String a){
        this.adresse=a;
    }
    
    public String getEmail(){
        return this.email;
    } 
    
    public void setEmail(String e){
        this.email=e;
    }
    
    public Date getAnniversaire(){
        return this.anniversaire;
    }
    public void setAnniversaire(Date a){
        this.anniversaire=a;
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
        sb.append("Contact");
        sb.append("{id=").append(id);
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", nom=").append(nom);
        sb.append(", telephone portable='").append(telephone_portable).append('\'');
        sb.append(", telephone fixe='").append(telephone_fixe).append('\'');
        sb.append(", telephone pro='").append(telephone_pro).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", adresse=").append(adresse);
        sb.append(", anniversaire=").append(anniversaire);
        sb.append('}');
        return sb.toString();
    }
}
