package de.htw.imi.springdatajpa.entities.entities;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Stockwerk extends Entity {

    private int geschossnummer;

    @OneToMany
    // 1:n relationship, mapped by FK geschoss_id in raum
    private List<Raum> raeume = new ArrayList<>();

    public Stockwerk() {
        super();
    }

    public int getGeschossnummer() {
        return geschossnummer;
    }

    public void setGeschossnummer(final int geschossnummer) {
        this.geschossnummer = geschossnummer;
    }

    public List<Raum> getRaeume() {
        return raeume;
    }

    public void setRaeume(final List<Raum> raeume) {
        this.raeume = raeume;
    }
}
