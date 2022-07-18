package de.htw.imi.springdatajpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "uni", name = "professoren")
@Getter
@Setter
public class Professor extends HTWEntity {

    @Column(name = "pers_nr")
    private String personalNummer;

    private String name = "N.N.";

    private String rang;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raum")
    private BueroRaum raum;

    private int gehalt;

    public Professor() {
        super();
    }

}
