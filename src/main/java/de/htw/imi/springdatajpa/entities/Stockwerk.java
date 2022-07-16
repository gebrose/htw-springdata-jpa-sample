package de.htw.imi.springdatajpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "uni", name = "stockwerke")
@Getter
@Setter
public class Stockwerk extends HTWEntity {

    private int geschossnummer;

    @OneToMany(mappedBy = "stockwerk", fetch = FetchType.EAGER)
    private List<Raum> raeume = new ArrayList<>();

    public Stockwerk() {
        super();
    }

}
