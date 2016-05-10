/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ms.musicschool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Eleve.findAll", query = "SELECT e FROM Eleve e")
public class Eleve {
    @Id
    @Column(nullable = false)
    private String name;
    
    public Eleve() {}

    public Eleve(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
