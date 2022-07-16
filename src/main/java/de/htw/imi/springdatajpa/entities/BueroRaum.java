package de.htw.imi.springdatajpa.entities.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BueroRaum extends ArbeitsRaum {

    public BueroRaum() {
        super();
    }

}
