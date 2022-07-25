package de.htw.imi.springdatajpa.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "uni", name = "professoren")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE) //Provide cache strategy.
@Getter
@Setter
public class Professor extends HTWEntity {

    @Column(name = "pers_nr")
    private Integer personalNummer;

    private String name = "N.N.";

    private String rang;

    @OneToOne
    @JoinColumn(name = "raum")
    private BueroRaum raum;

    private int gehalt;

    public Professor() {
        super();
    }

}
