package de.htw.imi.springdatajpa.entities.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ArbeitsRaum extends Raum {

    private int kapazitaet;

    protected ArbeitsRaum() {
        super();
    }

    public int getKapazitaet() {
        return kapazitaet;
    }

    public void setKapazitaet(int kapazitaet) {
        this.kapazitaet = kapazitaet;
    }
}
