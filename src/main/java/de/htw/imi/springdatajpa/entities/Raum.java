package de.htw.imi.springdatajpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Base class for all rooms.
 */
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "uni", name = "raeume")
@Getter
@Setter
public class Raum extends HTWEntity {

    private String name;

    private String raumnummer;

    private double flaeche;

    @Column(name = "raumhoehe")
    private double hoehe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockwerk")
    private Stockwerk stockwerk;

    protected Raum() {
        super();
    }


}
