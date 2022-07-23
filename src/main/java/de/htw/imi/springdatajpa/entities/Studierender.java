package de.htw.imi.springdatajpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "uni", name = "studierende")
@Getter
@Setter
public class Studierender extends HTWEntity {

    @Column(name = "matr_nr")
    private Integer matrikelNummer;

    private String name;

    private String rang;

    private String vorname;

    private Date geburtsdatum;

    private String geburtsort;

    private int anzahl_semester;

    private String studienbeginn;

    public Studierender() {
        super();
    }

}
