package de.htw.imi.springdatajpa.services;

import de.htw.imi.springdatajpa.entities.BueroRaum;
import de.htw.imi.springdatajpa.repos.BueroRaumRepository;
import de.htw.imi.springdatajpa.web.BueroDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the DAO (data access object) pattern for BueroRaum.
 * <p>
 * Classes annotated with @Service can be injected using @Autowired
 * in other Spring components.
 * <p>
 * Classes annotated with @Slf4j have access to loggers.
 */
@Service
@Slf4j
public class BueroRaumService {

    @Autowired
    BueroRaumRepository bueroRaumRepository;

    public BueroRaum createFrom(final BueroDto template) {
        final BueroRaum bueroRaum = new BueroRaum();
        bueroRaum.setFlaeche(template.getFlaeche());
        bueroRaum.setName(template.getName());
        bueroRaum.setHoehe(template.getHoehe());
        bueroRaum.setKapazitaet(template.getKapazitaet());
        bueroRaum.setRaumnummer(template.getRaumnummer());
        return bueroRaumRepository.save(bueroRaum);
    }

}
