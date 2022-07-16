package de.htw.imi.springdatajpa.entities.entities;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Professor extends Entity {

    private String name;

    private String rang;

    @ManyToOne
    private BueroRaum raum;

    private int gehalt;

    public Professor() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(final String rang) {
        this.rang = rang;
    }

    /**
     * models a one-to-one relationship to offices
     */
    public BueroRaum getRaum() {
        return raum;
    }

    public void setRaum(final BueroRaum raum) {
        this.raum = raum;
    }

    public int getGehalt() {
        return gehalt;
    }

    public void setGehalt(final int gehalt) {
        this.gehalt = gehalt;
    }

}
