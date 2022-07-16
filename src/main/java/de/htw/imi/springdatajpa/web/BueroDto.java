package de.htw.imi.springdatajpa.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object (DTO) class:
 * decouples representation from the entity class.
 * <p>
 * NB: we cannot create BueroRaum objects without an id.
 * Objects of this class simply hold field values
 */
@Getter
@Setter
public class BueroDto {

    private String name;

    private String raumnummer;

    private int kapazitaet;

    private int flaeche;

    private int hoehe;

}
