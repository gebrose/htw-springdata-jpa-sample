package de.htw.imi.springdatajpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "uni", name = "Bueroraeume")
@Getter
@Setter
public class BueroRaum extends ArbeitsRaum {

    public BueroRaum() {
        super();
    }

}
