package de.htw.imi.springdatajpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "uni", name = "Arbeitsraeume")
@Getter
@Setter
class ArbeitsRaum extends Raum {

    private int kapazitaet;

    protected ArbeitsRaum() {
        super();
    }

}
